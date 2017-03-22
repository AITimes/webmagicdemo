
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * @author zhangtong
 * @version 1.0
 * 一个用爬虫的小例子
 */
public class bxjProcessor implements PageProcessor{
	public static final String URL_LIST = "https://bbs\\.hupu\\.com/bxj-\\w+";
	
	public static final String URL_CONTENT = "https://bbs\\.hupu\\.com/\\w+\\.html";
	
	private Site site = Site.me().setSleepTime(3000).setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
	
	
	
	public void process(Page paramPage) {
		if(paramPage.getUrl().regex(URL_LIST).match()){
			paramPage.addTargetRequests(paramPage.getHtml().xpath("//table[@id='pl']").links().regex(URL_CONTENT).all());
			paramPage.addTargetRequests(paramPage.getHtml().links().regex(URL_LIST).all());
		}else{
			//http://bbs.hupu.com/18771993.html
			System.out.println("title :"+paramPage.getHtml().xpath("//h1[@id='j_data']/text()").toString());
			System.out.println("author :"+paramPage.getHtml().xpath("//div[@id='tpc']//div[@class='author']//a[@class=u]/text()").toString());
			System.out.println("publishDate :"+paramPage.getHtml().xpath("//div[@id='tpc']//div[@class='author']//span[@class='stime']/text()").toString());
     		System.out.println("context2 :"+paramPage.getHtml().xpath("//div[@id='tpc']//div[@class='quote-content']/tidyText()").all());
			System.out.println("imaheUrl :"+paramPage.getHtml().xpath("//div[@id='tpc']//div[@class='quote-content']/p/img/@src").all());
			System.out.println("context2 :"+paramPage.getHtml().xpath("//div[@id='tpc']//div[@class='quote-content']/tidyText()").all());

		}
		
	}

	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) {
		Spider.create(new bxjProcessor()).addUrl("https://bbs.hupu.com/18786270.html").run();
	}
}
