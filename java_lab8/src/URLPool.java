import java.util.LinkedList;

/**
 * Потокобезопасный класс для работы с двумя LinkedList (checkedURL и uncheckedURL) класса URLDepthPair
 * до определённой глубины
 * Note: реализованы только методы, необходимые для 8-ой лабораторной
 */
public class URLPool {

    private LinkedList<URLDepthPair> checkedURL;
    private LinkedList<URLDepthPair> uncheckedURL;
    public int MAXDepth = 0;

    /**
     * Инициализирует две пустых коллекции.
     * @param i Максимальная глубина поиска
     */
    public URLPool (int i){
        checkedURL = new LinkedList<>();
        uncheckedURL = new LinkedList<>();
        MAXDepth = i;
    }

    public synchronized void addToUnchecked (URLDepthPair urlDepthPair){
        if (urlDepthPair.getDepth() >= MAXDepth) checkedURL.add(urlDepthPair);
        else uncheckedURL.add(urlDepthPair);
    }

    /**
     * Добавляет url в коллекцию проверенных url и убирает из коллекции непроверенных url
     * @param urlDepthPair
     */
    public synchronized void addToChecked (URLDepthPair urlDepthPair){
        checkedURL.add(urlDepthPair);
        uncheckedURL.remove(urlDepthPair);
    }

    public synchronized boolean uncheckedIsEmpty (){
        return uncheckedURL.isEmpty();
    }

    public synchronized URLDepthPair getUncheckedURL (int i){
        return uncheckedURL.get(i);
    }
    public synchronized void removeUncheckedURL (URLDepthPair urlDepthPair){
        uncheckedURL.remove(urlDepthPair);
    }




    public synchronized boolean poolContains (URLDepthPair urlDepthPair) {

        if (checkedURL.isEmpty() && uncheckedURL.isEmpty()) return false;

        for (URLDepthPair ur: checkedURL) {
            if (ur.getURLAddress().equals(urlDepthPair.getURLAddress())) return true;
        }
        for (URLDepthPair ur: uncheckedURL) {
            if (ur.getURLAddress().equals(urlDepthPair.getURLAddress())) return true;
        }
        return false;
    }

    public synchronized void printAllUnchecked (){
        if (uncheckedURL.isEmpty()) System.out.println("Empty");
        for (URLDepthPair urlDepthPair : uncheckedURL) {
            System.out.println(urlDepthPair.getStringFormat());
        }
    }
    public synchronized void printAllChecked (){
        if (checkedURL.isEmpty()) System.out.println("Empty");
        for (URLDepthPair urlDepthPair : checkedURL) {
            System.out.println(urlDepthPair.getStringFormat());
        }
    }
}
