 


/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class Test {
    
    public void testFirstRatingloadMovies() {
        FirstRatings test = new FirstRatings();
        ArrayList<Movie> list = test.loadMovies("ratedmoviesfull.csv");
        //ArrayList<Movie> list = test.loadMovies("ratedmovies_short.csv");
        int count_type = 0;
        int count_min = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Movie movie : list) {
            String[] genre = movie.getGenres().split(", ");
            //System.out.println(movie.getGenres());
            for (String type : genre) {
                //System.out.println(type);
                if (type.equals("Comedy")) {
                   count_type += 1;
                }
            }
            if (movie.getMinutes() > 150) {
                count_min += 1;
            }
            if (map.containsKey(movie.getDirector())) {
                int num = map.get(movie.getDirector());
                map.put(movie.getDirector(), num + 1);
            }
            else {
                map.put(movie.getDirector(), 1);
            }
            
        }
        System.out.println("Type(Comedy): " + count_type);
        System.out.println("Max minutes: " + count_min);
        System.out.println("Total Directors: " + map.size());
        int max_director = 0;
        String directorking = "";
        for (String director : map.keySet()) {
            if (map.get(director) > max_director) {
                directorking = director;
                max_director = map.get(director);
            }
        }
        System.out.println("Max Director: " + directorking);
        System.out.println("Total movie: " + max_director);
    }
    
    public void testFirstRatingloadRaters() {
        FirstRatings test = new FirstRatings();
        ArrayList<EfficientRater> list = test.loadRaters("ratings.csv");
        //ArrayList<Rater> list = test.loadRaters("ratings_short.csv");
        System.out.println("Total raters: " + list.size());
        int count_rater = 0;
        int count_ifrated = 0;
        String item = "1798709";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String rater_id_most = "";
        int max_rating = 0;
        for (EfficientRater rater : list) {
            if (rater.getID().equals("193")) {
                count_rater = rater.numRatings();
            }
            if (rater.hasRating(item)) {
                count_ifrated += 1;
            }
            ArrayList<String> movie = rater.getItemsRated();
            if (movie.size() > max_rating) {
                max_rating = movie.size();
                rater_id_most = rater.getID();
            }
            for (String ID : movie) {
                if (map.containsKey(ID)) {
                    map.put(ID, map.get(ID) + 1);
                }
                else {
                    map.put(ID, 1);
                }
            }
        }
        System.out.println("ID 193 has rating: " + count_rater);
        System.out.println(item + " has rater:" + count_ifrated);
        System.out.println("Movie rated: " + map.size());
        System.out.println(rater_id_most + " has most number rated movied: " + max_rating);
    }

}
