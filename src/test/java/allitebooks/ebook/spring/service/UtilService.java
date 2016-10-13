package allitebooks.ebook.spring.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import allitebooks.ebooks.spring.model.Category;

@ContextConfiguration(locations={"classpath:app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilService {

	private CategoryService catService;
	
	private EbookService ebookService;
	
	@Autowired
	public void setEbookService(EbookService ebookService) {
		this.ebookService = ebookService;
	}
	
	
	@Autowired
	public void setTestCategoryService(CategoryService catService){
		this.catService = catService;
	}
	/*
	@Test
	public void deleteAll(){
		ebookService.cleanDatabase();
		catService.cleanDatabase();
	}
	*/
}
