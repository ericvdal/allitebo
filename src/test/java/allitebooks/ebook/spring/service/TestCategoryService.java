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
public class TestCategoryService {

	private CategoryService service;
	
	private Category fakeCategory = new Category("fake");
	
	@Autowired
	public void setTestCategoryService(CategoryService service){
		this.service = service;
	}
	
	@Test
	public void testInsert(){
		service.insertCategory(fakeCategory);
		List<Category> categoriesList = service.getAllCategory();
		Assert.assertTrue(categoriesList != null && categoriesList.contains(fakeCategory));
		service.removeCategory(fakeCategory);
		categoriesList = service.getAllCategory();
		Assert.assertTrue(categoriesList != null && !categoriesList.contains(fakeCategory));
	}
	
}
