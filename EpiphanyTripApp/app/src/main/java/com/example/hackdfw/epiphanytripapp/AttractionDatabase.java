package com.example.hackdfw.epiphanytripapp;
import java.util.ArrayList;
import java.util.Date;


public class AttractionDatabase {
	private ArrayList<Attraction> attractions;

	public AttractionDatabase(String start_location, Date date, int distance){
		YelpQuery yp = new YelpQuery();
		attractions = yp.getAttractions(start_location , distance);
	}

    public ArrayList<Attraction> getAllAttractions(){
        return attractions;
    }

    public ArrayList<Attraction> filterByDistance(int d , ArrayList<Attraction> attr){
        
        return new ArrayList<Attraction>();
    }

    public ArrayList<Attraction> filterByDistance(int d){
       return this.filterByDistance(d,attractions);
    }

	public ArrayList<Attraction> filterByWeather(String w , ArrayList<Attraction> attr){

		return new ArrayList<Attraction>();
	}

    public ArrayList<Attraction> filterByWeather(String w){
        return this.filterByWeather(w,attractions);
    }
	
	public ArrayList<Attraction> filterByRating(double r , ArrayList<Attraction> attr) {

		return new ArrayList<Attraction>();
	}

    public ArrayList<Attraction> filterByRating(double r) {
        return this.filterByRating(r,attractions);
    }

	public ArrayList<Attraction> filterByCityName(String n , ArrayList<Attraction> attr){
		
		return new ArrayList<Attraction>();
	}

    public ArrayList<Attraction> filterByCityName(String n){
        return this.filterByCityName(n,attractions);
    }

}
