package allitebooks.ebook.spring.dao;

import java.util.List;

import allitebooks.ebooks.spring.model.EbookDetail;

public interface EbookDAO {

	public List<EbookDetail> getAllEbookDetail();
	
	public void insertEbookDetail(EbookDetail ebookDetail);
	

	
}
