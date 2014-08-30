/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved. 
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License("CDDL") (the "License").  You may not use this file
 * except in compliance with the License. 
 * 
 * You can obtain a copy of the License at:
 *     https://jersey.dev.java.net/license.txt
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * When distributing the Covered Code, include this CDDL Header Notice in each
 * file and include the License file at:
 *     https://jersey.dev.java.net/license.txt
 * If applicable, add the following below this CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 *     "Portions Copyrighted [year] [name of copyright owner]"
 */

package com.sun.jersey.samples.resources;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/apr/2010
 */
@Path("/cookiesAndHeaders")
public class ReadCookiesAndHeadersResource {

    // The Java method will process HTTP GET requests
    @GET
    public String get(@Context HttpHeaders hh) {
        MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
        Map<String, Cookie> cookies = hh.getCookies();
        
        
	    StringBuffer result = new StringBuffer();

	    result.append("<br/><br/><h2>-Header params:</h2>");
	    for( Entry<String, List<String>> entry : headerParams.entrySet() ) {
	    	result.append(entry.getKey());
	    	result.append(" = ");
	    	result.append(entry.getValue());
	    	result.append("<br/>");
	    }
	    
	    result.append("<br/><br/><h2>Cookie:</h2>");
	    for( Entry<String, Cookie> entry : cookies.entrySet() ) {
	    	result.append(entry.getKey());
	    	result.append(" = ");
	    	result.append(entry.getValue());
	    	result.append("<br/>");
	    }
	    
	    return result.toString();
        
    }

}