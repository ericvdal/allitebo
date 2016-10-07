package allitebooks.ebook.spring.service;

import java.util.List;

public interface CommonService<T> {

	public void cleanDatabase();

	List<T> getAll();
	
	public void insertElement(T elements);
	
	public void removeElement(T elements);
	
}
