package allitebooks.ebook.spring.dao;

import java.util.List;

import allitebooks.ebooks.spring.model.Category;

public interface CategoryDao {

	public List<Category> getAll();

	void insertCategory(Category category);
	
}
