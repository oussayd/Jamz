package org.example.spring.mongodb.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.example.spring.mongodb.dao.DealDao;
import org.example.spring.mongodb.entity.Deal;
import org.example.spring.mongodb.utils.UrlInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinderService {

	@Autowired
	DealDao dealDao;

	private static final int MAX_PAGE = 2;// 401;
	int TIMEOUT = 5000;
	int prixLimite = 150;
	Boolean compareLocalPrices = false;
	String articleUrlTemplate = "http://www.amazon.{0}/dp/{1}";
	String baseUrlTemplate = "http://www.amazon.{0}/gp/offer-listing/";
	// String searchList = NOUVEAU.FLASH;
	// String searchList = RECONDITIONNE.TOP;
	// String searchList = RECONDITIONNE.BARRES_TOIT;
	// String searchList = RECONDITIONNE.ONDULEUR;

	// String searchList = RECONDITIONNE.SKI;
	String searchList = "RECONDITIONNE.PS4";
	// String searchList = RECONDITIONNE.COFFRE;

	// String searchList = RECONDITIONNE.RAPIDE;
	// String searchList = RECONDITIONNE.IT;
	// String searchList = NOUVEAU.SOLDES;

	// String searchList = NOUVEAU.FR;

	int numeroDeal = 0;

	String lienInfo;
	// String lienInfo = LIENS.WAREHOUSE.FR.INFORMATIQUE;
	String dealsUrl = "http://www.amazon.fr/s/ref=sr_pg_{0}?fst=as%3Aoff&rh=n%3A8873224031%2Cn%3A206617031&page={0}&bbn=8873224031&sort=price-desc-rank";
	Boolean lastPage = false;
	int indexLien = 0;
	List<String> links = new ArrayList<>();
	String _categorie = "";
	int pageIndex;

	public void recherchePrix(int indexL) {

		// lienInfo = links.get(indexL);

		// String dealsUrlTemlate = compile(lienInfo.LINK);
		//
		// String baseUrl = commun.baseUrlTemplate(urlInfo.locale);
		String dealsUrlTemlate = dealsUrl;
		UrlInfo urlInfo = Utils.getUrlInfos(MessageFormat.format(dealsUrlTemlate, new Object[] { 1 }));
		LienInfo lienInfo = new LienInfo(dealsUrlTemlate, "BEBE");
		String baseUrl = MessageFormat.format(baseUrlTemplate, new Object[] { urlInfo.getLocale() });
		System.out.println(urlInfo);
		pageIndex = 1;

		searchLoop(pageIndex, urlInfo, baseUrl, lienInfo, dealsUrlTemlate);

	};

	public void searchLoop(int pageIndex, UrlInfo urlInfo, String baseUrl, LienInfo lienInfo, String dealsUrlTemlate) {
		System.out.println("+++++++++++++++++++++++ Page " + pageIndex + " +++++++++++++++++++++++");

		String url = MessageFormat.format(dealsUrlTemlate, new Object[] { pageIndex });// dealsUrlTemlate(pageIndex);
		System.out.println(url);
		Document doc;
		try {
			//TODO Random userAgents List from array
			doc = Jsoup.connect(url).timeout(30000).userAgent("Mozilla/17.0").get();
			;

			System.out.println(doc.title());
			Elements deals = doc.select("li[id^='result']");
			for (Element dealInfo : deals) {
				// get the value from the href attribute
				String asin = dealInfo.attr("data-asin");
				String titre = dealInfo.select("h2").text();
				String imgUrl = dealInfo.select("img").attr("src");
				String prixString = dealInfo.select(".a-color-price").text();
				String pays = urlInfo.getLocale();
				String categorie = lienInfo.getCategorie();
				String dealUrl = baseUrl + asin + "/";
				Locale locale = Locale.FRANCE;
				prixString = prixString.replaceAll("[^0-9.,]", "");

				NumberFormat numberFormat = NumberFormat.getInstance(locale);
				numberFormat.setMaximumFractionDigits(2);

				Number prix = -1;

				try {
					prix = numberFormat.parse(prixString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date lastUpdate = Calendar.getInstance().getTime();

				Deal deal = new Deal(titre, asin, categorie, pays, dealUrl, prix, imgUrl, lastUpdate);
				dealDao.save(deal);
				System.out.println(deal);

			}
			// System.out.println("[" + lienInfo.CATEGORIE + "] Page URL : " +
			// url);
			// scrapPricesFromPage(url, urlInfo, baseUrl, lienInfo);
			// setTimeout(function () {
			pageIndex++;
			if (!lastPage && pageIndex < MAX_PAGE) {
				searchLoop(pageIndex, urlInfo, baseUrl, lienInfo, dealsUrlTemlate);
			} else {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!! LastPage " + pageIndex + " !!!!!!!!!!!!!!!!!!!!!!");
				indexLien++;

				if (indexLien < links.size()) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lastPage = false;
					// System.out.println("//////////////////////////// Passage
					// à la
					// categorie " + links.get(indexLien).CATEGORIE + "
					// ////////////////////////////");

					recherchePrix(indexLien);
				} else {
					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");

					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("////////////////////// ////// THE END ////////////////////////////");
					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");

					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("//+++++++++++++++++++++++++++++++++++++++++++++++++++++++");

				}

			}

		} catch (IOException e1) {

			System.err.println(e1.getMessage());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			searchLoop(pageIndex, urlInfo, baseUrl, lienInfo, dealsUrlTemlate);

		} // }, TIMEOUT);
	};

	public void scrapPricesFromPage(String _url, String urlInfo, String baseUrl, String lienInfo) {
		/*
		 * String options = { url: _url, headers: { 'User-Agent': 'Mozilla/5.0
		 * (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
		 * Chrome/49.0.2623.112 Safari/537.36' // 'Mozilla/5.0 (Windows NT 6.1;
		 * WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103
		 * Safari/537.36' // 'Mozilla/5.0 (Windows NT 6.3; rv:36.0)
		 * Gecko/20100101 Firefox/36.0' } }; request(_url, function (error,
		 * response, body) { if (!error && response.statusCode == 200) {
		 * 
		 * //System.out.println(body); // fs.writeFile('retour.html', body); $ =
		 * cheerio.load(body);
		 * 
		 * 
		 * lastPage = ($("#noResultsTitle").html() != undefined &&
		 * $("#noResultsTitle").html().length > 0) ||
		 * ($("div.proceedWarning").html() != undefined &&
		 * $("div.proceedWarning").html().length > 0);
		 * 
		 * 
		 * $("li[id^='result']").each(function (i, elem) { numeroDeal++; asin =
		 * $(this).attr('data-asin'); titre = $(this).find($('h2')).text();
		 * imgUrl = $(this).find($('img')).attr('src');
		 * 
		 * prix = -1; prixString = $(this).find($('.a-color-price')).text(); if
		 * (prixString) { prix = commun.parsePrice(prixString, { pays:
		 * urlInfo.locale, taux: 1.3 }); } if (prix > 0 && prix < 10000) {
		 * System.out.println(numeroDeal + " - " + asin + " : " + prix);
		 * 
		 * 
		 * 
		 * 
		 * Deal.update({ asin: asin, pays: urlInfo.locale }, { titre: titre,
		 * asin: asin, categorie: lienInfo.CATEGORIE, //categorie: _categorie,
		 * pays: urlInfo.locale, url: baseUrl + asin + '/', prix: prix, img:
		 * imgUrl, lastUpdate: Date.now(), $inc: { version: 1 }, }, { upsert:
		 * true }, function (err, deal) { if (err) System.out.println(err);
		 * 
		 * });
		 * 
		 * 
		 * // System.out.println("lancement requete  " + (i + 1)); if
		 * (compareLocalPrices) { if (prix <= prixLimite) {
		 * 
		 * 
		 * commun.locale.forEach(function (locale) { //TODO Ajout délai d'une
		 * seconde entre les differentes locales getLocalPrices(asin, locale,
		 * urlInfo, prix, numeroDeal);
		 * 
		 * }); } }
		 * 
		 * }
		 * 
		 * });
		 * 
		 * } else { System.out.println(error); // lastPage = true; }
		 * 
		 * });
		 */
	}

}
