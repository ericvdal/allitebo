package allitebooks.ebooks.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "author")
public class Author {
	
	@Id
	private String name;

	@SuppressWarnings("unused")
	private Author(){
		
	}
	
	public Author(String name) { 
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Author [name=" + name + "]";
	}
	
}
