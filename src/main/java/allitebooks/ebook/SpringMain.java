package allitebooks.ebook;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import allitebooks.ebook.spring.service.EbookService;
import allitebooks.ebooks.spring.model.EbookDetail;

public class SpringMain {

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		
		EbookService ebookService = context.getBean("ebookServiceImpl", EbookService.class); 
		
		List<EbookDetail> ebookDetailList = ebookService.getAllEbookDetail();
		
		context.getBeanDefinitionCount();
	}
	
}
