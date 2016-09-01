package org.example.spring.mongodb.utils;

public class UrlInfo {
	String domain;
    String locale;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public UrlInfo(String domain, String locale) {
		super();
		this.domain = domain;
		this.locale = locale;
	}
	public UrlInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
