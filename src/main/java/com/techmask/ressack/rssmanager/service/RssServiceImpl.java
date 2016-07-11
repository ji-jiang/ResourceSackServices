package com.techmask.ressack.rssmanager.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.feed.synd.SyndImageImpl;
import com.rometools.rome.io.SyndFeedOutput;
import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.log.LogUtils;
import com.techmask.ressack.resourcemanager.busobjs.ResourceCategory;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.service.ResourceService;

@Service
public class RssServiceImpl implements RssService {

	@Autowired
	private AppConfiguration appConfiguration;

	@Autowired
	private ResourceService resourceService;

	@Override
	@Scheduled(cron = "0 0/1 * * * ?")
	public void processRssFeeds() {
		// for all
		prepareRssFeeds(null);
		prepareRssFeeds(ResourceCategory.DESIGN);
		prepareRssFeeds(ResourceCategory.DEVELOPMENT);
		prepareRssFeeds(ResourceCategory.MOBILE);
		prepareRssFeeds(ResourceCategory.TOOLS);
	}

	protected void prepareRssFeeds(ResourceCategory resourceCategory) {
		Map<String, Object> requestMap = new HashMap<>();

		if (resourceCategory != null) {
			requestMap.put("category", resourceCategory.name());
		}

		List<Resource> resources = resourceService.loadAllResourceForRssFeed(requestMap);
		String categoryCNDesc = (resourceCategory == null ? "":resourceCategory.getCNDesc());
 
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("技匠社-" +categoryCNDesc+ "资源订阅RSS");
		feed.setDescription("来自技匠社社员分享的优秀" + categoryCNDesc + "资源。");
		feed.setLink("http://rss.jijiangshe.com/" +( resourceCategory == null ? ""
				: (resourceCategory.name().toLowerCase() + "-")) + "resources.xml");
		feed.setEncoding("UTF-8");
		feed.setPublishedDate(new Date());

		List<SyndEntry> entries = new ArrayList<>();
		for (int i = 0; i < resources.size(); i++) {
			Resource resource = resources.get(i);

			SyndEntry entry = new SyndEntryImpl();
			entry.setAuthor(resource.getOwnerName());
			entry.setTitle(resource.getTitle());
			String resourceDetailUrl = appConfiguration.getHostUrl() + "/resource/" + resource.getId();
			entry.setUri(resourceDetailUrl);
			entry.setLink(resourceDetailUrl);
			entry.setPublishedDate(resource.getCreatedDate());

			String imageUrl = resource.getImageSmUrl();

			SyndContent content = new SyndContentImpl();
			content.setType("text/html");
			String contentHtml = "<img src=\"" + imageUrl + "\">\n";
			contentHtml += "<p>" + resource.getDesc() + "</p>";
			content.setValue(contentHtml);
			entry.setDescription(content);

			entries.add(entry);
		}

		feed.setEntries(entries);
		SyndFeedOutput out = new SyndFeedOutput();
		try {
			String rssFileName = appConfiguration.getRssFileUploadPath();
			if (resourceCategory != null) {
				rssFileName += resourceCategory.name().toLowerCase() + "-";
			}
			rssFileName += "resources.xml";
			File rssFile = new File(rssFileName);

			if (!rssFile.exists()) {
				rssFile.createNewFile();
			}
			out.output(feed, rssFile, true);
		} catch (Exception e) {
			LogUtils.error("error processing rss feeds", e);
		}
	}

}
