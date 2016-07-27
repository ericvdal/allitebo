package allitebooks.ebooks.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category {

	 @Id
	 private int id;
	 	 
	 private String name;

	 @SuppressWarnings("unused")
	 private Category(){
		 
	 }
	 
	 public Category(String name){
		 this.name = name;
	 }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	 
}
