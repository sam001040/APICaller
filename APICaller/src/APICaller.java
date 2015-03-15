import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class APICaller {
		/*
	 * Which library should we use btw Apache HTTP Client & HttpURLConnection? 
	 * - Seems like HttpULRConnection is the best choice for 
	 * phones on Gingerbread and above & uses less memory 
	 * 
	 * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
	 * 
	 * */
	
	/* Tutorial 
 * http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
 * */

	static final String VOCABULARY_URL_PATH = "http://imoweb.azurewebsites.net/vocabulary/procedure"; 
	static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name();
	static final String API_KEY = "FSdgvuujDNpD1YPVjN95XcSFXBdsVwf66qeijgZDdwkji6GiyqYoKw15JRPywYV5";


	/**
	 * private class, so no one can call
	 */
	private APICaller(){
		return;
	}

	/**
	 * --- Should this be done in JSON?
	 * 
	 * @param param: String array of parameter names to be passed in
	 * @param values: String array of values for respective parameters to be passed in
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String queryBuilder(String [] param, String [] values) throws UnsupportedEncodingException{
		if (param.length != values.length){
			//  throw log message
			return "";
		}
		StringBuilder sb = new StringBuilder("?");
		
		for (int i = 0; i < param.length; ++i){
			sb.append(param[i] + "=");
			sb.append(URLEncoder.encode(values[i], CHARSET) + "&");
		}
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	private static void responseCodeHandler(final int respCode, final String path) throws IOException{
		if (respCode >= 400) {
	        if (respCode == 404 || respCode == 410) {
	            throw new FileNotFoundException(path.toString());
	        } else {
	            throw new java.io.IOException("Server returned HTTP" +
	          " response code: " + respCode + " for URL: " +
	          path.toString());
		        }
		    }
	}
	
	private static Map<String, Object> slurp (final String path, final String query){
		try{
		
			HttpURLConnection connection = (HttpURLConnection)new URL(path + query).openConnection();
			connection.setRequestProperty("Accept-Charset", CHARSET);
		
			int respCode = (connection.getResponseCode());
			
			responseCodeHandler(respCode, path);
			
			InputStream response = connection.getInputStream();
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(response, Map.class);
			
			return jsonMap; 
	}
	 catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 } 
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}


	public static void vocabularyGET(final String queryWord, final int limit){	
		try {
					
			String[]params = {"query", "limit", "apiKey"};
			String[]values = {queryWord,Integer.toString(limit), API_KEY};
			String query = queryBuilder(params, values);
			
			Map<String, Object> jsonMap = slurp(VOCABULARY_URL_PATH, query);
			Map a = (Map) jsonMap.get("data");
			
			ArrayList <Object> obj = (ArrayList<Object>) a.get("items");
				
			System.out.println(Arrays.toString(obj.toArray()));		
			System.out.println(obj.size());
			    
		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} 

				
		
	}
}
