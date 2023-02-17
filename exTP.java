import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static void main(String[] args) {

        File file = new File("netflix.csv");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

}

class Movies {

    static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    private String showId;
    private String title;
    private ArrayList<String> genres;
    private int duration;
    private String contentType;
    private Date dateAdded;

    public Movies() {

        this.showId = "";
        this.title = "";
        this.genres = new ArrayList<String>();
        this.duration = 0;
        this.contentType = "";
        this.dateAdded = null;
    }

    public Movies(String showId, String title, ArrayList<String> genres, int duration, String contentType, Date dateAdded) {
        this.showId = showId;
        this.title = title;
        this.genres = genres;
        this.duration = duration;
        this.contentType = contentType;
        this.dateAdded = dateAdded;
    }

    // Gets e Sets

    public void set_showId(String showId) {
        this.showId = showId;
    }
    public String get_showId() { 
        return showId; 
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

}
