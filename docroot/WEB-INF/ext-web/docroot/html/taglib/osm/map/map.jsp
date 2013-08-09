<%--
/**
 * Copyright (c) 2011-2012 Patrick NERDEN. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
--%>

<%@ include file="/html/taglib/osm/map/init.jsp" %>

<div class="osm-global" id="<%= portletNameSpace%>OSM_<%= occurenceId %>_">
	<c:if test="<%= (PortalUtil.getUser(request) != null) %>">
	<aui:layout cssClass="osm-mapSearch">
		<aui:column columnWidth="50" first="true">
			<input type="text" id="<%= portletNameSpace%>MapSearch_<%= occurenceId %>_" value="" /> <button name="<%= portletNameSpace%>SearchMap_<%= occurenceId %>_" type="button" onClick="<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.meetupsMapSearch()"><%= LanguageUtil.get(pageContext, "search") %></button>
		</aui:column>
		<aui:column columnWidth="50" last="true">
		</aui:column>
	</aui:layout>
	</c:if>
	<aui:layout cssClass="osm-navigation">
		<aui:column cssClass="osm-mapView" columnWidth="50" first="true">
		<%
		if (locationMarkerActive) {
		%>
			<p><liferay-ui:message key="rightclick-the-map-or-drag-the-flag-to-set-location" /></p>
		<%
		}
		%>
			<div id="<%= portletNameSpace%>map_<%= occurenceId %>_" style="width:<%= mapWidth%>px;height:<%= mapHeight%>px;"></div>
			<p>Map data &#169; OpenStreetMap contributors<br />Nominatim Search Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png"></p>
		</aui:column>
		<aui:column cssClass="osm-mapSuggestions" columnWidth="45" last="true">
			<p><%= LanguageUtil.get(pageContext, "suggestions-of-locations") %></p>
			<div id="<%= portletNameSpace%>MapSuggestions_<%= occurenceId %>_"></div>
		</aui:column>
	</aui:layout>
</div>

<script>

	// Create Map Manager Object (OSMMap) instance
	<%= portletNameSpace%>OSMMap_<%= occurenceId %>_ = new OSMMap('<%= portletNameSpace%>', '<%= occurenceId %>', '<%= startLatitude %>', '<%= startLongitude %>', '<%= startZoom %>');
	
	// Location Marker is not activated by default. Activate it if required by the tag.
	<%
	if (locationMarkerActive) {
	%>
		<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.activateLocationMarker();
	<%
	}
	%>
	
	// Never activate binding function if not required
	<%
	if ((locationLatitudeFieldIdBind != "") || (locationLongitudeFieldIdBind != "") || (locationZoomFieldIdBind != "")) {
	%>
		// Activate the event ContextMenu on the Map
		<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getMap().on('contextmenu', function(event) {
			<%= portletNameSpace%>updateLocationDependencies(event.latlng, event);
		});
		
		// Activate the event ZoomEnd on the Map
		<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getMap().on('zoomend', function(event) {
			<%
			if (locationMarkerActive) {
			%>
				// Use Marker as Reference
				<%= portletNameSpace%>updateLocationDependencies(<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getLocationMarker().getLatLng(), event);
			<%
			} else {
			%>
				// Use Map center as Reference
				<%= portletNameSpace%>updateLocationDependencies(<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getCenter(), event);
			<%
			}
			%>
		});
		
		// Activate the event DragEnd on the Marker if it is active
		<%
		if (locationMarkerActive) {
			%>
			
			<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getLocationMarker().on('dragend', function(event) {
				<%= portletNameSpace%>updateLocationDependencies(<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getLocationMarker().getLatLng(), event);
			});
		<%
		} else {
			%>
			
			<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getMap().on('mousemove', function(event) {
				<%= portletNameSpace%>updateLocationDependencies(<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getCenter(), event);
			});
			
		<%
		}
		%>
		
		function <%= portletNameSpace%>updateLocationDependencies(latlng, event) {
			
			<%
			if (locationMarkerActive) {
			%>	
				if (event.type != 'zoomend') {
					<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.updateLocationMarkerDisplay(latlng);
				}
			<%
			}
			%>
					
			// Update bindings for Latitude and Longitude Fields if asked by taglib 
			<%
				if ((locationLatitudeFieldIdBind != "") && (locationLongitudeFieldIdBind != "")) {
					%>
					
					<%
					if (locationMarkerActive) {
					%>	
					
						document.getElementById('<%= locationLatitudeFieldIdBind  %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getMarkerLatitude();
						document.getElementById('<%= locationLongitudeFieldIdBind %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getMarkerLongitude();
					<%
					} else {
					%>
						document.getElementById('<%= locationLatitudeFieldIdBind  %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getCenterLatitude();
						document.getElementById('<%= locationLongitudeFieldIdBind %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getCenterLongitude();
					<%
					}
				}
			%>
			
			// Update bindings for Zoom Field if asked by taglib 
			<%
				if (locationZoomFieldIdBind != "") {
					%>
					document.getElementById('<%= locationZoomFieldIdBind  %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getZoom();
					<%
				}
			%>
			
			// Update bindings for Location Field if asked by taglib 
			<%		
				if (locationLocationFieldIdBind != "") {
					%>
					if (<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getLocationLocationFieldBind()) {
						<%
						if (locationMarkerActive) {
						%>
							document.getElementById('<%= locationLocationFieldIdBind  %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getLocationMarkerContent();
						<%
						} else {
						%>
							document.getElementById('<%= locationLocationFieldIdBind  %>').value = <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.meetupsMapReverseSearch(latlng, ', ');
						<%
						}
						%>
					}
					<%
				}
			%>
		};
		
	<%
	}
	%>
	
	// Activate the event Change on the Location Field if Binded
	<%
	if (locationLocationFieldIdBind != "") {
		%>
		
		AUI().use('event', 'node', function(A) {
			A.one('#<%= locationLocationFieldIdBind  %>').on('change', function() {
				if (document.getElementById('<%= locationLocationFieldIdBind  %>').value != <%= portletNameSpace%>OSMMap_<%= occurenceId %>_.getLocationMarkerContent()) {
					<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.unbindLocationField();
				}
			});
		});
	
	<%
	}
	%>
	
	// Add plots if available
	<%
	if ((plotList != null) && (plotList.size() > 0)) {
		Iterator<GeolocalizationPlot> iter = plotList.iterator();
		while (iter.hasNext()) {
			GeolocalizationPlot plot = iter.next();
			%>
			<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.displayPlotMarker('<%= plot.getDescription()  %>', '<%= plot.getLatitude() %>', '<%= plot.getLongitude() %>');
			<%
		}
	}
	%>
	
</script>