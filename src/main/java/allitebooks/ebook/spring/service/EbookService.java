package allitebooks.ebook.spring.hibernate.service;

import java.util.List;

import allitebooks.ebook.spring.hibernate.model.EbookDetail;

public interface EbookService {

	public List<EbookDetail> getAllEbookDetail();
	
	public void insertEbookDetail(EbookDetail ebookDetail);
	
}
