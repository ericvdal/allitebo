package allitebooks.ebook.spring.tasklet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import allitebooks.ebook.ConfigProperties;
import allitebooks.ebook.spring.service.EbookServiceImpl;
import allitebooks.ebooks.spring.model.EbookDetail;

@StepScope
@Component
public class ParseProcessor implements ItemProcessor< EbookDetail,  EbookDetail>{


	private static final Logger logger = LogManager.getLogger(EbookServiceImpl.class);
	
	@Autowired
	private ConfigProperties config;
	
	@Override
	public EbookDetail process(EbookDetail item) throws Exception {
		String urlBook = item.getUrlDownload();
		String title =  item.getTitle().replaceAll(",", "");
		File file =  new File(config.getLocation().concat("\\").concat(title));
		URL url = new URL(urlBook);
		
		if (urlBook != null) {
			try {
				FileUtils.copyURLToFile(url, file);
			} catch (IOException ioe) {
				logger.debug(ioe);
			}
		}
		return null;
	}

}
