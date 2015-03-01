public class Attraction {
	private String name;
	private String cityName;
	private double rating;
	private double distanceFromStart;
	private String picURL;
	private String weather; //Sunny, Mostly Cloudy, Rainy...
	
	
	//possibly adding addresses, directions, descriptions, review list... 
		
	
	public Attraction(){}
	public Attraction(String n, String cn, double r, double dfs, String pu){
		name = n;
		cityName = cn; //city, STATE
		rating = r;
		distanceFromStart = dfs;
		picURL = pu;
		weather = "";
	}
		
	public String getName(){
		return name;
	}
	
	public String getCity(){
		return cityName;
	}
	
	public double getRating(){
		return rating;
	}
	
	public double getDistanceFromStart(){
		return distanceFromStart;
	}
	
	public void setWeather(String s){
		weather = s;
	}
	
	public String getWeather(){
		return weather;
	}
	
	public String getPicURL(){
		return picURL;
	}
}
