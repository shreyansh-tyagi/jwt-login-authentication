package com.jwt.controller;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	public Object getShows(@Validated @PathVariable("showid") String showid) throws JsonProcessingException {
		URL url=new URL("http://www.javatpoint.com/java-tutorial"); 
		URL url1=new URL("http://www.javatpoint.com/java-tutorial"); 
		JSONObject jsonObject =getJson(url);
		JSONArray arr = (JSONArray) jsonObject.get("id");
        for(int i=0; i<arr.length(); i++){
        	JSONObject issue = (JSONObject) arr.get(i);
            String issue_key = (String) arr.get("key");
            JSONObject fields = (JSONObject) arr.get("fields");
            JSONObject project = (JSONObject) arr.get("project");
            String project_name = (String) project.get("key");
            String fields_updated = (String) fields.get("updated");
            String fields_created = (String) fields.get("created");
            
        }
        JSONObject jsonObject1 =getJson(url1);
		jsonObject.getString("id");
		RestTemplate restTemplate = new RestTemplate();

		
		/*
		 * Object[] shows = restTemplate.getForObject(url, Object[].class); Object[]
		 * episode = restTemplate.getForObject(url1, Object[].class); ArrayList<Object>
		 * merge = new ArrayList<>(); merge.add(shows); merge.add(episode);
		 * System.out.println(merge);
		 */
		return merge;

	}

	public static JSONObject getJson(URL url) {
		String json = url.toString();
		return new JSONObject(json);
	}

}
