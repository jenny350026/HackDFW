package com.example.hackdfw.epiphanytripapp;
import java.util.ArrayList;
import java.util.Date;


public class AttractionDatabase {
	private ArrayList<Attraction> attractions;

	public AttractionDatabase(String start_location, Date date, int distance){
		YelpQuery yp = new YelpQuery();
		attractions = yp.getAttractions(start_location , distance);
	}
	
	public ArrayList<Attraction> filterByDistance(int d){
		
		return new ArrayList<Attraction>();
	}
	
	public ArrayList<Attraction> filterByWeather(String w){

		return new ArrayList<Attraction>();
	}
	
	public ArrayList<Attraction> filterByRating(double r) {

		return new ArrayList<Attraction>();
	}
	
	public ArrayList<Attraction> filterByCityName(String n){
		
		return new ArrayList<Attraction>();
	}

}
