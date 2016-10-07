package allitebooks.ebook.spring.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import allitebooks.ebook.ConfigProperties;
import allitebooks.ebook.spring.service.EbookService;
import allitebooks.ebook.spring.service.ParserService;
import allitebooks.ebooks.spring.model.EbookDetail;

public class ParseReader implements ItemReader<EbookDetail>{

	private ConfigProperties configProperties;
    
	private ParserService parseService; 
	
	private int page;
	
	private List<EbookDetail> ebookDetailList;
	
	public ParseReader(ParserService ebookService, ConfigProperties configProperties){
		this.parseService = parseService;
		this.configProperties = configProperties;
		page = 0;
	}
	
	@Override
	public EbookDetail read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (ebookDetailListIsNotInitilized()){
			ebookDetailList = parseService.loadPage(page);
		}
		return null;
	}

	private boolean ebookDetailListIsNotInitilized() {
		return this.ebookDetailList== null;
	}

}
