
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int miniRatings = 35;
        FourthRatings fourthrating = new FourthRatings();
        ArrayList<Rating> rating_list = fourthrating.getAverageRatings(miniRatings);
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
   
   public void printAverageRatingsByYearAfterAndGenre() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
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
        FourthRatings fourthrating = new FourthRatings();
        ArrayList<Rating> rating_list = fourthrating.getAverageRatingsByFilter (miniRatings, allfilters);
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
   
   public void printSimilarRatings() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies"); 
        
        String raterID = "71";
        int topRaters = 20;
        int minimalRaters = 5;
        
        FourthRatings fourthrating = new FourthRatings();
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        System.out.println("Total movies recomended: " + rating_list.size());
        
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            break;
        }
   }
   
   public void printSimilarRatingsByGenre () {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies"); 
        
        String raterID = "964";
        int topRaters = 20;
        int minimalRaters = 5;
        
        AllFilters allfilters = new AllFilters();
        String type = "Mystery";
        GenreFilter genrefiler = new GenreFilter(type);
        allfilters.addFilter(genrefiler);
        
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size());
        
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            break;
        }
   }
   
   public void printSimilarRatingsByDirector() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies"); 
        
        String raterID = "120";
        int topRaters = 10;
        int minimalRaters = 2;
        
        AllFilters allfilters = new AllFilters();
        String name = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorFilter directorfiler = new DirectorFilter(name);
        allfilters.addFilter(directorfiler);
        
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size());
        
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            break;
        }
   }
   
   public void printSimilarRatingsByGenreAndMinutes() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies"); 
        
        String raterID = "168";
        int topRaters = 10;
        int minimalRaters = 3;
        
        AllFilters allfilters = new AllFilters();
        String type = "Drama";
        GenreFilter genrefiler = new GenreFilter(type);
        allfilters.addFilter(genrefiler);
        int min = 80;
        int max = 160;
        MinutesFilter minutesfiler = new MinutesFilter(min, max);
        allfilters.addFilter(minutesfiler);
        
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size());
        
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            break;
        }
   }
   
   public void printSimilarRatingsByYearAfterAndMinutes() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies"); 
        
        String raterID = "314";
        int topRaters = 10;
        int minimalRaters = 5;
        
        AllFilters allfilters = new AllFilters();
        int year = 1975;
        YearAfterFilter yearafterfiler = new YearAfterFilter(year);
        allfilters.addFilter(yearafterfiler);
        int min = 70;
        int max = 200;
        MinutesFilter minutesfiler = new MinutesFilter(min, max);
        allfilters.addFilter(minutesfiler);
        
        FourthRatings fourthrating = new FourthRatings();
        //ArrayList<Rating> rating_list = fourthrating.getSimilarRatings(raterID, topRaters, minimalRaters);
        ArrayList<Rating> rating_list = fourthrating.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, allfilters);
        System.out.println("Total movies recomended: " + rating_list.size());
        
        for (Rating rating : rating_list) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            break;
        }
   }
}
