
public class Weather {

	private String weather;
	private String weatherURL;
	
	public Weather(String w, String wURL){
		weather = w;
		weatherURL= wURL;
	}
	
	public String getWeather(){
		return weather;
	}
	
	public String getWeatherURL(){
		return weatherURL;
	}
}
