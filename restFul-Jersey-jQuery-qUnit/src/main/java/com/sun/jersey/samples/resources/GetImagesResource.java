package com.sun.jersey.samples.resources;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("getImages/{imagename}")
public class GetImagesResource {
	
	@GET
	@Produces("image/*")
	public Response getImage(@PathParam("imagename") String imagename) {
	    File f = new File("src/main/webapp/images/" + imagename + ".jpg");

	    if (!f.exists()) {
	    	System.out.println("file " + f.getPath() + " not found" );
	        throw new WebApplicationException(404);
	    }

	    String mt = new MimetypesFileTypeMap().getContentType(f);
	    System.out.println("mimetype: " + mt);
	    return Response.ok(f, mt).build();
	}

	
}
