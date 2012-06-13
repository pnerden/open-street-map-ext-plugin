package org.lsp.liferay.ext.osm.taglib;

import javax.servlet.http.HttpServletRequest;

import org.lsp.liferay.ext.osm.taglib.util.OSMTaglibConstants;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.taglib.util.IncludeTag;

public class OSMSimpleMapTag extends IncludeTag {
	
	protected static final String ATTRIBUTE_NAMESPACE = "lsp-osm:simplemap:";
	
	
	private long occurenceId = OSMTaglibConstants.DEFAULT_OCCURENCE_ID;
	private String startLatitude = StringPool.BLANK;	
	private String startLongitude = StringPool.BLANK;
	private String startZoom = OSMTaglibConstants.DEFAULT_VIEW_ZOOM;
	private long mapWidth = OSMTaglibConstants.DEFAULT_MAP_WIDTH;
	private long mapHeight = OSMTaglibConstants.DEFAULT_MAP_WIDTH;
	private String displayText = StringPool.BLANK;
	
	private static final String _PAGE =
            "/html/taglib/osm/simplemap/simplemap.jsp";

	@Override
    protected String getPage() {
            return _PAGE;
    }
	
	@Override
    protected void cleanUp() {
            occurenceId = OSMTaglibConstants.DEFAULT_OCCURENCE_ID;
        	startLatitude = StringPool.BLANK;	
        	startLongitude = StringPool.BLANK;
        	startZoom = OSMTaglibConstants.DEFAULT_VIEW_ZOOM;
        	mapWidth = OSMTaglibConstants.DEFAULT_MAP_WIDTH;
        	mapHeight = OSMTaglibConstants.DEFAULT_MAP_WIDTH;
        	displayText = StringPool.BLANK;
    }

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"portletNameSpace","_"+request.getAttribute("PORTLET_ID")+"_");
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"occurenceId",String.valueOf(occurenceId));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startLatitude",String.valueOf(startLatitude));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startLongitude",String.valueOf(startLongitude));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startZoom",String.valueOf(startZoom));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"mapWidth",String.valueOf(mapWidth));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"mapHeight",String.valueOf(mapHeight));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"displayText",String.valueOf(displayText));
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
		if (startZoom.length() > 0) {
			this.startZoom = startZoom;
		}		
	}

	public long getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(long mapWidth) {
		this.mapWidth = mapWidth;
	}

	public long getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(long mapHeight) {
		this.mapHeight = mapHeight;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

}
