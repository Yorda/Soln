package all.utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.XmlReader;

public class RssTests {

	// http://feeds2.feedburner.com/LeJournalduGeek
	// http://linuxfr.org/news.atom
	// http://tantek.com/XHTML/Test/minimalxhtmlw3cdtd.html

	@SuppressWarnings("unchecked")
	public static void main(String[] a) {

		SyndFeedInput sfi = new SyndFeedInput();

		URL url = null;
		XmlReader reader = null;
		try {
			url = new URL("http://linuxfr.org/news.atom");
		} catch (MalformedURLException e) {
			System.out.println(e);
		}

		try {
			reader = new XmlReader(url);
		} catch (IOException e) {
			System.out.println(e);
		}

		SyndFeed feed = null;
		try {
			feed = sfi.build(reader);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (FeedException e) {
			System.out.println(e);
		}

		if (feed == null) {
			return;
		}
		
		//System.err.println(feed.getTitle());

		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		entries = feed.getEntries();

		for (SyndEntry entry : entries) {
		
			System.out.println(entry.getTitle() 
					+ " - " + entry.getLink()
					+ " - " + entry.getUpdatedDate() 
					+"\n");
		}
	}
}
