import java.io.*;
import java.text.*;
import java.util.*;

public class ExTP {

    public static String[] read(String[] strs) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;
        int place = 0;

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                strs[place] = sc.nextLine();
                place++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sc.close();

        return strs;
    }

    public static void main(String[] args) throws Exception {

        int n = 3900;
        String[] movies = new String[n];
        Movie[] movieObjects = new Movie[n];
        movies = read(movies);

        int countMovies = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < movies.length; j++) {
                movieObjects[countMovies] = new Movie();
                movieObjects[countMovies].read(movies[j]);
                countMovies++;
                j = movies.length;
            }
        }

    }

}

class Movie {

    static SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

    private String movieId;
    private String title;
    private ArrayList<String> genres;
    private int duration;
    private String contentType;
    private Date dateAdded;

    public Movie() {

        this.movieId = "";
        this.title = "";
        this.genres = new ArrayList<String>();
        this.duration = 0;
        this.contentType = "";
        this.dateAdded = null;
    }

    public Movie(String movieId, String title, ArrayList<String> genres, int duration, String contentType,
            Date dateAdded) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.duration = duration;
        this.contentType = contentType;
        this.dateAdded = dateAdded;
    }

    // Gets e Sets

    public void set_movieId(String movieId) {
        this.movieId = movieId;
    }

    public String get_movieId() {
        return movieId;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public String get_title() {
        return title;
    }

    public void set_genres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> get_genres() {
        return genres;
    }

    public void set_duration(int duration) {
        this.duration = duration;
    }

    public int get_duration() {
        return duration;
    }

    public void set_contentType(String contentType) {
        this.contentType = contentType;
    }

    public String get_contentType() {
        return contentType;
    }

    public void set_dateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date get_dateAdded() {
        return dateAdded;
    }

    public void read(String line) {
        int index = 0, atr_index = 0;

        // ---------------------------------- //
        // Find "ID"
        while (true) {
            index++;
            if (line.charAt(index) == ';') {
                this.movieId = line.substring(atr_index, index);
                atr_index = ++index;
                break;
            }
        }
        // ---------------------------------- //
        // Find "title"
        if (line.charAt(atr_index) != ';') {
            if (line.charAt(atr_index) == '#')
                atr_index++;

            while (true) {
                index++;
                if (line.charAt(index) == ';') {
                    this.title = line.substring(atr_index, index);
                    index++;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;
        // ---------------------------------- //
        // Find "genres"
        if (line.charAt(index) != ';') {
            while (true) {
                if(line.charAt(index) == ';'){
                    index++;
                    atr_index = index;
                    break;
                }else{
                int wordStart = index++;
                while (true) {
                    index++;
                    if (line.charAt(index) == ',' ||line.charAt(index) == ';') {
                        this.genres.add(line.substring(wordStart, index));
                        break;
                    } 
                }
            }

            }
        } else {
            atr_index = ++index;
        }
        // ---------------------------------- //
        // Find "duration"
        if (line.charAt(index) != ';') {
            while (true) {
                index++;
                if (line.charAt(index) == ';') {
                    this.duration = Integer.parseInt(line.substring(atr_index, index));
                    atr_index = ++index;
                    break;
                }
            }
        } else {
            atr_index = ++index;
        }
        // ---------------------------------- //
        // Find "contentType"
        if (line.charAt(index) != ';') {
            while (true) {
                index++;
                if (line.charAt(index) == ';') {
                    this.contentType = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else {
            atr_index = ++index;
        }
        // ---------------------------------- //
        // Find "Date"
        if (line.charAt(atr_index) != ';') {
            while (index != line.length()) {
                index++;
            }
            try {
                this.dateAdded = format.parse(line.substring(atr_index, index));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        } else
            atr_index = ++index;
    }

    

}
