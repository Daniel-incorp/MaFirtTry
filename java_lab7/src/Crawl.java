import jdk.jfr.internal.tool.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class Crawl {

    public static final String  BEFORE_URL = "a href=";
    public static final int     MAXDepth = 3;
    public static final int     MAXThreads = 16;

    private static int activeThreads = 0;

    private static LinkedList<URLDepthPair> CheckedURL = new LinkedList<>();
    private static LinkedList<URLDepthPair> UncheckedURL = new LinkedList<>();


    private static final Main m = new Main();
    public static void main(String[] args) {

        //https://www.nytimes.com/
        //https://slashdot.org/
        URLDepthPair firstURL = new URLDepthPair("https://slashdot.org/", 0);


        UncheckedURL.add(firstURL);

        // Значит цикл работает так: Если потоков меньше MAXThreads -> крутим цикл (иначе main.wait() (wait снимается при окончании работы потока)).
        // Если UncheckedURL не пуст и глубина поиска не превышена -> добавляем UncheckedURL.get(0) к CheckedURL и удаляем из UncheckedURL, после чего начинаем новый поток
        synchronized (UncheckedURL) {
            synchronized (CheckedURL) {
                    while (!UncheckedURL.isEmpty() || activeThreads != 0) { // работает пока не закончатся непроверенные ссылки и все потоки
                        // ВНИМАНИЕ - весь код внутри этого цикла сильно зависит от друг друга!!!


                        try {
                            synchronized (m) {
                                while (activeThreads >= MAXThreads) {
                                    System.out.println("waiting"); //[ДЛЯ НАГЛЯДНОСТИ]
                                    m.wait();
                                }
                            }
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                        }

                        if (!UncheckedURL.isEmpty()) {
                            if (UncheckedURL.get(0).getDepth() < MAXDepth) {

                                URLDepthPair urlDepthPair = UncheckedURL.get(0);
                                UncheckedURL.remove(urlDepthPair);
                                CheckedURL.add(urlDepthPair);

                                System.out.println(urlDepthPair.getStringFormat()); //[ДЛЯ НАГЛЯДНОСТИ]


                                activeThreads++;
                                startThread(urlDepthPair);

                            } else {
                                CheckedURL.add(UncheckedURL.get(0));
                                UncheckedURL.remove(UncheckedURL.get(0));
                            }
                        }
                    }
                }
            }

        System.out.println("UNCHECKED");
        printAll(UncheckedURL);
        System.out.println("CHECKED");
        printAll(CheckedURL);


    }

    public static void startThread (URLDepthPair urlDepthPair){
        new Thread(){
            @Override
            public void run() {

                crawlThroughURL(urlDepthPair); // [ИДЕЯ] засунуть этот метод в try catch чтобы потоки никогда не обрывались от ошибок этого метода
                activeThreads--;
                synchronized (m) {
                    if (activeThreads <= MAXThreads) {
                        m.notify();
                        // System.out.println("notifying"); //[ДЛЯ НАГЛЯДНОСТИ]
                    }
                }
            }
        }.start();
    }

    private static void crawlThroughURL (URLDepthPair urlDepthPair){
        try {
            URLConnection urlConnection;

            // устанавливаем соединение
            urlConnection = new URL(urlDepthPair.getURLAddress()).openConnection();
            urlConnection.setConnectTimeout(10_1000);

            // создаём Reader для чтения
            BufferedReader in = null;
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                in = new BufferedReader(inputStreamReader);
            } catch (ConnectException connectException) {
               // connectException.printStackTrace();
            }

            // читаем сайт
            String s;
            if (in != null) {
                while ((s = in.readLine()) != null) {

                    //System.out.println(s); //[ДЛЯ НАГЛЯДНОСТИ]

                    if (s.contains(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX) && urlDepthPair.getDepth() < Crawl.MAXDepth) { // содержит a href="http://
                        try {
                            String url = s.substring(s.indexOf(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX) + Crawl.BEFORE_URL.length() + 1); // обрезаем url адресс от лишнего слева
                            url = url.substring(0, url.indexOf("\"")); //обрезаем url адресс от лишнего справа


                            // url = url.replace(URLDepthPair.URL_PREFIX, URLDepthPair.URL_PREFIX_S); // костыль, потому что некоторые ссылки http работают только, если они https


                            URLDepthPair foundURL = new URLDepthPair(url, urlDepthPair.getDepth() + 1);
                            if (!listContains(UncheckedURL, foundURL) && !listContains(CheckedURL, foundURL)) {
                                UncheckedURL.add(foundURL);
                            }
                        } catch (StringIndexOutOfBoundsException e){
                            //e.printStackTrace();
                        }
                    }
                    if (s.contains(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX_S) && urlDepthPair.getDepth() < Crawl.MAXDepth) {
                        try {

                            String url = s.substring(s.indexOf(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX_S) + Crawl.BEFORE_URL.length() + 1); // обрезаем url адресс от лишнего слева
                            url = url.substring(0, url.indexOf("\"")); //обрезаем url адресс от лишнего справа

                            // url = url.replace(URLDepthPair.URL_PREFIX, URLDepthPair.URL_PREFIX_S); // костыль, потому что некоторые ссылки http работают только, если они https


                            URLDepthPair foundURL = new URLDepthPair(url, urlDepthPair.getDepth() + 1);
                            if (!listContains(UncheckedURL, foundURL) && !listContains(CheckedURL, foundURL)) {
                                UncheckedURL.add(foundURL);
                            }
                        } catch (StringIndexOutOfBoundsException e){
                            //e.printStackTrace();
                        }
                    }

                    if (s.contains("301 Moved Permanently") && !urlDepthPair.getURLAddress().contains(URLDepthPair.URL_PREFIX_S)){
                        crawlThroughURL(new URLDepthPair(urlDepthPair.getURLAddress().replace(URLDepthPair.URL_PREFIX, URLDepthPair.URL_PREFIX_S), urlDepthPair.getDepth()));
                        break;
                    }

                }

                in.close();
                inputStreamReader.close();
                urlConnection.getInputStream().close();

            }
        } catch (IOException ioException) {
            //ioException.printStackTrace();
        }


    }

    private static boolean listContains (LinkedList<URLDepthPair> linkedList, URLDepthPair urlDepthPair) {

        if (linkedList.isEmpty()) return false;
        LinkedList<URLDepthPair> newLinkedList =(LinkedList<URLDepthPair>) linkedList.clone(); // чтобы избежать ConcurrentModificationException
        for (URLDepthPair ur: newLinkedList) {
            if (ur.getURLAddress().equals(urlDepthPair.getURLAddress())) return true;
        }
        return false;
    }



    private static void printAll (LinkedList<URLDepthPair> linkedList){
        for (URLDepthPair urlDepthPair : linkedList) {
            System.out.println(urlDepthPair.getStringFormat());
        }
    }






}
