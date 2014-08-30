/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import server.rest.domain.Review;

/**
 *
 * @author ufo
 */
@Path(value="review")
public class ReviewController {
    
    private static final List<Review> REVIEWS = new ArrayList<Review>();
    
    @Path(value="all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAll() {
        return REVIEWS;
    }
    
    @Path(value="get/{phoneId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> get(@DefaultValue("") @PathParam(value="phoneId") String phoneId) {
        List<Review> phoneReviews = new ArrayList<Review>();
        for (Review review : REVIEWS) {
            if (phoneId.equals(review.getPhoneId())) {
                phoneReviews.add(review);
            }
        }
        return phoneReviews;
    }
    
    @Path(value="add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Review add(Review review) {
        if ( review != null ) {
            review.setId(REVIEWS.size());
            REVIEWS.add(review);
            return review;
        }
        return null;
    }
}
