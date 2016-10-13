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
public class EbookServiceTest {

	private EbookService service;
	
	@Autowired
	public void setEbookService(EbookService service) {
		this.service = service;
	}
	
	@Test
	public void testGetAll(){
		List<EbookDetail> ebookList = service.getAll();
		Assert.assertTrue(ebookList != null );
	}
	
	
	
	
}
