package all.business;

import java.util.List;

import model.Article;
import model.Syndication;
import all.exception.ExtractFeedException;

public interface SyndicationBusiness {

	/**
	 * Try to get the url for RSS feed in a web page by the url given in
	 * parameter.
	 * 
	 * @param url
	 * @return
	 * @throws ExtractFeedException
	 */
	public Syndication searchSyndication(String url) throws ExtractFeedException;
	
	/**
	 * Get a list of last articles published for a syndication
	 * 
	 * @param url
	 * @return
	 */
	public List<Article> getLastPublications(String url) throws ExtractFeedException ;

}
