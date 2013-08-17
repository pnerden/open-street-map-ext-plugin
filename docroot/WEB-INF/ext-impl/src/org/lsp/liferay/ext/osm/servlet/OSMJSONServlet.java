package org.lsp.liferay.ext.osm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lsp.liferay.ext.osm.util.GeolocalizationUtil;
import org.lsp.liferay.ext.osm.util.OSMConstants;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

public class OSMJSONServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291905216543981255L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			if (PortalUtil.getUser(req) != null) {
				resp.setContentType("text/javascript; charset=UTF-8");
				resp.setHeader("Cache-Control", "no-cache");
				
				switch (Integer.parseInt(req.getParameter("mapSearchAction"))) {
					case OSMConstants.ACTION_SEARCHMAP:
						searchMap(req.getParameter("mapSearchQuery"),resp);
						break;
					case OSMConstants.ACTION_REVERSE_SEARCHMAP:
						searchMap(req.getParameter("latitude"),req.getParameter("longitude"), resp);
						break;
				}		
			}
		} catch (PortalException e1) {
			e1.printStackTrace();
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void searchMap(String query, HttpServletResponse resp) throws IOException {
		try {
			PrintWriter out = resp.getWriter();		
			out.write(GeolocalizationUtil.getAsJSONArrayByQuery(query).toString());
			out.flush();
	        out.close();
		} catch (SystemException e) {
			resp.sendError(500);
		} catch (IOException e) {
			resp.sendError(500);
		}
	}
	
	private void searchMap(String latitude, String longitude, HttpServletResponse resp) throws IOException {
		try {
			PrintWriter out = resp.getWriter();
			out.write(GeolocalizationUtil.getAsJSONObjectByCoordinates(latitude, longitude).toString());
			out.flush();
	        out.close();
		} catch (SystemException e) {
			resp.sendError(500);
		} catch (IOException e) {
			resp.sendError(500);
		}
	}
	
}
