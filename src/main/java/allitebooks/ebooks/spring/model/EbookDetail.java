package allitebooks.ebooks.spring.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ebook_detail")
public class EbookDetail {


	@Id
	 private int id;
	 
	 private String title;
	 
	 private String urlThumbnail;

	 private long isbn;

	 private int year;

	 private int pages;
	 
	 private String fileSize;
	 
	 private String fileFormat;
	 
	 private String urlDownload;
	 
	 private String localCopy;
	 
	 private String language;
	 
	 private String description;
	 
	 private List<Author> authors;
	 
	 private List<Category> categories;
	 
	 
	 private EbookDetail(Builder builder) {
		this.title = builder.title;
		this.fileFormat = builder.fileFormat;
		this.fileSize = builder.fileSize;
		this.isbn = builder.isbn;
		this.pages = builder.pages;
		this.urlDownload = builder.urlDownload;
		this.urlThumbnail = builder.urlThumbnail;
		this.year = builder.year;
		this.language = builder.language;
		this.description = builder.description;
		this.authors = builder.authors;
		this.categories = builder.categories;
		this.localCopy = builder.localCopy;
	 }
	 
		public int getId() {
			return id;
		}


		public String getTitle() {
			return title;
		}


		public String getUrlThumbnail() {
			return urlThumbnail;
		}


		public long getIsbn() {
			return isbn;
		}


		public int getYear() {
			return year;
		}


		public int getPages() {
			return pages;
		}


		public String getFileSize() {
			return fileSize;
		}


		public String getFileFormat() {
			return fileFormat;
		}


		public String getUrlDownload() {
			return urlDownload;
		}	 
	 
		public String getLocalCopy(){
			return localCopy;
		}
		
		public String getLanguage() { 
			return language;
		}
	 
		public String getDesription() { 
			return description;
		}
		
		public List<Author> getAuthors(){
			return authors;
		}

		public List<Category> getCategories() {
			return categories;
		}
		
	 public static class Builder {

		 private String title;
		 
		 private String urlThumbnail;

		 private long isbn;

		 private int year;

		 private int pages;
		 
		 private String fileSize;
		 
		 private String fileFormat;
		 
		 private String urlDownload;
		 
		 private String localCopy;
		 
		 private String language;
		 
		 private String description;
		 
		 private List<Author> authors;
		 
		 private List<Category> categories;
		 
		 public Builder() {
			 
		 }
		 
		 public Builder setTitle(String title){
			 this.title = title;
			 return this;
		 }
		 
		 public Builder setUrlThumbnail(String urlThumbnail){
			 this.urlThumbnail = urlThumbnail;
			 return this;
		 }
		 
		 public Builder setIsbn(long isbn){
			 this.isbn = isbn;
			 return this;
		 }
		 
		 public Builder setYear(int year){
			 this.year = year;
			 return this;
		 }
		 
		 public Builder setPages(int pages){
			 this.pages = pages;
			 return this;
		 }
		 
		 public Builder setFileSize(String fileSize){
			 this.fileSize = fileSize;
			 return this;
		 }
		 
		 public Builder setFileFormat(String fileFormat){
			 this.fileFormat = fileFormat;
			 return this;
		 }
		 
		 public Builder setUrlDownload(String urlDownload){
			 this.urlDownload = urlDownload;
			 return this;
		 }
		 
		 public Builder setLocalCopy(String localCopy){
			 this.localCopy = localCopy;
			 return this;
		 }
		 
		 public Builder setLanguage(String language){
			 this.language = language;
			 return this;
		 }
		 
		 public Builder setDescription(String description){
			 this.description = description;
			 return this;
		 }

		 public Builder setAuthors(List<Author> authors){
			 this.authors = authors;
			 return this;
		 }

		 public Builder setCategories(List<Category> categories){
			 this.categories = categories;
			 return this;
		 }
		 
		 public EbookDetail build(){
			 return new EbookDetail(this);
		 }
		 
	 }

	@Override
	public String toString() {
		return "EbookDetail [id=" + id + ", title=" + title + ", urlThumbnail=" + urlThumbnail + ", isbn=" + isbn
				+ ", year=" + year + ", pages=" + pages + ", fileSize=" + fileSize + ", fileFormat=" + fileFormat
				+ ", urlDownload=" + urlDownload + ", language=" + language + "]";
	}



	 
}
