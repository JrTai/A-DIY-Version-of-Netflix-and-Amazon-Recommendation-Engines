 


/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            movieList.add(movie);
        }
        return movieList;
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raterList = new ArrayList<EfficientRater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        int rater_id = 0;
        EfficientRater rater = new EfficientRater("0");
        for (CSVRecord record : parser){
            if (rater_id == Integer.parseInt(record.get("rater_id"))) {
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
            else {
                raterList.add(rater);
                rater_id = Integer.parseInt(record.get("rater_id"));
                rater = new EfficientRater(String.valueOf(rater_id));
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
        }
        raterList.add(rater);
        raterList.remove(0);
        return raterList;
    }

}
