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

<%@ include file="/html/taglib/osm/init.jsp" %>

<div class="osm-global" id="<%= portletNameSpace%>OSM_<%= occurenceId %>_">
	<aui:layout cssClass="osm-mapSearch">
		<aui:column columnWidth="50" first="true">
			<input type="text" id="<%= portletNameSpace%>MapSearch_<%= occurenceId %>_" value="" /> <button name="<%= portletNameSpace%>SearchMap_<%= occurenceId %>_" type="button" onClick="<%= portletNameSpace%>OSMMap_<%= occurenceId %>_.meetupsMapSearch()"><%= LanguageUtil.get(pageContext, "search") %></button>
		</aui:column>
		<aui:column columnWidth="50" last="true">
		</aui:column>
	</aui:layout>
	<aui:layout cssClass="osm-navigation">
		<aui:column cssClass="osm-mapView" columnWidth="50" first="true">
			<div id="<%= portletNameSpace%>map_<%= occurenceId %>_" style="width:256px;height:256px;"></div>
			<p>Map data &#169; OpenStreetMap contributors<br />Nominatim Search Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png"></p>
		</aui:column>
		<aui:column cssClass="osm-mapSuggestions" columnWidth="45" last="true">
			<p><%= LanguageUtil.get(pageContext, "suggestions-of-locations") %></p>
			<div id="<%= portletNameSpace%>MapSuggestions_<%= occurenceId %>_"></div>
		</aui:column>
	</aui:layout>
</div>

<script>

	<%= portletNameSpace%>OSMMap_<%= occurenceId %>_ = new OSMMap("<%= portletNameSpace%>", "<%= occurenceId %>", "<%= startLatitude %>", "<%= startLongitude %>", "<%= startZoom %>");
	
</script>