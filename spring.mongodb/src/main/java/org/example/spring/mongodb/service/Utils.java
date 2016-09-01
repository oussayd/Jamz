package org.example.spring.mongodb.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.example.spring.mongodb.utils.UrlInfo;

public class Utils {

	public static final Map<String, Float> locale;

	static {
		locale = new HashMap<String, Float>();
		locale.put("fr", 1f);
		locale.put("de", 1f);
		locale.put("it", 1f);
		locale.put("co.uk", 1.2f);
	}

	public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
	    final NumberFormat format = NumberFormat.getNumberInstance(locale);
	    if (format instanceof DecimalFormat) {
	        ((DecimalFormat) format).setParseBigDecimal(true);
	    }
	    return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));
	}
	
	
	public static UrlInfo getUrlInfos(String _url) {
		String domain = null;
		String locale = null;
		try {
			domain = new URI(_url).getHost();
			locale = domain.replace("www.amazon.", "");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new UrlInfo(domain, locale);

	}

	// public Float parsePrice(String stringPrice, locale) {
	// prix = -1;
	//
	// if (locale.pays === 'co.uk') {
	//
	// prix = parser(stringPrice.replace(/[^\d^,^.]/g, ''), {
	// us: 0.75,
	// fr: 0.25
	// }) * 1.3;
	// } else {
	// prix = parser(stringPrice.replace(/[^\d^,^.]/g, ''));
	//
	// }
	//
	// return prix.toFixed(2);
	// };

}
