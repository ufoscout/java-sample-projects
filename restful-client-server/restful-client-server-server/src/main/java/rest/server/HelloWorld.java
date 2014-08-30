package rest.server;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import rest.commos.Constants;

/**
 * 
 * @author Francesco Cina'
 *
 * 30 May 2012
 */
@Path(Constants.URI_HELLOWORLD)
public class HelloWorld {

    @GET 
    @Produces(MediaType.TEXT_HTML)
    public String sayHello() {
        return Constants.HELLOWORLD_MESSAGE;
    }
}