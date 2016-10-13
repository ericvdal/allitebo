package allitebooks.ebook.spring.service;

import java.util.List;

public interface CommonService<T> {

	public void cleanDatabase();

	List<T> getAll();

	public boolean containsElement(String id);
	
	public void insertElement(T element);
	
	public void removeElement(T element);
	
	
}
