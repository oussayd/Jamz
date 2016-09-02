package org.example.spring.mongodb.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Customer entity class.
 *
 * @author adam.bialas
 *
 */
@Document(collection = "deals")
public class Deal {

    @Id
    private ObjectId id;
    private String titre;
    private String asin;
    private String categorie;
    private String pays;
    private String url;
    private Number prix;
    private PrixLocaux prixLocaux;
    

    private Integer stock;
    private Float reduction;
    private Float reductionGlobale;
    private String img;
    private Date lastUpdate;


    @Version
    private long version;

    public Deal() {
    }
    

	public Deal(String titre, String asin, String categorie, String pays, String url, Number prix, PrixLocaux prixLocaux,
			Integer stock, Float reduction, Float reductionGlobale, String img, Date lastUpdate, long version) {
		this.titre = titre;
		this.asin = asin;
		this.categorie = categorie;
		this.pays = pays;
		this.url = url;
		this.prix = prix;
		this.prixLocaux = prixLocaux;
		this.stock = stock;
		this.reduction = reduction;
		this.reductionGlobale = reductionGlobale;
		this.img = img;
		this.lastUpdate = lastUpdate;
		this.version = version;
	}


	public Deal(String titre, String asin, String categorie, String pays, String url, Number prix,
			 String img, Date lastUpdate) {
		this.titre = titre;
		this.asin = asin;
		this.categorie = categorie;
		this.pays = pays;
		this.url = url;
		this.prix = prix;
		this.img = img;
		this.lastUpdate = lastUpdate;
		this.prixLocaux = new PrixLocaux();
	}



	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Number getPrix() {
		return prix;
	}

	public void setPrix(Number prix) {
		this.prix = prix;
	}

	public PrixLocaux getPrixLocaux() {
		return prixLocaux;
	}

	public void setPrixLocaux(PrixLocaux prixLocaux) {
		this.prixLocaux = prixLocaux;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Float getReduction() {
		return reduction;
	}

	public void setReduction(Float reduction) {
		this.reduction = reduction;
	}

	public Float getReductionGlobale() {
		return reductionGlobale;
	}

	public void setReductionGlobale(Float reductionGlobale) {
		this.reductionGlobale = reductionGlobale;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	@Override
	public String toString() {
		return "Deal [titre=" + titre.substring(10) + ", asin=" + asin + ", categorie=" + categorie + ", pays=" + pays + ", prix=" + prix + ", prixLocaux=" + prixLocaux + ", stock=" + stock + ", reduction="
				+ reduction + ", reductionGlobale=" + reductionGlobale + "]";
	}



}