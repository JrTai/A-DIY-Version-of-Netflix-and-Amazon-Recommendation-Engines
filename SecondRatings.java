 


/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        // default constructor
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        double total_score = 0;
        for (EfficientRater rater : myRaters) {
            if (rater.hasRating(id)) {
                count += 1;
                total_score += rater.getRating(id);
            }
        }
        if (count > minimalRaters) {
            return total_score / count;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> rating_list = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
           double score = getAverageByID(movie.getID(), minimalRaters);
           // This might cause bug, if every rater rate 0.0
           if (score > 0.0) {
               Rating rating = new Rating(movie.getID(), score);
               rating_list.add(rating);
           }
        }
        return rating_list;
    }
    
    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID doesn't exit, sorry!";
    }
    
    public String getID(String title) {
       for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
       return "Title doesn't exit, sorry!";
    }
}
