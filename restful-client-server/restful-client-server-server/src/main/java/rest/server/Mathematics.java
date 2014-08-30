package rest.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rest.commos.Constants;

/**
 * 
 * @author Francesco Cina'
 *
 * 30 May 2012
 */
@Path(Constants.URI_MATHEMATICS)
public class Mathematics {
	
    @GET
    @Path(Constants.URI_MATHEMATICS_ADD)
    @Produces(MediaType.TEXT_HTML)
    public String add(@QueryParam("valueOne") int valueOne, @QueryParam("valueTwo") int valueTwo) {
        return "" + (valueOne + valueTwo);
    }

    @POST 
    @Path(Constants.URI_MATHEMATICS_MULTIPLY)
    @Produces(MediaType.TEXT_HTML)
    public String multiply(@FormParam("valueOne") int valueOne, @FormParam("valueTwo") int valueTwo) {
        return "" + (valueOne * valueTwo);
    }
}
