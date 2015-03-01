//package com.example.hackdfw.epiphanytripapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class WeatherQuery {

	private HttpURLConnection connection;
	private String httpRequest = "http://api.wunderground.com/api/7b5d56ecee70528f/";
	private XPathFactory factory;
    private XPath xpath;
    private InputSource inputXml = null;
		
	public WeatherQuery() {
		connection = null;
		httpRequest = "http://api.wunderground.com/api/7b5d56ecee70528f/";
		factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		inputXml = null;
	}
	
	
	// adding private to query API
	public String getWeather(String cn, Date d) throws XPathExpressionException, IOException{
		getAPIResponse("conditions/q/CA/San_Francisco.xml");
		return "";
	}
	
	private void getAPIResponse(String request) throws IOException, XPathExpressionException{
		
		URL url = new URL(httpRequest + request);
		connection = (HttpURLConnection) url.openConnection();
		inputXml = new InputSource(connection.getInputStream());
		
		NodeList nodes = nodes = (NodeList) xpath.evaluate("weather", inputXml, XPathConstants.NODESET);
		
		for (int i = 0, n = nodes.getLength(); i < n; i++) {
			String nodeString = nodes.item(i).getTextContent();
	        System.out.print(nodeString);
	        System.out.print("\n");
	    }
		
	}
}
