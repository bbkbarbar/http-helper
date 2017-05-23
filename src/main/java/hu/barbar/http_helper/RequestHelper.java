package hu.barbar.http_helper;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import hu.barbar.http_helper.util.Response;

public class RequestHelper {

	private static final String USER_AGENT = "Mozilla/5.0";
	
	
	protected static final HashMap<String, String> getDefaultProperties(){
		HashMap<String, String> prop = new HashMap<String, String>();
		prop.put("User-Agent", USER_AGENT);
		prop.put("Accept-Language", "en-US,en;q=0.5");
		return prop;
	}
	
	
	/**
	 * HTTP GET request to specified url
	 * @param url
	 * @return a {@link Response} object
	 * @throws Exception
	 */
	public static Response sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		
		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer sb = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();
		
		Response response = new Response(url, responseCode, sb);
		
		return response;

	}
	
	
	/**
	 * HTTP POST request
	 * @param url
	 * @param urlParameters
	 * @param properties
	 * @return a {@link Response} object
	 * @throws Exception
	 */
	public static Response sendPost(String url, String urlParameters, HashMap<String, String> properties) throws Exception {

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		
		Iterator<Entry<String, String>> it = properties.entrySet().iterator();
	    while (it.hasNext()) {
			Map.Entry<String, String> pair = (Entry<String, String>)it.next();
	        con.setRequestProperty((String)pair.getKey(), (String)pair.getValue());
	        it.remove(); 
	    }
		

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();


		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer sb = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();
		
		Response response = new Response(url, con.getResponseCode(), sb);
		
		return response;
	}

	
	/**
	 * HTTP POST request
	 * @param url
	 * @param urlParameters
	 * @return a {@link Response} object
	 * @throws Exception
	 */
	public static Response sendPost(String url, String urlParameters) throws Exception {
		return sendPost(url, urlParameters, getDefaultProperties());
	}

	
}
