
public class CrawlerTask implements Runnable{

    private URLDepthPair urlDepthPair;

    public CrawlerTask (URLDepthPair urlDepthPair){
        this.urlDepthPair = urlDepthPair;
    }

    // [Runnable] interface
    @Override
    public void run() {
        Crawl.crawlThroughURL(urlDepthPair); // [ИДЕЯ] засунуть этот метод в try catch чтобы потоки никогда не обрывались от ошибок этого метода
        Crawl.activeThreadsDec();
    }

}
