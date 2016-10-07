package allitebooks.ebook.spring.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import allitebooks.ebooks.spring.model.Category;
import allitebooks.ebooks.spring.model.EbookDetail;

@ContextConfiguration(locations={"classpath:app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCategoryService {

	private CategoryService service;
	
	private Category fakeCategory = new Category("fake");
	
	@Autowired
	public void setTestCategoryService(CategoryService service){
		this.service = service;
	}
	
	@Test
	public void testInsert(){
		service.insertElement(fakeCategory);
		List<Category> categoriesList = service.getAll();
		Assert.assertTrue(categoriesList != null && categoriesList.contains(fakeCategory));
		service.removeElement(fakeCategory);
		categoriesList = service.getAll();
		Assert.assertTrue(categoriesList != null && !categoriesList.contains(fakeCategory));
	}
	
	
	@Test
	public void testGetAll(){
		List<Category> ebookList = service.getAll();
		Assert.assertTrue(ebookList != null );
	}
}
