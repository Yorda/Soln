package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Syndication implements Serializable {
	private static final long serialVersionUID = -5995704417797241060L;
	private String name;
	private String url;
	private List<Article> articles;

	public Syndication() {
		setArticles(new ArrayList<Article>());
	}

	public void addArticle(Article article) {
		articles.add(article);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
