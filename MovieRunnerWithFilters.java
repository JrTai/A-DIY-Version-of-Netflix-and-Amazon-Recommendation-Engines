
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
   public void printAverageRatings() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        int miniRatings = 35;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatings(miniRatings);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            System.out.println(rating.getValue()  + " " + MovieDatabase.getTitle(rating.getItem()));
        }
   }
   
   public void printAverageRatingsByYear() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        int year = 2000;
        YearAfterFilter yearafterfiler = new YearAfterFilter(year);
        int miniRatings = 20;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatingsByFilter (miniRatings, yearafterfiler);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            //System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
   }
   
   public void printAverageRatingsByGenre() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        String type = "Comedy";
        GenreFilter genrefiler = new GenreFilter(type);
        int miniRatings = 20;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatingsByFilter (miniRatings, genrefiler);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
   }
   
   public void printAverageRatingsByMinutes() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        int min = 105;
        int max = 135;
        MinutesFilter minutesfiler = new MinutesFilter(min, max);
        int miniRatings = 5;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatingsByFilter (miniRatings, minutesfiler);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            //System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
   }
   
   public void printAverageRatingsByDirectors() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorFilter directorfiler = new DirectorFilter(directors);
        int miniRatings = 4;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatingsByFilter (miniRatings, directorfiler);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
   }
   
   public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        AllFilters allfilters = new AllFilters();
        int year = 1990;
        YearAfterFilter yearafterfiler = new YearAfterFilter(year);
        allfilters.addFilter(yearafterfiler);
        String type = "Drama";
        GenreFilter genrefiler = new GenreFilter(type);
        allfilters.addFilter(genrefiler);
        
        int miniRatings = 8;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatingsByFilter (miniRatings, allfilters);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            //System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            //System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
   }
   
   public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdrating = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + thirdrating.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        AllFilters allfilters = new AllFilters();
        int min = 90;
        int max = 180;
        MinutesFilter minutesfiler = new MinutesFilter(min, max);
        allfilters.addFilter(minutesfiler);
        String name = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorFilter directorfiler = new DirectorFilter(name);
        allfilters.addFilter(directorfiler);
        
        int miniRatings = 3;
        ArrayList<Rating> rating_list = thirdrating.getAverageRatingsByFilter (miniRatings, allfilters);
        System.out.println("Total movie have above " + miniRatings + " ratings: " + rating_list.size());
        //System.out.println(rating_list);
        for (int idx = 0; idx < rating_list.size(); idx++) {
            for (int jdx = 0; jdx < rating_list.size() - 1; jdx++) {
                if (rating_list.get(jdx + 1).getValue() < rating_list.get(jdx).getValue()) {
                    Rating temp_rating = rating_list.get(jdx);
                    rating_list.set(jdx, rating_list.get(jdx + 1));
                    rating_list.set(jdx + 1, temp_rating);
                }
            }
        }
        for (Rating rating : rating_list) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
   }
}
