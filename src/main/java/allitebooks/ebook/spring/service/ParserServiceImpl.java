package allitebooks.ebook.spring.service;

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
import org.springframework.stereotype.Service;

import allitebooks.ebook.ConfigProperties;
import allitebooks.ebooks.spring.model.Author;
import allitebooks.ebooks.spring.model.Category;
import allitebooks.ebooks.spring.model.EbookDetail;

@Service(value="parserService")
public class ParserServiceImpl implements ParserService{

	private ConfigProperties configProperties;
    
	private EbookService ebookService; 
	
	public ParserServiceImpl(EbookService ebookService, ConfigProperties configProperties){
		this.ebookService = ebookService;
		this.configProperties = configProperties;
	}
	
	@Override
	public List<EbookDetail> loadPage(int page){
		
		String urlPage = configProperties.getUrlPage();
		
		List<EbookDetail> ebookDetailsList = new ArrayList<EbookDetail>();
		
		try {
			Document doc = Jsoup.connect(urlPage + page).get();
			
			Elements articleElements = doc.getElementsByTag("article");
			
			Iterator<Element> articleIterator = articleElements.iterator();
			
			while (articleIterator.hasNext()){
				
				Element article = articleIterator.next();
				
				String id = article.attr("id");
				
				Elements entryBoyElements = article.getElementsByClass("entry-body");
				
				Element entryBoyElement = entryBoyElements.first();
				
				String title = entryBoyElement.getElementsByClass("entry-title").first().text();
				
				
				if (!isTitleSaved(title)){
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
					int pages = 0;
					try {
						pages = Integer.parseInt((String) detailList.get("pages"));
					}catch (NumberFormatException nfe){
						System.err.println(nfe);;
					}
					EbookDetail ebookDetail = new EbookDetail.Builder()
												.setTitle( title)
												.setIsbn((String) detailList.get("isbn"))
												.setPages(pages)
												.setYear((String) detailList.get("year"))
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
					
					ebookDetailsList.add(ebookDetail);
					
				//	save(ebookDetail);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ebookDetailsList;
	}
	
	@Override
	public Integer getTotalPage(){

		Integer nbPage = 0;
		try {
			Document doc = Jsoup.connect("http://www.allitebooks.com/").get();
			
			Elements elementPagination = doc.getElementsByClass("pagination");
			
			Element element = elementPagination.last().children().last();
			
			nbPage = Integer.valueOf(element.text());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nbPage;
	}
	
	@Override
	public Boolean isTitleSaved(String title){
		return ebookService.isEbookTitleExists(title);
	}
	
}
