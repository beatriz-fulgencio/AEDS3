import java.io.*;
import java.text.*;
import java.util.*;

public class ExTP {

    public static String[] read(String[] strs, int maxRead) throws FileNotFoundException {
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
        movies = read(movies, n);

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

