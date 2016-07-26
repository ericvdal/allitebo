package allitebooks.ebooks.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "author")
public class Author {

	@SuppressWarnings("unused")
	private Author(){
		
	}
	
	public Author(String name) { 
		this.name = name;
	}
	
	 @Id
	 private int id;
	 	 
	 private String name;


	
}
