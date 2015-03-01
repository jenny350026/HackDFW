//package com.example.hackdfw.epiphanytripapp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.xpath.XPathExpressionException;


public class AttractionDatabase {
	private ArrayList<Attraction> attractions;

	public AttractionDatabase(String start_location, Date date, int distance) throws XPathExpressionException, IOException{
		YelpQuery yq = new YelpQuery();
		attractions = yq.getAttractions(start_location , distance);
		WeatherQuery wq = new WeatherQuery();
		for(int i = 0; i<attractions.size(); i++){
			attractions.get(i).setWeather(wq.getWeather(attractions.get(i).getCity(), date));
		}
	}

    public ArrayList<Attraction> getAllAttractions(){
        return attractions;
    }
    
    public ArrayList<Attraction> filterByDistance(int d , ArrayList<Attraction> attr){
        ArrayList<Attraction> temp_attr = new ArrayList<Attraction>();
    	if(attr.size()==0)
    		return null;
    	else{
    		for(int i = 0; i<attr.size(); i++){
	        	if(attr.get(i).getDistanceFromStart()<=d){
	        		temp_attr.add(attr.get(i));
	        	}
    		}
    		return temp_attr;
        }     
        
    }

    public ArrayList<Attraction> filterByDistance(int d){
       return this.filterByDistance(d,attractions);
    }

	public ArrayList<Attraction> filterByWeather(String w , ArrayList<Attraction> attr){
		
		ArrayList<Attraction> temp_attr = new ArrayList<Attraction>();
		if(attr.size() == 0)
			return null;
		else{	
			for(int i = 0; i < attr.size(); i++){
			    if(attr.get(i).getWeather().equals(w)){
			        temp_attr.add(attr.get(i));
			    }
			}
			return temp_attr;
		}
	}

    public ArrayList<Attraction> filterByWeather(String w){
        return this.filterByWeather(w,attractions);
    }
	
	public ArrayList<Attraction> filterByRating(double r , ArrayList<Attraction> attr) {
		ArrayList<Attraction> temp_attr = new ArrayList<Attraction>();
    	if(attr.size()==0)
    		return null;
    	else{
    		for(int i = 0; i<attr.size(); i++){
	        	if(attr.get(i).getDistanceFromStart()>=r){
	        		temp_attr.add(attr.get(i));
	        	}
    		}
    		return temp_attr;
        }  
	}

    public ArrayList<Attraction> filterByRating(double r) {
        return this.filterByRating(r,attractions);
    }

	public ArrayList<Attraction> filterByCityName(String cn , ArrayList<Attraction> attr){
		ArrayList<Attraction> temp_attr = new ArrayList<Attraction>();
		if(attr.size() == 0)
			return null;
		else{	
			for(int i = 0; i < attr.size(); i++){
			    if(attr.get(i).getWeather().equals(cn)){
			        temp_attr.add(attr.get(i));
			    }
			}
			return temp_attr;
		}
	}

    public ArrayList<Attraction> filterByCityName(String n){
        return this.filterByCityName(n,attractions);
    }

}
