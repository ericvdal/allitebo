package allitebooks.ebook.spring.service;

import java.util.List;

import allitebooks.ebooks.spring.model.Category;

public interface CategoryService {
	
	public void insertCategory(Category category);
	
	public void removeCategory(Category category);

	public List<Category> getAllCategory();
	
}
