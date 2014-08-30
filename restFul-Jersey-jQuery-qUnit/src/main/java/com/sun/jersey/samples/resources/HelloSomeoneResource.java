package com.sun.jersey.samples.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("helloSomeone")
public class HelloSomeoneResource {
	
	@GET
	public String hello(@DefaultValue("Mago") @QueryParam("inputName") String name) {
		return "Hello " + name;
	}
	
}
