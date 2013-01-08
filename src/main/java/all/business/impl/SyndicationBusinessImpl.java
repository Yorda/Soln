package all.business.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Publication;
import model.Syndication;
import all.business.SyndicationBusiness;
import all.exception.ExtractFeedException;
import all.utility.HttpUtil;
import all.utility.SyndicateUtil;
import all.utility.WebSiteUtil;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;

public class SyndicationBusinessImpl implements SyndicationBusiness {
	private SyndicateUtil syndicateUtil;
	
	public SyndicationBusinessImpl() {
		syndicateUtil = new SyndicateUtil();
	}	
	
	
	public List<Publication> getLastPublications(String rssUrl) throws ExtractFeedException {
		List<Publication> publications = new ArrayList<Publication>();
		
		if (!HttpUtil.isValidUrl(rssUrl)) {
			throw new ExtractFeedException(ExtractFeedException.Error.BAD_URL);
		}

		String html = null;
		try {
			html = HttpUtil.htmlFromSite(rssUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExtractFeedException(ExtractFeedException.Error.GET_HTTP_DATA);
		}
		
		try {
			syndicateUtil.init(html);
			List<SyndEntry> entries = syndicateUtil.lastEntries();
			Publication publication = null;
			String description;
			for (SyndEntry entry : entries) {
				description = entry.getDescription() != null 
						? entry.getDescription().getValue() : null;
				publication = new Publication(entry.getLink(),
						entry.getPublishedDate(), entry.getTitle(), description);
				publications.add(publication);
			}
		} catch (Exception e) {
			throw new ExtractFeedException(ExtractFeedException.Error.GET_FEED_INFO);
		}		
		return publications;

	}
	
	public Syndication searchSyndication(String url) throws ExtractFeedException {

		Syndication syndication = new Syndication();
		syndication.setWebsiteUrl(url);
		
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
		Publication publication = null;
		String description;
		for (SyndEntry entry : entries) {
			description = entry.getDescription() != null 
					? entry.getDescription().getValue() : null;
			publication = new Publication(entry.getLink(), entry.getPublishedDate(), entry.getTitle(), description);
			syndication.addPublication(publication);
		}
		
		return syndication;
	}
	
	public static void main(String[] a) throws Exception {
		SyndicationBusinessImpl bsn = new SyndicationBusinessImpl();
		Syndication s = bsn.searchSyndication("http://linuxfr.org");
		System.err.println(s.getName() + " " + s.getUrl());
	}
}
