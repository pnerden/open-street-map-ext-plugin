package org.lsp.liferay.ext.osm.model;

import java.io.Serializable;

public class GeolocalizationPlot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6154463615012840607L;
	
	
	public String latitude;
	public String longitude;
	public String description;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
