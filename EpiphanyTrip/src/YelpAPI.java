import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Code sample for accessing the Yelp API V2.
 * 
 * This program demonstrates the capability of the Yelp API version 2.0 by using the Search API to
 * query for businesses by a search term and location, and the Business API to query additional
 * information about the top result from the search query.
 * 
 * <p>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp Documentation</a> for more info.
 * 
 */
public class YelpAPI {

  private String API_HOST = "api.yelp.com";
  private String search_term;
  //private static final String DEFAULT_LOCATION = "San Francisco, CA";
  private String SEARCH_PATH = "/v2/search";
  private String BUSINESS_PATH = "/v2/business";
  
  private String search_location;
  private int    search_radius;
  private int    search_num;

 

  OAuthService service;
  Token accessToken;

  /**
   * Setup the Yelp API OAuth credentials.
   * 
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
    this.service =
        new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
            .apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
    this.search_term = "attraction";
  }

  /**
   * Creates and sends a request to the Search API by term and location.
   * <p>
   * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
   * for more info.
   * 
   * @param term <tt>String</tt> of the search term to be queried
   * @param location <tt>String</tt> of the location
   * @return <tt>String</tt> JSON Response
   */
  public String searchForBusinessesByLocation(String term, String location, boolean location_by_name) {
    OAuthRequest request = createOAuthRequest(SEARCH_PATH);
    request.addQuerystringParameter("term", term);
   // request.addQuerystringParameter("location", location);
    if(location_by_name)
    	request.addQuerystringParameter("location", location);
    else
    	request.addQuerystringParameter("ll", this.search_location);
    request.addQuerystringParameter("radius_filter", Integer.toString(this.search_radius));
    request.addQuerystringParameter("limit", String.valueOf(this.search_num));
    return sendRequestAndGetResponse(request);
  }

  /**
   * Creates and sends a request to the Business API by business ID.
   * <p>
   * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
   * for more info.
   * 
   * @param businessID <tt>String</tt> business ID of the requested business
   * @return <tt>String</tt> JSON Response
   */
  public String searchByBusinessId(String businessID) {
    OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
    return sendRequestAndGetResponse(request);
  }

  /**
   * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
   * 
   * @param path API endpoint to be queried
   * @return <tt>OAuthRequest</tt>
   */
  private OAuthRequest createOAuthRequest(String path) {
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
    return request;
  }

  /**
   * Sends an {@link OAuthRequest} and returns the {@link Response} body.
   * 
   * @param request {@link OAuthRequest} corresponding to the API request
   * @return <tt>String</tt> body of API response
   */
  private String sendRequestAndGetResponse(OAuthRequest request) {
    System.out.println("Querying " + request.getCompleteUrl() + " ...");
    this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    return response.getBody();
  }

  /**
   * Queries the Search API based on the command line arguments and takes the first result to query
   * the Business API.
   * 
   * @param yelpApi <tt>YelpAPI</tt> service instance
   * @param yelpApiCli <tt>YelpAPICLI</tt> command line arguments
   */
  private String queryAPI(boolean location_by_name) {
    String searchResponseJSON =
        this.searchForBusinessesByLocation(this.search_term, this.search_location, location_by_name);
    
    return searchResponseJSON;
    /*JSONObject firstBusiness = (JSONObject) businesses.get(0);
    String firstBusinessID = firstBusiness.get("id").toString();
    System.out.println(String.format(
        "%s businesses found, querying business info for the top result \"%s\" ...",
        businesses.size(), firstBusinessID));

    // Select the first business and display business details
    String businessResponseJSON = yelpApi.searchByBusinessId(firstBusinessID.toString());
    System.out.println(String.format("Result for business \"%s\" found:", firstBusinessID));
    System.out.println(businessResponseJSON);*/
    
  }

  public String search(String location, int distance, boolean location_by_name){
	  return this.search(location, distance, 10, location_by_name);
  }
  
  public String search(String location, int distance, int number, boolean location_by_name) {
    this.search_location = location;
    this.search_radius = distance;
    this.search_num = number;
   
    return queryAPI(location_by_name);
  }
}