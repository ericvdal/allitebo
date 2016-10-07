package allitebooks.ebook.spring.service;

import java.util.List;

import allitebooks.ebooks.spring.model.EbookDetail;

public interface ParserService {

	List<EbookDetail> loadPage(int page);

	Integer getTotalPage();

	Boolean isTitleSaved(String string);

}
