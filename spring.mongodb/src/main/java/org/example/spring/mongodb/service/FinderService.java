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
import java.util.Map;
import java.util.Map.Entry;

import org.example.spring.mongodb.dao.DealDao;
import org.example.spring.mongodb.entity.Deal;
import org.example.spring.mongodb.utils.LinkInfo;
import org.example.spring.mongodb.utils.UrlInfo;
import org.example.spring.mongodb.utils.UrlListObject;
import org.example.spring.mongodb.utils.Utils;
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

	private static final int MAX_PAGE =  401;
	int TIMEOUT = 5000;
	int prixLimite = 150;
	Boolean compareLocalPrices = false;
	String articleUrlTemplate = "http://www.amazon.{0}/dp/{1}";
	String baseUrlTemplate = "http://www.amazon.{0}/gp/offer-listing/";
	List<LinkInfo> urlList = null;
	// String searchList = NOUVEAU.FLASH;
	// String searchList = RECONDITIONNE.TOP;
	 String searchList = "BARRES_TOIT";
//	 String searchList = "ONDULEUR";

	// String searchList = RECONDITIONNE.SKI;
	//String searchList = "RECONDITIONNE.PS4";
	// String searchList = RECONDITIONNE.COFFRE;

	// String searchList = RECONDITIONNE.RAPIDE;
	// String searchList = RECONDITIONNE.IT;
	// String searchList = NOUVEAU.SOLDES;

	// String searchList = NOUVEAU.FR;

	int numeroDeal = 0;

	String lienInfo;
	String dealsUrl = "http://www.amazon.fr/s/ref=sr_pg_{0}?fst=as%3Aoff&rh=n%3A8873224031%2Cn%3A206617031&page={0}&bbn=8873224031&sort=price-desc-rank";
	Boolean lastPage = false;
	int indexLien = 0;
	List<String> links = new ArrayList<>();
	String _categorie = "";
	int pageIndex;

	public void recherchePrix() {
		UrlListObject urlListObject = Utils
				.getUrlList("E://DEV//Amazon//mongodb-java//spring.mongodb//src//main//resources//reconditionne.json");
		Map<String, LinkInfo> barresUrlList = urlListObject.getUrlList().get(searchList);
		urlList = new ArrayList<>(barresUrlList.values());
		recherchePrix(0);
	}

	public void recherchePrix(int indexL) {
		LinkInfo lienInfo = urlList.get(indexL);
		String dealsUrlTemlate = lienInfo.getLINK();
		UrlInfo urlInfo = Utils.getUrlInfos(MessageFormat.format(dealsUrlTemlate, new Object[] { 1 }));
		String baseUrl = MessageFormat.format(baseUrlTemplate, new Object[] { urlInfo.getLocale() });
		System.out.println(urlInfo);
		lastPage = false;
		pageIndex = 1;
		searchLoop(pageIndex, urlInfo, baseUrl, lienInfo, dealsUrlTemlate);
	};

	public void searchLoop(int pageIndex, UrlInfo urlInfo, String baseUrl, LinkInfo lienInfo, String dealsUrlTemlate) {
		System.out.println("+++++++++++++++++++++++ Page " + pageIndex + " +++++++++++++++++++++++");
		String url = MessageFormat.format(dealsUrlTemlate, new Object[] { pageIndex });// dealsUrlTemlate(pageIndex);
		System.out.println(url);
		Document doc;
		try {
			// TODO Random userAgents List from array
			doc = Jsoup.connect(url).timeout(30000)
					.userAgent(
							"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.referrer("http://www.google.com").get();

			System.out.println(doc.title());
			Elements deals = doc.select("li[id^='result']");
			for (Element dealInfo : deals) {
				traitementDealInfoElement(urlInfo, baseUrl, lienInfo, dealInfo);

			}

			lastPage = deals == null || deals.size() == 0;
			pageIndex++;
			if (!lastPage && pageIndex < MAX_PAGE) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				searchLoop(pageIndex, urlInfo, baseUrl, lienInfo, dealsUrlTemlate);
			} else {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!! LastPage " + pageIndex + " !!!!!!!!!!!!!!!!!!!!!!");
				indexLien++;

				if (indexLien < urlList.size()) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lastPage = false;
					System.out.println("//////////////////////////// Passage  à la  categorie "
							+ urlList.get(indexLien).getCATEGORIE() + " ////////////////////////////");

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
	}

	private void traitementDealInfoElement(UrlInfo urlInfo, String baseUrl, LinkInfo lienInfo, Element dealInfo) {
		numeroDeal++;
		// get the value from the href attribute
		String asin = dealInfo.attr("data-asin");
		String titre = dealInfo.select("h2").text();
		String imgUrl = dealInfo.select("img").attr("src");
		String pays = urlInfo.getLocale();
		String categorie = lienInfo.getCATEGORIE();
		String dealUrl = baseUrl + asin + "/";

		String prixString = dealInfo.select(".a-color-price").text();

		Number prix = convertPrice(pays, prixString);
		Date lastUpdate = Calendar.getInstance().getTime();

		Deal deal = null;
		deal = new Deal(titre, asin, categorie, pays, dealUrl, prix, imgUrl, lastUpdate);

		deal = dealDao.findOneByAsinAndPays(asin, pays);
		if (null == deal) {
			System.out.println("NEW DEAL");
			deal = new Deal(titre, asin, categorie, pays, dealUrl, prix, imgUrl, lastUpdate);
		} else {
			System.out.println("ODL DEAL");

			deal.setPrix(prix);
			deal.setLastUpdate(Calendar.getInstance().getTime());
		}

		// System.out.println(deal.getPrixLocaux());
//		for (Entry<String, Float> _locale : Utils.locale.entrySet()) {
//			if ((_locale.getKey().equals("co.uk") && deal.getPrixLocaux().getCouk() < 0)
//					|| (_locale.getKey().equals("fr") && deal.getPrixLocaux().getFr() < 0)
//					|| (_locale.getKey().equals("de") && deal.getPrixLocaux().getDe() < 0)
//					|| (_locale.getKey().equals("it") && deal.getPrixLocaux().getIt() < 0)) {
//
//				 getLocalPrices(deal, _locale, urlInfo, numeroDeal);
//			}
//
//		}

		dealDao.save(deal);
		System.out.println(numeroDeal + " - " + deal);
	}

	private Number convertPrice(String pays, String prixString) {
		prixString = prixString.replaceAll("[^0-9.,]", "");
		Locale locale = Locale.UK;

		if (!pays.equals("co.uk")) {
			locale = Locale.FRANCE;
		}

		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		numberFormat.setMaximumFractionDigits(2);
		Number prix = -1;
		try {
			prix = numberFormat.parse(prixString);
			if (pays.equals("co.uk")) {
				prix = prix.doubleValue() * 1.2;
			}
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		return prix;
	};

	private void getLocalPrices(Deal deal, Entry<String, Float> _locale, UrlInfo urlInfo, int numeroDeal2) {

		String articleUrl = MessageFormat.format(articleUrlTemplate, new Object[] { _locale.getKey(), deal.getAsin() });

		Document doc;
		try {
			doc = Jsoup.connect(articleUrl).timeout(30000)
					.userAgent(
							"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.referrer("http://www.google.com").get();

			String priceString = doc.select("#priceblock_ourprice").text();
			if (null == priceString || priceString.trim().length() == 0) {
				return;

			}
			Number prix = convertPrice(_locale.getKey(), priceString);
			if (prix == null || prix.intValue() == -1) {

				return;
			}

			Float reduction = 100 * deal.getPrix().floatValue() / prix.floatValue();

			if (_locale.getKey().equals(urlInfo.getLocale())) {

				deal.setReduction(reduction);

			}
			if (deal != null && (deal.getReductionGlobale() == null || reduction < deal.getReductionGlobale())) {
				deal.setReductionGlobale(reduction);
			}

			if (_locale.getKey().equals("fr")) {

				deal.getPrixLocaux().setFr(prix.floatValue());
			} else if (_locale.getKey().equals("de")) {

				deal.getPrixLocaux().setDe(prix.floatValue());
			} else if (_locale.getKey().equals("it")) {

				deal.getPrixLocaux().setIt(prix.floatValue());
			} else {

				deal.getPrixLocaux().setCouk(prix.floatValue());
			}
			System.out.println(
					numeroDeal + " - " + _locale.getKey() + " prix : " + prix + " prixLocal : " + deal.getPrix()
							+ " reduction : " + reduction + " reductionGlobale : " + deal.getReductionGlobale());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
