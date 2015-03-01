<<<<<<< HEAD
=======
//package com.example.hackdfw.epiphanytripapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
>>>>>>> master
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class WeatherQuery {

	private HttpURLConnection connection;
	private String httpRequest = "http://api.wunderground.com/api/7b5d56ecee70528f/";
    private XPath xpath;
    private InputSource inputXml = null;
		
	public WeatherQuery() {
		connection = null;
		httpRequest = "http://api.wunderground.com/api/7b5d56ecee70528f/";
		xpath = XPathFactory.newInstance().newXPath();
		inputXml = null;
	}
	
	
	// adding private to query API
	public Weather getWeather(String cn, Date d) throws XPathExpressionException, IOException{
		String[] parts = cn.split(", ");
		getAPIResponse("forecast10day/q/" + parts[1] + "/" + parts[0] + ".xml");
		return new Weather("","");
	}
	
	
	
	private void getAPIResponse(String request) throws IOException, XPathExpressionException{
		
		URL url = new URL(httpRequest + request);
		connection = (HttpURLConnection) url.openConnection();
		
		inputXml = new InputSource(connection.getInputStream());

		NodeList nodes = (NodeList) xpath.compile("/response/forecast/txt_forecast/forecastdays/forecastday").evaluate(inputXml, XPathConstants.NODESET);
		
		//System.out.println(nodes.getLength());
		for (int i = 0, n = nodes.getLength(); i < n; i++) {
			System.out.println(nodes.item(i).getNodeName() + ": " + nodes.item(i).getTextContent());
			String nodeString = nodes.item(i).getTextContent();
	        System.out.print(nodeString);
	        System.out.print("\n");
	    }
		
	}
}
