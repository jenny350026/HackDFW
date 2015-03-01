
public class Weather {

	private String summary;
	private String picURL;
	
	public Weather(String w, String wURL){
		summary = w;
		picURL= wURL;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public String getPicURL(){
		return picURL;
	}
}
