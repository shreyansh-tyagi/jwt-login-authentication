package com.jwt.controller;

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
	public Object getShows(@Validated @PathVariable("showid") String showid) throws JsonProcessingException, MalformedURLException {
		URL url = new URL("http://www.javatpoint.com/java-tutorial");
		URL url1 = new URL("http://www.javatpoint.com/java-tutorial");
		JSONObject jsonObject = getJson(url);
		JSONObject jsonObject1 = getJson(url1);
		JSONObject obj = new JSONObject();
		for (int i = 0; i < jsonObject.length(); i++) {
			obj.put("id", jsonObject.getInt("id"));
			obj.put("url", jsonObject.getString("url"));
			obj.put("name", jsonObject.getString("name"));
			obj.put("type", jsonObject.getString("type"));
			obj.put("language", jsonObject.getString("language"));
			obj.put("status", jsonObject.getString("status"));
			obj.put("runtime", jsonObject.getInt("runtime"));
			obj.put("premiered", jsonObject.getString("premiered"));
			JSONArray jsonArray = (JSONArray) jsonObject.get("externals");
	        Iterator<Object> iterator = jsonArray.iterator();
	        while(iterator.hasNext()) {
	           obj.put("externals",iterator.next());
	         }
			
		}

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
