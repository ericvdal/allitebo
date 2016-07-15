package allitebooks.ebook.spring.hibernate.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@DynamicUpdate
@Table(name = "EBOOK_DETAIL")
public class EbookDetail {

	 @Id
	 @Column(name = "ID")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Column(name = "TITLE")
	 private String title;
	 
	 @Column(name = "URL_THUMBNAIL")
	 private String urlThumbnail;
	 /*
	 @ManyToOne
	 @JoinColumn(name="AUTHOR_ID")
	 private Author author;
	 */
	 @Column(name = "ISBN")
	 private Long isbn;

	 @Column(name = "YEAR")
	 private Integer year;

	 @Column(name = "PAGES")
	 private Integer pages;
	 
	 @Column(name = "FILE_SIZE")
	 private long fileSize;
	 
	 @Column(name = "FILE_FORMAT")
	 private long fileFormat;
}
