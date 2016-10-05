package allitebooks.ebook.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import allitebooks.ebooks.spring.model.Category;
import allitebooks.ebooks.spring.model.EbookDetail;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	private static final String COLLECTION = "category";
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Category> getAll() {
		return mongoTemplate.findAll(Category.class, COLLECTION);
	}

	@Override
	public void insertCategory(Category category) {
		mongoTemplate.insert(category, COLLECTION);
	}
}
