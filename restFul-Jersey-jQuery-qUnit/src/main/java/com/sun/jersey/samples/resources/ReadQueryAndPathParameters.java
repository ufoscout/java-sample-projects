package com.sun.jersey.samples.resources;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/apr/2010
 */
@Path("/queryAndParams/{other}")
public class ReadQueryAndPathParameters {

	@GET
	public String get(@Context UriInfo ui) {
		System.out.println(getClass().getSimpleName() + " - > Chiamato metodo GET");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    MultivaluedMap<String, String> pathParams = ui.getPathParameters();
	    
	    StringBuffer result = new StringBuffer();

	    System.out.println("Setting query params:");
	    result.append("<br/><br/><h2>Query params:</h2>");
	    for( Entry<String, List<String>> entry : queryParams.entrySet() ) {
	    	System.out.println(getClass().getSimpleName() + " -> [" + entry.getKey() + "] = [" + entry.getValue() +"]");
	    	result.append(entry.getKey());
	    	result.append(" = ");
	    	result.append(entry.getValue());
	    	result.append("<br/>");
	    }
	    
	    System.out.println("Setting path params:");
	    result.append("<br/><br/><h2>Path params:</h2>");
	    for( Entry<String, List<String>> entry : pathParams.entrySet() ) {
	    	System.out.println(getClass().getSimpleName() + " -> [" + entry.getKey() + "] = [" + entry.getValue() +"]");
	    	result.append(entry.getKey());
	    	result.append(" = ");
	    	result.append(entry.getValue());
	    	result.append("<br/>");
	    }
	    
	    return result.toString();
	}
	
	@SuppressWarnings("rawtypes")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String post(@Context HttpServletRequest request, MultivaluedMap<String, String> form) {
		System.out.println(getClass().getSimpleName() + " - > Chiamato metodo POST");
		
		HttpSession session = request.getSession();
		System.out.println("Session is " + session);
		session.setAttribute("Attr1-" + new Date().getTime(), new Date());

		for (Enumeration attributes = session.getAttributeNames(); attributes.hasMoreElements();) {
			String attribute = (String) attributes.nextElement();
			System.out.println("Preso dalla request attribute: " + attribute + " = " + session.getAttribute(attribute));
		}
		
		for (Enumeration names = request.getParameterNames(); names.hasMoreElements();  ) {
			String name = (String) names.nextElement();
			System.out.println("Preso dalla request parameters: " + name + " = " + request.getParameter(name));
		}
		
		System.out.println("form size: " + form.size());
	    
	    StringBuffer result = new StringBuffer();

	    System.out.println("Setting form params:");
	    result.append("<br/><br/><h2>Form params:</h2>");
	    for( Entry<String, List<String>> entry : form.entrySet() ) {
	    	System.out.println(getClass().getSimpleName() + " -> [" + entry.getKey() + "] = [" + entry.getValue() +"]");
	    	result.append(entry.getKey());
	    	result.append(" = ");
	    	result.append(entry.getValue());
	    	result.append("<br/>");
	    }
	    
	    return result.toString();
	}
	
}

