var FlagIcon = L.Icon.extend({
    iconUrl: '/html/common/leaflet/images/flag.png',
    iconSize: new L.Point(48, 48),
    iconAnchor: new L.Point(7, 47),
    popupAnchor: new L.Point(0, -40)
});

OSMMap = function(portletNameSpace, occurenceId, startLatitude, startLongitude, startZoom) {
	
	var map;
	var mapSearchField;
	var mapSuggestionsDiv;
	var searchURL;
	var reverseSearchURL;
	var osmUrl;
	var osmAttrib;
	var osm;
	
	var locationMarker;
	var locationMarkerContent;
	var markerLayer;
	var suggestions;
	
	var locationLocationFieldBind;
	
	var parent = this;
	
	this.init = function() {
		// set up the map
		map = new L.Map(portletNameSpace+"map_"+occurenceId+"_");
		mapSearchField = document.getElementById(portletNameSpace+"MapSearch_"+occurenceId+"_");
		mapSuggestionsDiv = document.getElementById(portletNameSpace+"MapSuggestions_"+occurenceId+"_");
		searchURL = '/api/osm/?mapSearchAction=1&mapSearchQuery={q}';
		reverseSearchURL = '/api/osm/?mapSearchAction=2&latitude={lat}&longitude={lon}';
		
		locationLocationFieldBind=true;
		
		// create the tile layer with correct attribution
		osmUrl='http://otile4.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png';
		osm = new L.TileLayer(osmUrl, {minZoom: 1, maxZoom: 18, attribution: ""});
		
		markerLayer = new Array();
		suggestions = new Array();
		
		// Fire in the hole !!
		this.goTo(startLatitude, startLongitude, startZoom);
	}
	
	// Business functions
	
	this.activateLocationMarker = function() {
		var icon = new FlagIcon();
		locationMarker = new L.Marker(new L.LatLng(startLatitude, startLongitude), {icon: icon, draggable: true});
		map.addLayer(locationMarker);
		this.updateLocationMarkerDisplay(new L.LatLng(startLatitude, startLongitude));
	};
	
	this.addMapSuggestion = function(location) {
		var child = document.createElement('p');
		var anchor = this.buildZoomToAnchor(location, ", ");
		child.appendChild(anchor);
		mapSuggestionsDiv.appendChild(child);
	};
	
	this.clearMapSuggestions = function() {
		if ( mapSuggestionsDiv.hasChildNodes() ) {
    		while ( mapSuggestionsDiv.childNodes.length >= 1 ) {
        		mapSuggestionsDiv.removeChild( mapSuggestionsDiv.firstChild );       
    		}
    		suggestions = [];
		}
	};
	
	this.clearMarkers = function() {
		for (var i=0;i<markerLayer.length;i++) {
			map.removeLayer(markerLayer[i]);
		}
		markerLayer = [];
	};
	
	this.displayMarker = function (location) {
		var anchor = this.buildZoomToAnchor(location, "<br />");
		
		var markerLocation = new L.LatLng(location.lat, location.lng);
		var marker = new L.Marker(markerLocation);
		
		marker.bindPopup(anchor).openPopup();
		markerLayer.push(marker);
		map.addLayer(marker);
	};
	
	this.getCenterLatitude = function() {
		return map.getCenter().lat;
	};
	
	this.getCenterLongitude = function() {
		return map.getCenter().lng;
	};
	
	this.getMarkerLatitude = function() {
		return locationMarker.getLatLng().lat;
	};
	
	this.getMarkerLongitude = function() {
		return locationMarker.getLatLng().lng;
	};
	
	this.goTo = function (latitude,longitude,zoom) {
		var defaultCoordinates = new L.LatLng(latitude, longitude);
		map.setView(defaultCoordinates, zoom).addLayer(osm);
	};
	
	this.meetupsMapReverseSearch = function(latlng, separator) {
		var url = reverseSearchURL.replace("{lat}", latlng.lat).replace("{lon}", latlng.lng);
		var parent = this;
		var result;
		
		AUI().use('aui-io-request', function(A) {
			A.io.request(url, {
				dataType: 'json',
				sync: true,
				method:	'get',
				headers: {'Content-Type':'charset=utf-8'},
				on: { 
					success: function() {
				  		result = parent.meetupsMapReverseSearchSuccess(this.get('responseData'), separator);
					}
			  	} 
			}); 
		});
		
		return result;
	};
	
	this.meetupsMapReverseSearchSuccess = function(responseData, separator) {
		return this.buildLocationDescription(responseData, separator);
	};
	
	this.meetupsMapSearch = function() {
		var query = mapSearchField.value;
		var url = searchURL.replace("{q}",query);
		var parent = this;
		
		AUI().use('aui-io-request', function(A) {
			A.io.request(url, {
				dataType: 'json', 
				method:	'get',
				headers: {'Content-Type':'charset=utf-8'},
				on: { 
					success: function() {
				  		parent.meetupsMapSearchSuccess(this.get('responseData'));				  		
					}
			  	} 
			}); 
		});
	};
	
	this.meetupsMapSearchSuccess = function(responseData) {
		this.clearMarkers();
		this.clearMapSuggestions();
		for (var i=0;i<responseData.length;i++) {
			var location = responseData[i];
			suggestions[i] = location;
			this.displayMarker(location);
			this.addMapSuggestion(location);
		}
		var box = suggestions[0].box.split(',');
		this.zoomTo(box[1], box[2], box[0], box[3]);
	};
	
	this.unbindLocationField = function() {
		locationLocationFieldBind=false;
	};
	
	this.updateLocationMarkerDisplay = function(latlng) {
		locationMarker.setLatLng(latlng);
		locationMarkerContent = this.meetupsMapReverseSearch(latlng, ", ");
		locationMarker.bindPopup(locationMarkerContent.replace(/, /g, "<br />")).openPopup();
	};
	
	this.zoomTo = function (northEastLat, northEastLng, southWestLat, southWestLng) {
		var northEast = new L.LatLng(northEastLat,northEastLng);
		var southWest = new L.LatLng(southWestLat,southWestLng);
    	var bounds = new L.LatLngBounds(southWest, northEast);
		map.fitBounds(bounds);
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Accessors
	
	this.getMap = function() {
		return map;
	};
	
	this.getLocationMarker = function() {
		return locationMarker;
	};
	
	this.getLocationMarkerContent = function() {
		return locationMarkerContent;
	};
	
	this.getLocationLocationFieldBind = function() {
		return locationLocationFieldBind;
	};
	
	this.getMapSearchField = function() {
		return this.mapSearchField;
	};
	
	this.getMapSuggestionsDiv = function() {
		return this.mapSuggestionsDiv;	
	};
	
	this.setMapSearchField = function(mapSearchFieldId) {
		this.mapSearchField = document.getElementById(mapSearchFieldId);
	};
	
	this.getZoom = function() {
		return map.getZoom();
	};
	
	
	
	/// Internal purpose functions
	
	this.buildLocationDescription = function(responseData, separator) {
		var result = "";
		if (responseData.road != null) {
			result += responseData.road+separator;
		}
		if (responseData.zipcode != null) {
			result += responseData.zipcode+separator;
		}
		if (responseData.city != null) {
			result += responseData.city+separator;
		}
		if (responseData.county != null ) {
			result += responseData.county+separator;
		}
		if (responseData.state != null) {
			result += responseData.state+separator;
		}
		if (responseData.country != null) {
			result += responseData.country;
		}
		return result;
	};
	
	this.buildZoomToAnchor = function(location, separator) {
		var box = location.box.split(',');
		var onClick = portletNameSpace+"OSMMap_"+occurenceId+"_.zoomTo('"+box[1]+"','"+box[2]+"','"+box[0]+"','"+box[3]+"')";
		var anchor = document.createElement('a');
		anchor.innerHTML = this.buildLocationDescription(location, separator);
		anchor.href = "#";
		anchor.setAttribute("onclick", onClick);
		return anchor;
	};
	
	this.init();
	
}