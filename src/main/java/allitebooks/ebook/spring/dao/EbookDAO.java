package allitebooks.ebook.spring.hibernate.dao;

import java.util.List;

import allitebooks.ebook.spring.hibernate.model.EbookDetail;

public interface EbookDAO {

	public List<EbookDetail> getAllEbookDetail();
	
	public void insertEbookDetail(EbookDetail ebookDetail);
	
}
