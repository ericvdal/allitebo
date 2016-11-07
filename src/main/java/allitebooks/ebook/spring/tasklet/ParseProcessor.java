package allitebooks.ebook.spring.tasklet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.io.Files;

import allitebooks.ebook.ConfigProperties;
import allitebooks.ebook.spring.service.EbookServiceImpl;
import allitebooks.ebooks.spring.model.EbookDetail;

@StepScope
@Component
public class ParseProcessor implements ItemProcessor< EbookDetail,  EbookDetail>{


	private static final Logger logger = LogManager.getLogger(EbookServiceImpl.class);
	
	@Autowired
	private ConfigProperties config;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public EbookDetail process(EbookDetail item) throws Exception {
		logger.debug("process " + item.getTitle());
		try {
			savePdf(item);
			return item;
		} catch (IOException ioe) {
			logger.debug(ioe);
		}
		return null;
	}
	
	private byte[] loadPdfFile(String url) {
		logger.debug("loadPdfFile");
		HttpHeaders newHeader = new HttpHeaders();
		newHeader.add(HttpHeaders.REFERER, "http://it-ebooks.info");
		HttpEntity requestEntity = new HttpEntity(newHeader);
		
		ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

		List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
		MediaType pdfApplication = new MediaType("application","pdf");
		supportedApplicationTypes.add(pdfApplication);

		byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(byteArrayHttpMessageConverter);
		restTemplate.setMessageConverters(messageConverters);
		
		ResponseEntity<byte[]> streamFile = restTemplate.exchange(url, HttpMethod.GET, requestEntity, byte[].class);

		return streamFile.getBody();
	}

	private  void savePdf(EbookDetail ebookDetail) throws IOException {
		logger.debug("savePdf");
	
			File ebookFile = new File(config.getLocation()+ebookDetail.getTitle().replace("/", "-").replace("?", "").replace(":", "")+".pdf");
			
			if (!ebookFile.exists() && ebookDetail.getTitle() != null ){
				
				LocalDateTime localDateTimeStart = LocalDateTime.now();
				
				byte[] streamFile = loadPdfFile(ebookDetail.getUrlDownload());
				Files.write(streamFile,ebookFile);
				
				LocalDateTime localDateTimeEnd = LocalDateTime.now();
				
			}
	}

}
