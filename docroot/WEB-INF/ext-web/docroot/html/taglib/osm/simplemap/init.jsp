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

<%
String portletNameSpace = (String)request.getAttribute("lsp-osm:simplemap:portletNameSpace");
String occurenceId = (String)request.getAttribute("lsp-osm:simplemap:occurenceId");
String startLatitude = (String)request.getAttribute("lsp-osm:simplemap:startLatitude");
String startLongitude = (String)request.getAttribute("lsp-osm:simplemap:startLongitude");
String startZoom = (String)request.getAttribute("lsp-osm:simplemap:startZoom");
String mapWidth = (String)request.getAttribute("lsp-osm:simplemap:mapWidth");
String mapHeight = (String)request.getAttribute("lsp-osm:simplemap:mapHeight");
String displayText = (String)request.getAttribute("lsp-osm:simplemap:displayText");
%>