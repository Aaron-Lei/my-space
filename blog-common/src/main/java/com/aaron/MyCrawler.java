package com.aaron;

import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {

	private static final Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|jpg|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		// TODO Auto-generated method stub
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.startsWith("http://www.hao6v.com/");
	}

	@Override
	public void visit(Page page) {
		// TODO Auto-generated method stub
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			String text = htmlParseData.getText();
			String title = htmlParseData.getTitle();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

			System.out.println("Title: " + title);
			System.out.println("Text: " + text);
			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
			links.forEach(t -> {
				System.out.println(t.getURL());
			});

			System.out.println("========================================");
		}
	}
}
