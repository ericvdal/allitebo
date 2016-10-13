package allitebooks.ebook.spring.tasklet;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import allitebooks.ebook.spring.service.CommonService;
import allitebooks.ebooks.spring.model.Category;
import allitebooks.ebooks.spring.model.EbookDetail;

@StepScope
@Component
public class ParseWriter implements  ItemWriter<EbookDetail> {

	@Autowired
	CommonService<EbookDetail> ebookService;
	
	@Autowired
	CommonService<Category> catService;
	
	@Override
	public void write(List<? extends EbookDetail> items) throws Exception {
		for(EbookDetail item:items) {
			List<Category> categories = item.getCategories();
			if (categories != null && categories.size() >0) {
				for (Category cat: categories) {
					if (!catService.containsElement(cat.getName())){
						catService.insertElement(cat);
					}
				}
			}
			if (!ebookService.containsElement(item.getIsbn())){
				ebookService.insertElement(item);
			}
		}
		
	}

	

}
