
package allitebooks.ebook.spring.service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public abstract class CommonServiceImpl<T> implements CommonService<T> {

	abstract MongoRepository<T, String> getRepository(); 
	
	@Override
	public void cleanDatabase() {
		getRepository().deleteAll();
	}

	@Override
	public List<T> getAll() { 
		return getRepository().findAll();
	}
	
	public void insertElement(T element) {
		getRepository().insert(element);
	}
	
	public void removeElement(T element) {
		getRepository().delete(element);
	}
	
}
