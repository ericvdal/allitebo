package allitebooks.ebook.spring.service;

import java.util.List;

import allitebooks.ebooks.spring.model.EbookDetail;

public interface EbookService {

	public List<EbookDetail> getAllEbookDetail();
	
	public void insertEbookDetail(EbookDetail ebookDetail);
	
	public boolean isEbookTitleExists(String title);
	
	public boolean saveFile(EbookDetail ebookDetail);
	
	public byte[] loadPdfFile(String url);
	
	public String getFileLocation();
}
