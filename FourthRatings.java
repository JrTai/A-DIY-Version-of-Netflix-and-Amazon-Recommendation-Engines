
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
        
    private double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        double total_score = 0;
        for (Rater rater : RaterDatabase.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double dotproduct = 0;
        ArrayList<String> movieItemList = me.getItemsRated(); 
        for (String id : movieItemList) {
            if (r.hasRating(id)) {
                dotproduct += (me.getRating(id) - 5.) * (r.getRating(id) - 5.);
            }
        }
        return dotproduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> sortedList = new ArrayList<Rating>();
        for (Rater rater : RaterDatabase.getRaters()) {
           if (!rater.getID().equals(id)) {
               double dotproduct = dotProduct(RaterDatabase.getRater(id), rater);
               if (dotproduct > 0) {
                   Rating rating = new Rating(rater.getID(), dotproduct);
                   sortedList.add(rating);
               }
           }
        }
        // start to sort sortedList from high to low
        for (int idx = 0; idx < sortedList.size(); idx++) {
            for (int jdx = 0; jdx < sortedList.size() - 1; jdx++) {
                if (sortedList.get(jdx + 1).getValue() > sortedList.get(jdx).getValue()) {
                    Rating temp_rating = sortedList.get(jdx);
                    sortedList.set(jdx, sortedList.get(jdx + 1));
                    sortedList.set(jdx + 1, temp_rating);
                }
            }
        }
        return sortedList;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
       ArrayList<Rating> sortedMovieList = new ArrayList<Rating>();
       ArrayList<Rating> similarRaters = getSimilarities(id);
       ArrayList<Rating> topRaters = new ArrayList<Rating>();
       for (int idx = 0; idx < numSimilarRaters; idx++) {
           topRaters.add(similarRaters.get(idx));     
       }
       System.out.println("Top Raters size: " + topRaters.size());
       // weight every movie in MovieDatabase
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       for (String movieID : movies) {
           double count_raters = 0;
           double score = 0;
           for (Rating rater : topRaters) {
               Rater topRater = RaterDatabase.getRater(rater.getItem()); 
               if (topRater.hasRating(movieID)) {
                   count_raters += 1;
                   score += rater.getValue() * topRater.getRating(movieID);
               }
           }
           if (count_raters >= minimalRaters) {
               double weightedAverage = score / count_raters;
               Rating rating = new Rating(movieID, weightedAverage);
               sortedMovieList.add(rating);
           }
       }
       // start to sort sortedMovieList from high to low
       for (int idx = 0; idx < sortedMovieList.size(); idx++) {
            for (int jdx = 0; jdx < sortedMovieList.size() - 1; jdx++) {
                if (sortedMovieList.get(jdx + 1).getValue() > sortedMovieList.get(jdx).getValue()) {
                    Rating temp_rating = sortedMovieList.get(jdx);
                    sortedMovieList.set(jdx, sortedMovieList.get(jdx + 1));
                    sortedMovieList.set(jdx + 1, temp_rating);
                }
            }
        }
       return sortedMovieList;
    }
    
    /* // finish process then use filter (maybe wrong)
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> sortedMovieList = getSimilarRatings(id, numSimilarRaters, minimalRaters);
        ArrayList<Rating> rating_list = new ArrayList<Rating>();
        for (Rating rating : sortedMovieList) {
           boolean bl = filterCriteria.satisfies(rating.getItem());
           if (bl) {
               Rating filteredRating = new Rating(rating.getItem(), rating.getValue());
               rating_list.add(filteredRating);
           }
        }
        return rating_list;
    }
    */
   
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
       ArrayList<Rating> sortedMovieList = new ArrayList<Rating>();
       ArrayList<Rating> similarRaters = getSimilarities(id);
       ArrayList<Rating> topRaters = new ArrayList<Rating>();
       for (int idx = 0; idx < numSimilarRaters; idx++) {
           topRaters.add(similarRaters.get(idx));     
       }
       System.out.println("Top Raters size: " + topRaters.size());
       // weight every movie in MovieDatabase
       ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
       for (String movieID : movies) {
           double count_raters = 0;
           double score = 0;
           for (Rating rater : topRaters) {
               Rater topRater = RaterDatabase.getRater(rater.getItem()); 
               if (topRater.hasRating(movieID)) {
                   count_raters += 1;
                   score += rater.getValue() * topRater.getRating(movieID);
               }
           }
           if (count_raters >= minimalRaters) {
               double weightedAverage = score / count_raters;
               Rating rating = new Rating(movieID, weightedAverage);
               sortedMovieList.add(rating);
           }
       }
       // start to sort sortedMovieList from high to low
       for (int idx = 0; idx < sortedMovieList.size(); idx++) {
            for (int jdx = 0; jdx < sortedMovieList.size() - 1; jdx++) {
                if (sortedMovieList.get(jdx + 1).getValue() > sortedMovieList.get(jdx).getValue()) {
                    Rating temp_rating = sortedMovieList.get(jdx);
                    sortedMovieList.set(jdx, sortedMovieList.get(jdx + 1));
                    sortedMovieList.set(jdx + 1, temp_rating);
                }
            }
        }
       return sortedMovieList;
    }
}
