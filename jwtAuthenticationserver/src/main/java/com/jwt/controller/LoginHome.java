package com.jwt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LoginHome {

	@RequestMapping("/welcome")
	public String welcome() {
		String text = "this is the next welcome page";
		text += " unauthorized user not allowed here";
		return text;
	}

	@GetMapping("/show-info/{showid}")
	public JSONObject getShows(@Validated @PathVariable("showid") String showid) throws JsonProcessingException, MalformedURLException {
		HttpURLConnection connection = null;
		URL url = new URL("http://api.tvmaze.com/shows");
		URL url1 = new URL("http://api.tvmaze.com/shows/1/episodes");
		JSONObject obj = new JSONObject();
		try {
		connection =  (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("Accept","application/json");
        connection.setUseCaches(false);
        connection.setAllowUserInteraction(false);
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String result ="";
		String output = null;
		while ((result = bufferedReader.readLine()) != null) {
		    output = result.replace("[", "").replace("]", "");
		    JSONObject jsonObject = new JSONObject(output); 
		    JSONArray jsonArray = new JSONArray(output); 
		    
			for (int i = 1; i < jsonObject.length(); i++) {
				obj.put("id", jsonObject.getInt("id"));
				obj.put("url", jsonObject.getString("url"));
				obj.put("name", jsonObject.getString("name"));
				obj.put("type", jsonObject.getString("type"));
				obj.put("language", jsonObject.getString("language"));
				obj.put("status", jsonObject.getString("status"));
				obj.put("runtime", jsonObject.getInt("runtime"));
				obj.put("premiered", jsonObject.getString("premiered"));
			    jsonArray = (JSONArray) jsonObject.get("externals");
		        Iterator<Object> iterator = jsonArray.iterator();
		        while(iterator.hasNext()) {
		           obj.put("externals",iterator.next());
		         }
				
			}
		     
		}
		
        bufferedReader.close();
        System.out.println(stringBuffer.toString());
    }
    catch(Exception e){
        System.out.println(e);
    }
    finally{
        if (connection != null) {
        try {
            connection.disconnect();
        }catch (Exception ex) {
            System.out.println("Error");
        }
     }
    }
		
	/*
	 * JSONObject jsonObject = getJson(url); JSONObject jsonObject1 = getJson(url1);
	 */
		

		/*
		 * RestTemplate restTemplate = new RestTemplate(); Object[] shows =
		 * restTemplate.getForObject(url, Object[].class); Object[] episode =
		 * restTemplate.getForObject(url1, Object[].class); ArrayList<Object> merge =
		 * new ArrayList<>(); merge.add(shows); merge.add(episode);
		 * System.out.println(merge);
		 */
		return obj;

	}

	public static JSONObject getJson(URL url) {
		String json = url.toString();
		return new JSONObject(json);
	}

}
