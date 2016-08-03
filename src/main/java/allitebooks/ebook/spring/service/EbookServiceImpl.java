package allitebooks.ebook.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import allitebooks.ebook.spring.dao.EbookDAO;
import allitebooks.ebook.spring.dao.EbookRepository;
import allitebooks.ebooks.spring.model.EbookDetail;

@Service(value="ebookService")
public class EbookServiceImpl implements EbookService {

	@Value("${ebook.location}")
	public String fileLocation;
	
	@Autowired
	private EbookDAO ebookDao;
	
	@Autowired
	private EbookRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<EbookDetail> getAllEbookDetail() {
		List<EbookDetail> ebookDetails = repository.findAll();
		return ebookDetails;
	}

	@Override
	public void insertEbookDetail(EbookDetail ebookDetail) {
		EbookDetail ebook = repository.findByIsbn(ebookDetail.getIsbn());
		if (ebook == null){
			repository.save(ebookDetail);
		}
	}
	

	@Override
	public boolean isEbookTitleExists(String title) {
		EbookDetail ebook = repository.findByTitle(title);
		if (ebook == null){
			return false;
		}
		return true;
	}

	@Override
	public boolean saveFile(EbookDetail ebookDetail) {
		if (ebookDetail != null && ebookDetail.getUrlDownload() != null ){
			
			
			return true;
		}
		return false;
	}

	@Override
	public byte[] loadPdfFile(String url) {
		HttpHeaders newHeader = new HttpHeaders();
		newHeader.add(HttpHeaders.REFERER, "http://it-ebooks.info");
		HttpEntity requestEntity =new HttpEntity(newHeader);
		
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
	public String getFileLocation() {
		return fileLocation;
	}

}
