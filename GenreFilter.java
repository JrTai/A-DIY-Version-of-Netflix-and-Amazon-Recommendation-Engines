
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class GenreFilter implements Filter {
    private String myGenre;
	
	public GenreFilter(String type) {
		myGenre = type;
	}
	
	@Override
	public boolean satisfies(String id) {
		String allGenre = MovieDatabase.getGenres(id);
		ArrayList<String> allList = new ArrayList<String>(Arrays.asList(allGenre.split(", ")));
		/* // for debug
		System.out.println(allList);
		for (String s : allList) {
		   System.out.println(s);  
		}
		*/
	    if (allList.contains(myGenre)) {
		    return true;
		}
		else {
		    return false;  
		}
	}
}
