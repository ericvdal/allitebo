package allitebooks.ebook.spring.hibernate.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name = "AUTHOR")
public class Author {

	 @Id
	 @Column(name = "AUTHOR_ID")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Column(name = "NAME")
	 private String name;
	
}
