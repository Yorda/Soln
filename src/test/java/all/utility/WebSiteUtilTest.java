package all.utility;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class WebSiteUtilTest {

	
	@Test
	public void testSearchSyndicate() {
		String syndicate = null;
		
		try {
			syndicate = WebSiteUtil.searchSyndicate("http://linuxfr.org/");
		} catch (IOException ioe) {
			fail("Unable to connect to web site");
		}
		assertEquals("http://linuxfr.org/news.atom", syndicate);
		
		try {
			syndicate = WebSiteUtil.searchSyndicate("http://mediabenews.wordpress.com");
		} catch (IOException ioe) {
			fail("Unable to connect to web site");
		}
		assertEquals("http://mediabenews.wordpress.com/feed/", syndicate);
	}

	public static void main(String[] a){
	}
}
