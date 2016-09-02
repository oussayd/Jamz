package org.example.spring.mongodb.entity;

public class PrixLocaux {

	
    private Float it;
    private Float fr;
    private Float de;
    private Float couk;
	public Float getIt() {
		return it;
	}
	public void setIt(Float it) {
		this.it = it;
	}
	public Float getFr() {
		return fr;
	}
	public void setFr(Float fr) {
		this.fr = fr;
	}
	public Float getDe() {
		return de;
	}
	public void setDe(Float de) {
		this.de = de;
	}
	public Float getCouk() {
		return couk;
	}
	public void setCouk(Float couk) {
		this.couk = couk;
	}
	@Override
	public String toString() {
		return "PrixLocaux [it=" + it + ", fr=" + fr + ", de=" + de + ", couk=" + couk + "]";
	}
	public PrixLocaux() {
		this.it = -1f;
		this.de = -1f;
		this.couk = -1f;
		this.fr = -1f;
	}
	public PrixLocaux(Float it, Float fr, Float de, Float couk) {
		this.it = it;
		this.fr = fr;
		this.de = de;
		this.couk = couk;
	}
	

}
