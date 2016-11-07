package allitebooks.ebook.spring.tasklet;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import allitebooks.ebook.ConfigProperties;
import allitebooks.ebook.spring.service.EbookServiceImpl;
import allitebooks.ebook.spring.service.ParserService;
import allitebooks.ebooks.spring.model.EbookDetail;

@StepScope
@Component
public class ParseReader implements ItemReader<EbookDetail>{


	private static final Logger logger = LogManager.getLogger(ParseReader.class);
	
	private ConfigProperties configProperties;
    
	private ParserService parseService; 
	
	
	private List<EbookDetail> ebookDetailList;

	@Value("#{stepExecution}")
	StepExecution stepExecution;
	
	public ParseReader(ParserService parseService, ConfigProperties configProperties){
		this.parseService = parseService;
		this.configProperties = configProperties;
	}
	
	@Override
	public EbookDetail read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		EbookDetail retour = null;
		
		if (ebookDetailListIsNotInitilized()) {
			load();
		}
		
		if (ebookDetailList.size() > 0){
			EbookDetail ebookDetail = ebookDetailList.remove(0);
			logger.debug( " return: " + ebookDetail.getTitle());
			retour =  ebookDetail;
		}
			
		else {
			logger.debug( " return null ");
		}

		
		return retour;

	}
	
	private void load(){
		Integer page = (Integer) stepExecution.getJobExecution().getExecutionContext().get("currentPage");
		while(ebookDetailListIsNotInitilized()) {
			ebookDetailList = parseService.loadPage(page);
			if (ebookDetailListIsNotInitilized() && page < parseService.getTotalPage()) {
				page ++;
			}
		}
		

		stepExecution.getJobExecution().getExecutionContext().putInt("currentPage", page);
		
	}

	private boolean ebookDetailListIsNotInitilized() {
		return this.ebookDetailList== null || this.ebookDetailList.size() == 0;
		
		
		
	}

}
