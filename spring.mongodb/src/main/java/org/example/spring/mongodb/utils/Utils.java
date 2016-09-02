package org.example.spring.mongodb.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
		return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]", ""));
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

	public static UrlListObject getUrlList(String fileName) {

		// Read from File to String
		UrlListObject urlListObject = null;
		Gson gson = new Gson();
		try {
			JsonParser parser = new JsonParser();

			JsonElement jsonElement = parser.parse(new FileReader(fileName));
			urlListObject = gson.fromJson(jsonElement.getAsJsonObject(), UrlListObject.class);
			System.out.println(urlListObject);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
		return urlListObject;
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
