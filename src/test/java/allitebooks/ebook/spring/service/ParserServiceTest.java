package allitebooks.ebook.spring.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import allitebooks.ebooks.spring.model.EbookDetail;

@ContextConfiguration(locations={"classpath:app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ParserServiceTest {

	@Autowired
	ParserService parser;
	
	@Test
	public void testEquals(){
		Integer nbPages = parser.getTotalPage();
		Assert.assertTrue(nbPages != null && nbPages.intValue() > 0);
	}
	
	@Test
	public void testIsTitleSaved(){
		Boolean isSaved = parser.isTitleSaved("fake title");
		Assert.assertTrue(isSaved != null && !isSaved.booleanValue());
	}
	
	@Test
	public void testLoadPage(){
		List<EbookDetail> ebookDetail = parser.loadPage(1);
		Assert.assertTrue(ebookDetail != null && !ebookDetail.isEmpty());
	}
	
}
