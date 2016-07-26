package allitebooks.ebook.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import allitebooks.ebooks.spring.model.EbookDetail;

@Component
public class EbookDAOImpl implements EbookDAO {

	private static final String COLLECTION = "ebook";
	
	@Autowired
	 MongoTemplate mongoTemplate;
	
	@Override
	public List<EbookDetail> getAllEbookDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEbookDetail(EbookDetail ebookDetail) {
		// TODO Auto-generated method stub

	}

}
