package all.business.impl;

import java.net.URL;
import java.util.List;

import model.Article;
import model.Syndication;
import all.business.SyndicationBusiness;
import all.exception.ExtractFeedException;
import all.utility.HttpUtil;
import all.utility.SyndicateUtil;
import all.utility.WebSiteUtil;

import com.sun.syndication.feed.synd.SyndEntry;

public class SyndicationBusinessImpl implements SyndicationBusiness {
	private SyndicateUtil syndicateUtil;
	
	public SyndicationBusinessImpl() {
		syndicateUtil = new SyndicateUtil();
	}	
	
	public Syndication searchSyndication(String url) throws ExtractFeedException {

		Syndication syndication = new Syndication();

		if (!HttpUtil.isValidUrl(url)) {
			throw new ExtractFeedException(ExtractFeedException.Error.BAD_URL);
		}

		String html = null;
		try {
			html = HttpUtil.htmlFromSite(url);
		} catch (Exception e) {
			throw new ExtractFeedException(ExtractFeedException.Error.GET_HTTP_DATA);
		}

		try {
			syndication = setSyndication(html, url);
		} catch (Exception e) {
			throw new ExtractFeedException(ExtractFeedException.Error.GET_FEED_INFO);
		}
		
		return syndication;
	}

	/**
	 * 
	 * @param html
	 * @param url
	 * @return An object syndication otherwise null if no syndication 
	 * information found
	 * @throws Exception
	 */
	private Syndication setSyndication(String html, String url) throws Exception {
		Syndication syndication = new Syndication();

		if (syndicateUtil.isFeed(html)) {
			syndication.setUrl(url);
			syndicateUtil.init(html);
		} else {
			String syndicationUrl = WebSiteUtil.searchSyndicate(html, url);
			if (syndicationUrl == null) {
				return null;
			}
			syndication.setUrl(syndicationUrl);
			syndicateUtil.init(new URL(syndicationUrl));
		}
		
		syndication.setName(syndicateUtil.syndicationName());
		List<SyndEntry> entries = syndicateUtil.lastEntries();
		Article article = null;
		for (SyndEntry entry : entries) {
			article = new Article(entry.getLink(), entry.getPublishedDate(), entry.getTitle());
			syndication.addArticle(article);
		}
		
		return syndication;
	}
	
	public static void main(String[] a) throws Exception {
		SyndicationBusinessImpl bsn = new SyndicationBusinessImpl();
		Syndication s = bsn.searchSyndication("http://linuxfr.org");
		System.err.println(s.getName() + " " + s.getUrl());
	}
}
