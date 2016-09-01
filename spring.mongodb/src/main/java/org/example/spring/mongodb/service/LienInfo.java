package org.example.spring.mongodb.service;

public class LienInfo {
	String url;
	String categorie;
	
	public LienInfo(){
		
	}
	public LienInfo(String url, String categorie) {
		super();
		this.url = url;
		this.categorie = categorie;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	@Override
	public String toString() {
		return "LienInfo [url=" + url + ", categorie=" + categorie + "]";
	} 
	

}
