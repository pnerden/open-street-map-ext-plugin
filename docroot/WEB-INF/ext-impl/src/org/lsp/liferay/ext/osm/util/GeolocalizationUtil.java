package org.lsp.liferay.ext.osm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.lsp.liferay.ext.osm.model.GeolocalizationEntry;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.liferay.portal.json.JSONArrayImpl;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

public class GeolocalizationUtil {
	
	private static final String PROVIDER_SEARCH_QUERY_TEMPLATE = "http://nominatim.openstreetmap.org/search/?q={q}&format=xml&polygon=0&addressdetails=1&countrycodes=fr&limit=5";
	private static final String PROVIDER_CACHE_NAME = "GeolocalizationEntry";
	private static final String PROVIDER_REVERSE_SEARCH_QUERY_TEMPLATE = "http://nominatim.openstreetmap.org/reverse?format=xml&lat={lat}&lon={lon}&zoom=18&addressdetails=1";
	
	@SuppressWarnings("unchecked")
	public static List<GeolocalizationEntry> getAsObjectByQuery(String query) {
		
		List<GeolocalizationEntry> result = new ArrayList<GeolocalizationEntry>();
		
		if (MultiVMPoolUtil.get(PROVIDER_CACHE_NAME, query) != null) {
			result = (ArrayList<GeolocalizationEntry>) MultiVMPoolUtil.get(PROVIDER_CACHE_NAME, query);
		} else {
			RestTemplate rest = new RestTemplate();
	
			Map<String, String> params = new HashMap<String, String>();
			params.put("q", query);
			Source source = rest.getForObject(PROVIDER_SEARCH_QUERY_TEMPLATE, Source.class, params);
			
			XPathOperations xpath = new Jaxp13XPathTemplate();
			List<Node> nodes = xpath.evaluateAsNodeList("/searchresults/place", source);
			
			Iterator<Node> iter = nodes.iterator();
			while (iter.hasNext()) {
				Element locElement = (Element) iter.next();
				GeolocalizationEntry entry = new GeolocalizationEntry();
				
				entry.setLatitude(locElement.getAttribute("lat"));
				entry.setLongitude(locElement.getAttribute("lon"));
				entry.setBoundingbox(locElement.getAttribute("boundingbox"));
				
				NodeList list = locElement.getChildNodes();
				for (int i=0; i < list.getLength(); i++) {
					Node subnode = list.item(i);
					
					if (subnode.getNodeName() == "road") {
						entry.setRoad(subnode.getTextContent());
					}
					
					if (subnode.getNodeName() == "city") {
						entry.setCity(subnode.getTextContent());
					}
					
					if (subnode.getNodeName() == "city_district") {
						entry.setCity_district(subnode.getTextContent());
					}
					
					if (subnode.getNodeName() == "county") {
						entry.setCounty(subnode.getTextContent());
					}
					
					if (subnode.getNodeName() == "state") {
						entry.setState(subnode.getTextContent());
					}
					
					if (subnode.getNodeName() == "postcode") {
						entry.setZipcode(subnode.getTextContent());
					}
					
					if (subnode.getNodeName() == "country") {
						entry.setCountry(subnode.getTextContent());
					}
				}
				
				result.add(entry);
				
			}			
			
			MultiVMPoolUtil.put(PROVIDER_CACHE_NAME, query, result);
		}
		
		return result;
		
	}
	
	public static JSONArray getAsJSONArrayByQuery(String query) {
		List<GeolocalizationEntry> loc = getAsObjectByQuery(query);
		Iterator<GeolocalizationEntry> iter = loc.iterator();
		
		JSONArray result = new JSONArrayImpl();
		
		while (iter.hasNext()) {
			result.put(iter.next().toJSONObject());
		}
		
		return result;
		
	}
	
	public static GeolocalizationEntry getAsObjectByCoordinates(String latitude, String longitude) {
		
		GeolocalizationEntry result = new GeolocalizationEntry();
		
		if (MultiVMPoolUtil.get(PROVIDER_CACHE_NAME, buildCacheKey(latitude,longitude)) != null) {
			result = (GeolocalizationEntry) MultiVMPoolUtil.get(PROVIDER_CACHE_NAME, buildCacheKey(latitude,longitude));
		} else {
			RestTemplate rest = new RestTemplate();
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("lat", latitude);
			params.put("lon", longitude);
			Source source = rest.getForObject(PROVIDER_REVERSE_SEARCH_QUERY_TEMPLATE, Source.class, params);
			
			XPathOperations xpath = new Jaxp13XPathTemplate();
			Element locElement = (Element) xpath.evaluateAsNode("/reversegeocode/addressparts", source);
			NodeList list = locElement.getChildNodes();
			
			result.setLatitude(latitude);
			result.setLongitude(longitude);
			
			for (int i=0; i < list.getLength(); i++) {
				Node subnode = list.item(i);
				
				if (subnode.getNodeName() == "road") {
					result.setRoad(subnode.getTextContent());
				}
				
				if (subnode.getNodeName() == "city") {
					result.setCity(subnode.getTextContent());
				}
				
				if (subnode.getNodeName() == "city_district") {
					result.setCity_district(subnode.getTextContent());
				}
				
				if (subnode.getNodeName() == "county") {
					result.setCounty(subnode.getTextContent());
				}
				
				if (subnode.getNodeName() == "state") {
					result.setState(subnode.getTextContent());
				}
				
				if (subnode.getNodeName() == "postcode") {
					result.setZipcode(subnode.getTextContent());
				}
				
				if (subnode.getNodeName() == "country") {
					result.setCountry(subnode.getTextContent());
				}
			}
			
		}
		
		return result;
		
	}
		
	public static JSONObject getAsJSONObjectByCoordinates(String latitude, String longitude) {
		return getAsObjectByCoordinates(latitude, longitude).toJSONObject();
	}
		
		
	private static final String buildCacheKey(String latitude, String longitude) {
		return latitude+"|"+longitude;
	}

}
