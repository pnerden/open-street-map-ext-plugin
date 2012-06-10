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

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%
String portletNameSpace = (String)request.getAttribute("lsp-osm:map:portletNameSpace");
String occurenceId = (String)request.getAttribute("lsp-osm:map:occurenceId");
String startLatitude = (String)request.getAttribute("lsp-osm:map:startLatitude");
String startLongitude = (String)request.getAttribute("lsp-osm:map:startLongitude");
String startZoom = (String)request.getAttribute("lsp-osm:map:startZoom");
%>