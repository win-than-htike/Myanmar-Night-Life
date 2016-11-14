package myanmarnightlife.lower.team1.data;

/**
 * Created by user on 11/11/16.
 */

public class Review {
  public String review;

  public String places_id;
  public Review() {
  }

  public Review(String review,String id) {
    this.review = review;


    this.places_id = id;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }


  public java.lang.String getPlaces_id() {
    return places_id;
  }

  public void setPlaces_id(java.lang.String places_id) {
    this.places_id = places_id;
  }



}
