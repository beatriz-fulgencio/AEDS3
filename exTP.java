import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static int qntde;

    public static void read(Crud bfr) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Movie movie = new Movie(qntde++);
                movie.read(sc.nextLine());
                bfr.writeMovie(movie);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();

    }

    public static void main(String[] args) throws Exception {
        Crud byteFileRandler = new Crud("movies.db");

        read(byteFileRandler);

        //byteFileRandler.clear();
        //byteFileRandler.read("0000");

        byteFileRandler.create();
    }

}