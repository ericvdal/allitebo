package allitebooks.ebook.spring.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import allitebooks.ebook.spring.dao.CategoryRepository;
import allitebooks.ebooks.spring.model.Category;

@Service(value="categoryService")
public class CategoryServiceImpl extends CommonServiceImpl<Category> implements CategoryService {

	private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);
	
	private CategoryRepository repository;
	
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}
	
	@Override
	MongoRepository<Category, String> getRepository() {
		return repository;
	}
	





}
