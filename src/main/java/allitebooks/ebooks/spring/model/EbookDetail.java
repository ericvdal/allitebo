package allitebooks.ebooks.spring.model;

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
	 
	 private String language;
	 
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
	 
		public String getLanguage() { 
			return language;
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
		 
		 private String language;
		 
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
		 
		 public Builder setLanguage(String language){
			 this.language = language;
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
