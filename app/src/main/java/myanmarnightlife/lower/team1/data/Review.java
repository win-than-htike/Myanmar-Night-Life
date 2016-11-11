package myanmarnightlife.lower.team1.data;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by user on 11/11/16.
 */

public class Review {
  public String review;
  public FirebaseUser user;
  public String places_id;
  public Review() {
  }

  public Review(String review, FirebaseUser user,String id) {
    this.review = review;
    this.user = user;
    this.places_id = id;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public FirebaseUser getUser() {
    return user;
  }

  public java.lang.String getPlaces_id() {
    return places_id;
  }

  public void setPlaces_id(java.lang.String places_id) {
    this.places_id = places_id;
  }

  public void setUser(FirebaseUser user) {
    this.user = user;
  }

}
