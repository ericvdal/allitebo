package allitebooks.ebook.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import allitebooks.ebook.spring.service.EbookService;
import allitebooks.ebooks.spring.model.Author;
import allitebooks.ebooks.spring.model.Category;
import allitebooks.ebooks.spring.model.EbookDetail;

public class Parser {


    private static String springConfig = "app-context.xml";
	
	
	private EbookService ebookService; 
	
	private static int nbPage = 0;
	
	public static void main(String[] args) {
		
		Parser parser = new Parser();
		
		parser.getTotalPage();
		
		parser.loadPage(1);
		
		System.out.println(nbPage);
		
					
	}

	private Elements loadPage(int page){
		

		ApplicationContext ctx = new ClassPathXmlApplicationContext(springConfig);
		
		ebookService = (EbookService) ctx.getBean("ebookService");
		
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
				
				String thumbnailSource = entryThumbnailElements.first().children().first().children().first().attr("src");
				
				Document docEbook = Jsoup.connect(detailUrl).get();
				
				Elements detailElements = docEbook.getElementsByClass(id);
				
				String  link = detailElements.first().getElementsByClass("download-links").first().children().first().attr("href");
				
				Elements bookDetailElements = detailElements.first().getElementsByClass("book-detail");
				
				
				String description = detailElements.first().getElementsByClass("entry-content").first().children().text().replaceAll("Book Description: ", "");
				
				Element bookDetailElement  = bookDetailElements.first();
				
				Elements details = bookDetailElement.children().first().children();
				
				Iterator<Element> detailsIterator = details.iterator();
				
				Map<String, Object> detailList = new HashMap<String, Object>();
				
				String lastKey = "";
				Object lastValue = null;
				
				while (detailsIterator.hasNext()){
					Element el = detailsIterator.next();
					if (lastKey.isEmpty()){
						lastKey = el.text().replaceAll(":", "").toLowerCase().replace(" ", "");
						lastValue = null;
					} else {
						switch (lastKey) {
						case "author":
							List<Author> authorList = new ArrayList<Author>();
							Elements authElements = el.children();
							Iterator<Element> itAuthor = authElements.iterator();
							while (itAuthor.hasNext()){
								String a = itAuthor.next().text();
								authorList.add(new Author(a));
							}
							detailList.put(lastKey, authorList);
							break;
						case "category":
							List<Category> catList = new ArrayList<Category>();
							Elements catElements = el.children();
							Iterator<Element> itCat = catElements.iterator();
							while (itCat.hasNext()){
								String c = itCat.next().text();
								catList.add(new Category(c));
							}
							detailList.put(lastKey, catList);

							break;
						default:
							lastValue = el.text();
							System.out.println(lastKey + ": "+ lastValue );
							detailList.put(lastKey, lastValue);
							break;
						}
						lastKey = "";
					}
				}
				
				
				EbookDetail ebookDetail = new EbookDetail.Builder()
											.setTitle( title)
											.setIsbn(Long.parseLong((String) detailList.get("isbn")))
											.setPages(Integer.parseInt((String) detailList.get("pages")))
											.setUrlThumbnail(thumbnailSource)
											.setUrlDownload(link)
											.setLocalCopy(null)
											.setFileSize((String) detailList.get("filesize"))
											.setFileFormat((String) detailList.get("fileformat"))
											.setLanguage((String) detailList.get("language"))
											.setDescription(description)
											.setAuthors((List<Author>) detailList.get("author"))
											.setCategories((List<Category>) detailList.get("category"))
											.build();
			
				System.out.println(ebookDetail);
				
				save(ebookDetail);
				
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elements;
	}
	
	private void save(EbookDetail ebookDetail) {
		ebookService.insertEbookDetail(ebookDetail);
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
