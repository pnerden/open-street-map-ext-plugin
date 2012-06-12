package org.lsp.liferay.ext.osm.taglib;

import javax.servlet.http.HttpServletRequest;

import org.lsp.liferay.ext.osm.taglib.util.OSMTaglibConstants;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.taglib.util.IncludeTag;

public class OSMMapTag extends IncludeTag {
	
	protected static final String ATTRIBUTE_NAMESPACE = "lsp-osm:map:";
		
	private long occurenceId = OSMTaglibConstants.DEFAULT_OCCURENCE_ID;
	private String startLatitude = StringPool.BLANK;	
	private String startLongitude = StringPool.BLANK;
	private String startZoom = StringPool.BLANK;
	private String locationLatitudeFieldIdBind = StringPool.BLANK;
	private String locationLongitudeFieldIdBind = StringPool.BLANK;
	private String locationZoomFieldIdBind = StringPool.BLANK;
	private String locationLocationFieldIdBind = StringPool.BLANK;
	private boolean locationMarkerActive = false;
	
	private static final String _PAGE =
            "/html/taglib/osm/map/map.jsp";

	@Override
    protected String getPage() {
            return _PAGE;
    }
	
	@Override
    protected void cleanUp() {
            occurenceId = OSMTaglibConstants.DEFAULT_OCCURENCE_ID;
        	startLatitude = StringPool.BLANK;	
        	startLongitude = StringPool.BLANK;
        	startZoom = StringPool.BLANK;
        	locationLatitudeFieldIdBind = StringPool.BLANK;
        	locationLongitudeFieldIdBind = StringPool.BLANK;
        	locationZoomFieldIdBind = StringPool.BLANK;
        	locationLocationFieldIdBind = StringPool.BLANK;
        	locationMarkerActive = false;
    }

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"portletNameSpace","_"+request.getAttribute("PORTLET_ID")+"_");
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"occurenceId",String.valueOf(occurenceId));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startLatitude",String.valueOf(startLatitude));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startLongitude",String.valueOf(startLongitude));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startZoom",String.valueOf(startZoom));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"locationLatitudeFieldIdBind",String.valueOf(locationLatitudeFieldIdBind));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"locationLongitudeFieldIdBind",String.valueOf(locationLongitudeFieldIdBind));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"locationZoomFieldIdBind",String.valueOf(locationZoomFieldIdBind));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"locationLocationFieldIdBind",String.valueOf(locationLocationFieldIdBind));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"locationMarkerActive",String.valueOf(locationMarkerActive));
	}



	public long getOccurenceId() {
		return occurenceId;
	}

	public void setOccurenceId(long occurenceId) {
		if (startZoom.length() > 0) {
			this.occurenceId = occurenceId;
		}
	}

	public String getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(String startLatitude) {
		this.startLatitude = startLatitude;
	}

	public String getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(String startLongitude) {
		this.startLongitude = startLongitude;
	}

	public String getStartZoom() {
		return startZoom;
	}

	public void setStartZoom(String startZoom) {
		this.startZoom = startZoom;
	}

	public String getLocationLatitudeFieldIdBind() {
		return locationLatitudeFieldIdBind;
	}

	public void setLocationLatitudeFieldIdBind(String locationLatitudeFieldIdBind) {
		this.locationLatitudeFieldIdBind = locationLatitudeFieldIdBind;
	}

	public String getLocationLongitudeFieldIdBind() {
		return locationLongitudeFieldIdBind;
	}

	public void setLocationLongitudeFieldIdBind(String locationLongitudeFieldIdBind) {
		this.locationLongitudeFieldIdBind = locationLongitudeFieldIdBind;
	}
	
	public String getLocationZoomFieldIdBind() {
		return locationZoomFieldIdBind;
	}

	public void setLocationZoomFieldIdBind(String locationZoomFieldIdBind) {
		this.locationZoomFieldIdBind = locationZoomFieldIdBind;
	}

	public String getLocationLocationFieldIdBind() {
		return locationLocationFieldIdBind;
	}

	public void setLocationLocationFieldIdBind(String locationLocationFieldIdBind) {
		this.locationLocationFieldIdBind = locationLocationFieldIdBind;
	}

	public boolean isLocationMarkerActive() {
		return locationMarkerActive;
	}

	public void setLocationMarkerActive(boolean locationMarkerActive) {
		this.locationMarkerActive = locationMarkerActive;
	}
	
	
	



}
