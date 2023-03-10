import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static int qntde;

    public static void read(Crud bfr) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;

        int i = 0;
        try {
            sc = new Scanner(file);
            while (/* sc.hasNextLine() */ i < 20) {
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

         read(byteFileRandler);

        // byteFileRandler.clear();
        // byteFileRandler.read("0000");

        // byteFileRandler.update("0002");

        // byteFileRandler.update("0000");

   Sort fileSort = new Sort("movies.db");

        //  fileSort.clear();

        fileSort.intercalacaoBalanceadaComum();

        // byteFileRandler.create();
        // byteFileRandler.delete("0099");
        // byteFileRandler.update("0002");
        // System.out.println(byteFileRandler.select("0002"));
        // System.out.println(byteFileRandler.select("0003"));
    }

}