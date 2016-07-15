package allitebooks.ebook.parse;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	private static int nbPage = 0;
	
	public static void main(String[] args) {

		Parser parser = new Parser();
		
		parser.getTotalPage();
		
		parser.loadPage(1);
		
		System.out.println(nbPage);
		
					
	}

	private Elements loadPage(int page){
		Elements elements = null;
		
		try {
			Document doc = Jsoup.connect("http://www.allitebooks.com/page/" + page).get();
			
			Elements articleElements = doc.getElementsByTag("article");
			
			Iterator<Element> articleIterator = articleElements.iterator();
			
			while (articleIterator.hasNext()){
				
				
				Element article = articleIterator.next();
				
				String id = article.attr("id");
				
				Elements entryBoyElements = article.getElementsByClass("entry-body");
				
				Element entryBoyElement = entryBoyElements.first();
				
				
				
				String title = entryBoyElement.getElementsByClass("entry-title").first().text();
				
				String detailUrl = entryBoyElement.getElementsByClass("entry-title").first().children().first().attr("href");
				
				String resume = entryBoyElement.getElementsByClass("entry-summary").first().text();
				
				Elements entryThumbnailElements = article.getElementsByClass("entry-thumbnail");
				
				String thumbnailSource = entryThumbnailElements.first().children().first().children().attr("src");
				
				Document docEbook = Jsoup.connect(detailUrl).get();
				
				Elements detailElements = docEbook.getElementsByClass(id);
				
				Elements bookDetailElements = detailElements.first().getElementsByClass("book-detail");
				
				Element bookDetailElement  = bookDetailElements.first();
				
				Elements details = bookDetailElement.children().first().children();
				
				Iterator<Element> detailsIterator = details.iterator();
				
				while (detailsIterator.hasNext()){
					Element elDetail = detailsIterator.next();
				}
			}
			

			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elements;
	}
	
	private void getTotalPage(){

		try {
			Document doc = Jsoup.connect("http://www.allitebooks.com/").get();
			
			Elements elementPagination = doc.getElementsByClass("pagination");
			
			Element element = elementPagination.last().children().last();
			
			nbPage = Integer.valueOf(element.text());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
