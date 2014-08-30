package ufo.gae.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

@Controller
@Path("hello")
public class HelloWorldController {

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public String get() {
		System.out.println("REST HELLO WORLD CALLED!!!!");
		return "Hello World!";
	}

}
