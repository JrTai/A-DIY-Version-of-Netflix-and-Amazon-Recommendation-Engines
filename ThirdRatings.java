
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        // default constructor
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        if (count >= minimalRaters) {
            return total_score / count;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> rating_list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movies) {
           double score = getAverageByID(id, minimalRaters);
           // This might cause bug, if every rater rate 0.0
           if (score > 0.0) {
               Rating rating = new Rating(id, score);
               rating_list.add(rating);
           }
        }
        return rating_list;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> rating_list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movies) {
           double score = getAverageByID(id, minimalRaters);
           boolean bl = filterCriteria.satisfies(id);
           // This might cause bug, if every rater rate 0.0
           if (score > 0.0 && bl) {
               Rating rating = new Rating(id, score);
               rating_list.add(rating);
           }
        }
        return rating_list;
    }

}
