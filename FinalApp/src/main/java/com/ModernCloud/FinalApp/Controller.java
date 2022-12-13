package com.ModernCloud.FinalApp;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
	@CrossOrigin(origins = "*")
	@RequestMapping("/location")
	public HashMap<String, String> getLocation() {
		// Get the ip address
		String ipUrl = "https://api.ipify.org/?format=json";
		RestTemplate restTemplate = new RestTemplate();
		LinkedHashMap<String, String> result = restTemplate.getForObject(ipUrl, LinkedHashMap.class);
		String myIp = result.get("ip");
		
		// Get the Goegraphy params
		String geoUrl = "https://ipinfo.io/" + myIp + "/geo";
		LinkedHashMap<String, String> geo = restTemplate.getForObject(geoUrl, LinkedHashMap.class);
		
		String loc = geo.get("loc");
		String[] coords = loc.split(",");
		
		String lat = coords[0];
		String lon = coords[1];
		
		String url = "https://www.openstreetmap.org/export/embed.html?bbox="+lon+"%2C"+lat+"%2C"+lon+"%2C"+lat+"&amp;layer=mapnik&amp;marker="+lat+"%2C"+lon;		
	    HashMap<String, String> map = new HashMap<>();
	    map.put("url", url);
	    
	    return map;
	}
	
	@RequestMapping("/hello")
	public String getHello() {
		return "Hello";
	}

}
