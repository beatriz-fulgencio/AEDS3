import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static String[] read(String[] strs, int maxRead) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;
        int place = 0;

        try {
            sc = new Scanner(file);
            while (place < maxRead) {
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
        int m = 3862;

        while (m > 0) {
            int n = 100;
            if (m < 100) {
                n = 62;
            }
            String[] movies = new String[n];
            Movie[] movieObjects = new Movie[n];
            movies = read(movies, n);

            for (int j = 0; j < movies.length; j++) {
                movieObjects[j] = new Movie();
                movieObjects[j].read(movies[j]);
                System.out.print(movieObjects[j].toString());
            }
            m = m-n;

            
        }

    }

}