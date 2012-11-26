package all.utility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;

public class SyndicateUtilTest {

	@Test
	public void testLastEntries() {

		URL url = null;
		try {
			url = new URL("http://www.pcinpact.com/rss/news.xml");
			url = new URL("http://www.gizmodo.fr/feed");
		} catch (MalformedURLException mue) {
			fail("Bad syndication url");
		}

		List<SyndEntry> entries = null;
		try {
			entries = SyndicateUtil.lastEntries(url);
		} catch (IOException e) {
			fail("Unable to get feeds");
		} catch (FeedException e) {
			fail("Bad feeds returned by site");
		}

		assertNotNull(entries);
	}

}
