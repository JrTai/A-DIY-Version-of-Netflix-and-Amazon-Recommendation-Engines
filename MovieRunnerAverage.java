 


/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings secondrating = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        //SecondRatings secondrating = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        //System.out.println(secondrating.getMovieSize());
        //System.out.println(secondrating.getRaterSize());
        ArrayList<Rating> rating_list = secondrating.getAverageRatings(12);
        System.out.println("Total movie above 12: " + rating_list.size());
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = idx + 1; jdx < rating_list.size(); jdx++) {
                if (rating_list.get(jdx - 1).getValue() > rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx - 1));
                    rating_list.set(jdx - 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            //System.out.println(rating.getValue() + " " + secondrating.getTitle(rating.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
       SecondRatings secondrating = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
       //SecondRatings secondrating = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
       ArrayList<Rating> rating_list = secondrating.getAverageRatings(0);
       String title = "The Hangover Part III";
       for (Rating rating : rating_list) {
           if (secondrating.getTitle(rating.getItem()).equals(title)) {
               System.out.println(rating.getValue() + " " + title); 
           } 
       }
       title = "The Purge";
       for (Rating rating : rating_list) {
           if (secondrating.getTitle(rating.getItem()).equals(title)) {
               System.out.println(rating.getValue() + " " + title); 
           } 
       }
       title = "Spring Breakers";
       for (Rating rating : rating_list) {
           if (secondrating.getTitle(rating.getItem()).equals(title)) {
               System.out.println(rating.getValue() + " " + title); 
           } 
       }
       title = "Mama";
       for (Rating rating : rating_list) {
           if (secondrating.getTitle(rating.getItem()).equals(title)) {
               System.out.println(rating.getValue() + " " + title); 
           } 
       }
       title = "Identity Thief";
       for (Rating rating : rating_list) {
           if (secondrating.getTitle(rating.getItem()).equals(title)) {
               System.out.println(rating.getValue() + " " + title); 
           } 
       }
    }
    
    /*
    public void testAverageByID() {
        //SecondRatings rating = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        SecondRatings rating = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        System.out.println(rating.getAverageByID("1798709", 3));
    }
    */

}
