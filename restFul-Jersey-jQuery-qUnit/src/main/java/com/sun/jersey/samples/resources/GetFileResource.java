package com.sun.jersey.samples.resources;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("getFile/{filename}")
public class GetFileResource {
	
	@GET
	public Response getFile(@PathParam("filename") String filename) {
	    File file = new File("src/main/webapp/file/" + filename );

	    if (!file.exists()) {
	    	System.out.println("file " + file.getPath() + " not found" );
	        throw new WebApplicationException(404);
	    }

	    String mt = new MimetypesFileTypeMap().getContentType(file);
	    System.out.println("mimetype: " + mt);
	    return Response.ok(file, mt).build();
	}

	
}
