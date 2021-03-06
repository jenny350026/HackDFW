//package com.example.hackdfw.epiphanytripapp;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class YelpQuery {
	
	 /*
	   * Update OAuth credentials below from the Yelp Developers API site:
	   * http://www.yelp.com/developers/getting_started/api_access
	   */
	  private static String CONSUMER_KEY = "VkqrUAkISUpTZxo_HZRVBg";
	  private static String CONSUMER_SECRET = "7AI12aT7xjIE908LM54PcStva-g";
	  private static String TOKEN = "dIk2j8s2IXYbPHrSGm-CAifNFzNX2G3w";
	  private static String TOKEN_SECRET = "5FCLNt0g9aRpAnarkpRL4U1TWec";
	  
	  private YelpAPI yelpAPI;
	  
	  public YelpQuery(){
		  this.yelpAPI = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
	  }
	  
    public class FetchAPI extends Thread{
        private String start_location;
        private int distance;
        private ArrayList<Attraction> attractions;

        public FetchAPI(String s, int d){
            start_location = s;
            distance = d;
        }

        public void run() {
            attractions = getAPIResponse(start_location, distance);
        }

        public ArrayList<Attraction> getResults(){
            return attractions;
        }
    }

    public ArrayList<Attraction> getAttractions(String start_location, int distance){
        FetchAPI fapi = new FetchAPI(start_location, distance);
        fapi.start();
        try {
            fapi.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return fapi.getResults();
    }

	public ArrayList<Attraction> getAPIResponse(String start_location, int distance){
		char first_letter = Character.toLowerCase(start_location.charAt(0));
		String output_query;
		JSONParser parser;
		JSONObject response = null;
		distance  = (int)distance * 1609;
		
		//if true get longitude and latitude 
		if (first_letter <= 'z' && first_letter >= 'a'){
			output_query = this.yelpAPI.search(start_location, distance, true);
			parser = new JSONParser();
			try {
				  response = (JSONObject) parser.parse(output_query);
			} catch (ParseException pe) {
			  System.out.println("Error: could not parse JSON response:");
			  System.out.println(output_query);
			  System.exit(1);
			}
			JSONObject region_info = (JSONObject)response.get("region");
			JSONObject center      = (JSONObject)region_info.get("center");
			double latitude  = (double)center.get("latitude");
			double longitude = (double)center.get("longitude");
			start_location = Double.toString(latitude) + ", " + Double.toString(longitude);
		}
			
		ArrayList<Attraction>list = new ArrayList<Attraction>();
		output_query = this.yelpAPI.search(start_location, distance, false);
		System.out.println(output_query);
		parser = new JSONParser();
		try {
		  response = (JSONObject) parser.parse(output_query);
		} catch (ParseException pe) {
		  System.out.println("Error: could not parse JSON response:");
		  System.out.println(output_query);
		  System.exit(1);
		}
		
		JSONArray businesses = (JSONArray) response.get("businesses");
		for(int i = 0; i < 10; i ++){
			JSONObject cur_business;
			try{
				cur_business = (JSONObject) businesses.get(i);
			}catch(RuntimeException pe){
				return list;
			}
			JSONObject loc = (JSONObject) cur_business.get("location");
			double attr_distance = (double)cur_business.get("distance");
			String attr_name = (String)cur_business.get("name");
			String picURL = (String)cur_business.get("snippet_image_url");
			double rating = (double)cur_business.get("rating");
			JSONArray display = (JSONArray)loc.get("display_address");
			String city_name = ((String)display.get(0)).replaceAll("[0-9]", "").trim();
			Attraction a = new Attraction(attr_name, city_name, rating, attr_distance, picURL);
			list.add(a);
		}
		
		return list;
	}
}