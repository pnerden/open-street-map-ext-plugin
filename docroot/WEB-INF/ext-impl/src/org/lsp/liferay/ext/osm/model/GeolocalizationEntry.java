package org.lsp.liferay.ext.osm.model;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class GeolocalizationEntry {
	
	public String latitude;
	public String longitude;
	public String boundingbox;
	public String road;
	public String zipcode;
	public String city;
	public String city_district;
	public String county;
	public String state;
	public String country;
	
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getBoundingbox() {
		return boundingbox;
	}
	public void setBoundingbox(String boundingbox) {
		this.boundingbox = boundingbox;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_district() {
		return city_district;
	}
	public void setCity_district(String city_district) {
		this.city_district = city_district;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("lat", this.getLatitude());
		jsonObject.put("lng", this.getLongitude());
		jsonObject.put("box", this.getBoundingbox());
		jsonObject.put("road", this.getRoad());
		jsonObject.put("city", this.getCity());
		jsonObject.put("city_district", this.getCity_district());
		jsonObject.put("county", this.getCounty());
		jsonObject.put("state", this.getState());
		jsonObject.put("zipcode", this.getZipcode());
		jsonObject.put("country", this.getCountry());
		return jsonObject;
	}

}
