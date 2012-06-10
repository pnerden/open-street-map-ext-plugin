package org.lsp.liferay.ext.osm.taglib;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.taglib.util.IncludeTag;

public class OSMMapTag extends IncludeTag {
	
	protected static final String ATTRIBUTE_NAMESPACE = "lsp-osm:map:";
	protected static final long DEFAULT_OCCURENCE_ID = 0;
	
	private long occurenceId = DEFAULT_OCCURENCE_ID;
	private String startLatitude = StringPool.BLANK;	
	private String startLongitude = StringPool.BLANK;
	private String startZoom = StringPool.BLANK;
	
	private static final String _PAGE =
            "/html/taglib/osm/map.jsp";

	@Override
    protected String getPage() {
            return _PAGE;
    }
	
	@Override
    protected void cleanUp() {
            occurenceId = DEFAULT_OCCURENCE_ID;
        	startLatitude = StringPool.BLANK;	
        	startLongitude = StringPool.BLANK;
        	startZoom = StringPool.BLANK;
    }

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"portletNameSpace","_"+request.getAttribute("PORTLET_ID")+"_");
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"occurenceId",String.valueOf(occurenceId));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startLatitude",String.valueOf(startLatitude));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startLongitude",String.valueOf(startLongitude));
		setNamespacedAttribute(request, ATTRIBUTE_NAMESPACE+"startZoom",String.valueOf(startZoom));
	}



	public long getOccurenceId() {
		return occurenceId;
	}

	public void setOccurenceId(long occurenceId) {
		this.occurenceId = occurenceId;
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
	
	
	



}
