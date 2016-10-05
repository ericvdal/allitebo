package allitebooks.ebook.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import allitebooks.ebooks.spring.model.EbookDetail;

@Repository
public class EbookDAOImpl implements EbookDAO {

	private static final String COLLECTION = "ebook";
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<EbookDetail> getAllEbookDetail() {
		return mongoTemplate.findAll(EbookDetail.class, COLLECTION);
	}

	@Override
	public void insertEbookDetail(EbookDetail ebookDetail) {
		mongoTemplate.insert(ebookDetail, COLLECTION);
	}

}
