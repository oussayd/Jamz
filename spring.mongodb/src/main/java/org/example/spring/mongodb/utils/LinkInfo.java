package org.example.spring.mongodb.utils;

public class LinkInfo {
	String LINK;
    String CATEGORIE;
    String PAYS;
    
	public String getPAYS() {
		return PAYS;
	}
	public void setPAYS(String pays) {
		this.PAYS = pays;
	}
	public String getLINK() {
		return LINK;
	}
	public void setLINK(String link) {
		this.LINK = link;
	}
	public String getCATEGORIE() {
		return CATEGORIE;
	}
	public void setCATEGORIE(String categorie) {
		this.CATEGORIE = categorie;
	}
	public LinkInfo(String link, String categorie, String pays) {
		super();
		this.LINK = link;
		this.CATEGORIE = categorie;
		this.PAYS = pays;

	}
	public LinkInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
