package all.business.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import model.Syndication;

import org.junit.Test;

import all.business.SyndicationBusiness;
import all.exception.ExtractFeedException;

public class SyndicationBusinessImplTest {


	@Test
	public void searchSyndication() {

		SyndicationBusiness bsn = new SyndicationBusinessImpl();

		Syndication syndic = null;

		// Test with url
		try {
			syndic = bsn.searchSyndication("http://linuxfr.org");
		} catch (ExtractFeedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Test with syndication atom
		try {
			syndic = bsn.searchSyndication("http://linuxfr.org/news.atom");
		} catch (ExtractFeedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Test with syndication rss
		try {
			syndic = bsn.searchSyndication("http://www.pcinpact.com/rss/news.xml");
		} catch (ExtractFeedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		assertNotNull(syndic.getUrl());
		
	}

}
