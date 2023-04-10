import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static int qntde = /*3860*/ 0;

    public static void read(Crud bfr) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;

        int i = 0;
        try {
            sc = new Scanner(file);
            while ( sc.hasNextLine()) {
                Movie movie = new Movie(qntde++);
                movie.read(sc.nextLine());
                bfr.writeMovie(movie);
                i++;
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

        Directory hash = new Directory("Hash.db");
        
        read(byteFileRandler);
// 
        byteFileRandler.read(hash);


        byteFileRandler.getAddress(hash.search(3654));

       

        // byteFileRandler.clear();
        // hash.clear();
    }

}