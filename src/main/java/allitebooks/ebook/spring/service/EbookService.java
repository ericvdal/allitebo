package allitebooks.ebook.spring.service;

import java.util.List;

import allitebooks.ebooks.spring.model.EbookDetail;

public interface EbookService extends CommonService<EbookDetail>{
		
	public boolean isEbookTitleExists(String title);
	
	public boolean saveFileList(List<EbookDetail> ebookDetailList);

	
}
