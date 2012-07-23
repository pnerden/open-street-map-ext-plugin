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

<%@ include file="/html/taglib/osm/simplemap/init.jsp" %>

<c:if test="<%=((startLatitude.length() > 0) && (startLongitude.length() > 0)) %>">
	<div class="osm-global" id="<%= portletNameSpace%>OSM_<%= occurenceId %>_">
		<aui:layout cssClass="osm-navigation">
			<aui:column cssClass="osm-mapView osm-simplemapView" columnWidth="100" first="true">
				<div id="<%= portletNameSpace%>map_<%= occurenceId %>_" style="width:<%= mapWidth%>px;height:<%= mapHeight%>px;"></div>
				<p>Map data &#169; OpenStreetMap contributors<br />Nominatim Search Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png"></p>
			</aui:column>
		</aui:layout>
	</div>
	
	<script>
	
		// Create Map Manager Object (OSMMap) instance
		<%= portletNameSpace%>OSMMap_<%= occurenceId %>_ = new OSMMap('<%= portletNameSpace%>', '<%= occurenceId %>', '<%= startLatitude %>', '<%= startLongitude %>', '<%= startZoom %>');
		<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.displayPlotMarker('<%= displayText %>', '<%= startLatitude %>', '<%= startLongitude %>');
		
		// Add plots if available
		<%
		if ((plotList != null) && (plotList.size() > 0)) {
			Iterator<GeolocalizationPlot> iter = plotList.iterator();
			while (iter.hasNext()) {
				GeolocalizationPlot plot = iter.next();
				%>
				<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.displayPlotMarker('<%= plot.getDescription() %>', '<%= plot.getLatitude() %>', '<%= plot.getLongitude() %>');
				<%
			}
		}
		%>
		
	</script>
</c:if>
