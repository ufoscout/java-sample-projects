/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rest.domain;

/**
 *
 * @author ufo
 */
public class Review {
    
    private long id;
    private String phoneId;
    private String title;
    private String review;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }
    
}
