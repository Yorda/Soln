package model;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{
	private static final long serialVersionUID = 1015800126769745622L;
	private String url;
	private Date publicationDate;
	private String title;
	
	public Article(String url, Date publicationDate, String title) {
		super();
		this.url = url;
		this.publicationDate = publicationDate;
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
