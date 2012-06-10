package org.lsp.liferay.ext.osm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lsp.liferay.ext.osm.util.GeolocalizationUtil;
import org.lsp.liferay.ext.osm.util.OSMConstants;
import org.springframework.xml.xpath.XPathException;

public class OSMJSONServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291905216543981255L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/javascript");
		resp.setHeader("Cache-Control", "no-cache");
		
		switch (Integer.parseInt(req.getParameter("mapSearchAction"))) {
			case OSMConstants.ACTION_SEARCHMAP:
				searchMap(req.getParameter("mapSearchQuery"),resp);
				break;
		}		
		
	}
	
	private void searchMap(String query, HttpServletResponse resp)	throws IOException, XPathException {
		PrintWriter out = resp.getWriter();
		
		out.write(GeolocalizationUtil.getAsJSONArrayByQuery(query).toString());

		out.flush();
        out.close();
	}
	
}
