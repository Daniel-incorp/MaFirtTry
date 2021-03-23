import jdk.jfr.internal.tool.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Crawl {

    public static final String  BEFORE_URL = "a href=";

    private static int     MAXThreads = 0;
    public  static URLPool urlPool;

    private static int activeThreads = 0;




    private static final Main m = new Main();
    public static void main(String[] args) {


        //https://slashdot.org/    - нормальная рабочая ссылка
       //startWithUserInput();


        //Нормальный способ активировать программу
        urlPool = new URLPool(3);
        MAXThreads = 8;
        urlPool.addToUnchecked(new URLDepthPair("https://slashdot.org/", 0));


        while (!urlPool.uncheckedIsEmpty() || activeThreads != 0) { // работает пока не закончатся непроверенные ссылки и все потоки
            try {
                synchronized (m) {// чтобы была возможность усыпить main поток
                    while (activeThreads >= MAXThreads) { // спим пока потоков >= максимально установленных потоков
                        System.out.println("waiting... threads working = " + activeThreads); //[ДЛЯ НАГЛЯДНОСТИ]
                        m.wait(1000);
                    }

                    if (!urlPool.uncheckedIsEmpty()) {//лист непроверенных url не пуст -> начинаем новый поток
                        startThread(urlPool.getUncheckedURL(0));
                    } else m.wait(1000);//иначе спим
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("UNCHECKED");
        urlPool.printAllUnchecked();
        
        System.out.println("CHECKED");
        urlPool.printAllChecked();


    }

    public static void startThread (URLDepthPair urlDepthPair){
        System.out.println(urlDepthPair.getStringFormat()); //[ДЛЯ НАГЛЯДНОСТИ]
        Crawl.activeThreadsInc();
        Crawl.urlPool.addToChecked(urlDepthPair);
        new Thread(new CrawlerTask(urlDepthPair), "thread №" + activeThreads).start();
    }

    public static void crawlThroughURL (URLDepthPair urlDepthPair){
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
                //connectException.printStackTrace();
            }

            // читаем сайт
            String s;
            if (in != null) {
                while ((s = in.readLine()) != null) {

                    //System.out.println(s); //[ДЛЯ НАГЛЯДНОСТИ]

                    if (s.contains(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX) && urlDepthPair.getDepth() < urlPool.MAXDepth) { // содержит a href="http://
                        try {
                            String url = s.substring(s.indexOf(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX) + Crawl.BEFORE_URL.length() + 1); // обрезаем url адресс от лишнего слева
                            url = url.substring(0, url.indexOf("\"")); //обрезаем url адресс от лишнего справа


                            // url = url.replace(URLDepthPair.URL_PREFIX, URLDepthPair.URL_PREFIX_S); // костыль, потому что некоторые ссылки http работают только, если они https


                            URLDepthPair foundURL = new URLDepthPair(url, urlDepthPair.getDepth() + 1);
                            if (!urlPool.poolContains(foundURL) ) {
                                urlPool.addToUnchecked(foundURL);
                            }
                        } catch (StringIndexOutOfBoundsException e){
                            //e.printStackTrace();
                        }
                    }
                    if (s.contains(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX_S) && urlDepthPair.getDepth() < urlPool.MAXDepth) {
                        try {

                            String url = s.substring(s.indexOf(Crawl.BEFORE_URL + "\"" + URLDepthPair.URL_PREFIX_S) + Crawl.BEFORE_URL.length() + 1); // обрезаем url адресс от лишнего слева
                            url = url.substring(0, url.indexOf("\"")); //обрезаем url адресс от лишнего справа

                            // url = url.replace(URLDepthPair.URL_PREFIX, URLDepthPair.URL_PREFIX_S); // костыль, потому что некоторые ссылки http работают только, если они https


                            URLDepthPair foundURL = new URLDepthPair(url, urlDepthPair.getDepth() + 1);
                            if (!urlPool.poolContains(foundURL)) {
                                urlPool.addToUnchecked(foundURL);
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
    private static void startWithUserInput (){
        // активируем прогу с помощью ввода пользователя
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try { // Ввод и проверка MAXDepth
                if (urlPool == null) {
                    System.out.println("Введите максимальную глубину поиска:");
                    String writingPoolDepth = scanner.nextLine();
                    int i = Integer.parseInt(writingPoolDepth);
                    if (i <= 0) {
                        System.out.print("Ошибка ввода! ");
                        continue;
                    }
                    urlPool = new URLPool(i);
                }
            }
            catch (NumberFormatException numberFormatException) {
                System.out.print("Ошибка ввода! ");
                continue;
            }
            try { // Ввод и проверка MAXThreads
                if (MAXThreads == 0) {
                    System.out.println("Введите количество потоков");
                    String writingMAXThreads = scanner.nextLine();
                    int i = Integer.parseInt(writingMAXThreads);
                    if (i <= 0){
                        System.out.print("Ошибка ввода! ");
                        continue;
                    }
                    MAXThreads = i;
                }
            }
            catch (NumberFormatException numberFormatException) {
                System.out.print("Ошибка ввода! ");
                continue;
            }
            try { // Ввод и проверка url
                System.out.println("Введите сайт для начала поиска");
                String writingURL = scanner.nextLine();
                (new java.net.URL(writingURL)).openStream().close();
                urlPool.addToUnchecked(new URLDepthPair(writingURL, 0));
                break;
            } catch (Exception ex) {
                System.out.print("Ошибка ввода! ");
            }
        }
    }


    public static synchronized void activeThreadsInc (){
        activeThreads++;
    }
    public static synchronized void activeThreadsDec (){
        activeThreads--;
    }







}
