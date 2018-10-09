package com.aaron;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String folder = "C:\\Users\\leia\\Documents\\commons";
		int numberOfCrawlers = 7;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(folder);
		config.setPolitenessDelay(1000);
		config.setMaxDepthOfCrawling(0);

		PageFetcher fetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, fetcher);
		CrawlController controller = new CrawlController(config, fetcher, robotstxtServer);

		controller.addSeed("http://www.hao6v.com/");

		controller.startNonBlocking(MyCrawler.class, numberOfCrawlers);

		Thread.sleep(10 * 1000);

		System.out.println("休息10秒");

		controller.shutdown();
		System.out.println("停止爬取");

		controller.waitUntilFinish();
	}

}
