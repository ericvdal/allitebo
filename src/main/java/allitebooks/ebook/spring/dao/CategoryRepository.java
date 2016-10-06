package allitebooks.ebook.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import allitebooks.ebooks.spring.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
	
    public Category findByName(String name);
    
}
