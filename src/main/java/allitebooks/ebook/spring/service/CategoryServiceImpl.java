package allitebooks.ebook.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import allitebooks.ebook.spring.dao.CategoryRepository;
import allitebooks.ebooks.spring.model.Category;

@Service(value="categoryService")
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;
	
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Category> getAllCategory() {
		return repository.findAll();
	}

	@Override
	public void insertCategory(Category category) {
		repository.insert(category);
	}

	@Override
	public void removeCategory(Category category) {
		repository.delete(category);
		
	}

	

}
