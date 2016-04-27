
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorFilter implements Filter {
    private ArrayList<String> myDirectors;
    private String list;
	
	public DirectorFilter(String name) {
		// ex: "Charles Chaplin,Michael Mann,Spike Jonze"
	    list = name;
	}
	
	@Override
	public boolean satisfies(String id) {
		String directors = MovieDatabase.getDirector(id);
		ArrayList<String> directorsList = new ArrayList<String>(Arrays.asList(directors.split(", ")));
		ArrayList<String> myDirectors = new ArrayList<String>(Arrays.asList(list.split(",")));
		// for debug
		/*
		System.out.println(directorsList);
		for (String s : directorsList) {
		   System.out.println(s);
		}
		*/
		for (String name : myDirectors) {
    	    if (directorsList.contains(name)) {
    		    return true;
    		}
        }
        return false;
	}
}
