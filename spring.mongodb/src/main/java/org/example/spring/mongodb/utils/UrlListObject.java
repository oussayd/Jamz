package org.example.spring.mongodb.utils;

import java.util.Map;

public class UrlListObject {
	
	private Map<String,Map<String,LinkInfo>> urlList;

	public UrlListObject(Map<String, Map<String, LinkInfo>> urlList) {
		super();
		this.urlList = urlList;
	}

	public Map<String, Map<String, LinkInfo>> getUrlList() {
		return urlList;
	}

	public void setUrlList(Map<String, Map<String, LinkInfo>> urlList) {
		this.urlList = urlList;
	}

	@Override
	public String toString() {
		return "UrlListObject [urlList=" + urlList.size() + "]";
	}
	
	
	

	
}
