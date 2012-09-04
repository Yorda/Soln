package all.utility;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class SyndicateUtil {

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws FeedException
	 */
	@SuppressWarnings("unchecked")
	public static List<SyndEntry> lastEntries(URL url) throws IOException,
			FeedException {
		SyndFeedInput sfi = new SyndFeedInput();
		return sfi.build(new XmlReader(url)).getEntries();
	}
}
