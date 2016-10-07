package allitebooks.ebook.spring.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.io.Files;

import allitebooks.ebook.ConfigProperties;
import allitebooks.ebook.spring.dao.EbookRepository;
import allitebooks.ebooks.spring.model.EbookDetail;

@Service(value="ebookService")
public class EbookServiceImpl extends CommonServiceImpl<EbookDetail> implements EbookService {
	
	private static final Logger logger = LogManager.getLogger(EbookServiceImpl.class);
	
	@Autowired
	private EbookRepository repository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ConfigProperties config;
	
	@Override
	MongoRepository<EbookDetail, String> getRepository() {
		return repository;
	}
	
	@Override
	public boolean isEbookTitleExists(String title) {
		logger.debug("isEbookTitleExists");
		EbookDetail ebook = repository.findByTitle(title);
		if (ebook == null){
			return false;
		}
		return true;
	}

	
	private boolean saveFile(EbookDetail ebookDetail) {
		logger.debug("saveFile");
		if (ebookDetail != null && ebookDetail.getUrlDownload() != null ){
			ebookDetail.setDownloaded(true);
			insertElement(ebookDetail);
			return true;
		}
		return false;
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

	@Override
	public boolean saveFileList(List<EbookDetail> ebookDetailList) {
		logger.debug("saveFileList");
		if (ebookDetailList != null){
			for (EbookDetail ebookDetail:ebookDetailList) {
				if( ebookDetail.getDownloaded() != null) {
					boolean ret = saveFile(ebookDetail);
					try {
						savePdf(ebookDetail);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
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
