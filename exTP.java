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
            while ( sc.hasNextLine() /*i < 100*/) {
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
        Scanner sc = new Scanner(System.in);
        // Crud byteFileRandler = new Crud("movies.db");

        // read(byteFileRandler);

        //  byteFileRandler.clear();
        // // byteFileRandler.read("0000");

        // byteFileRandler.update("0002");
        // System.out.print("Ler");
        // String y = sc.nextLine();

        // // byteFileRandler.update("0000");

        Sort fileSort = new Sort("movies.db");

        // fileSort.clear();

        fileSort.intercalacaoBalanceadaComum();
        
//          System.out.print("Ler");
//         String x = sc.nextLine();
//         fileSort.read();
// // 
        // byteFileRandler.create();
        // byteFileRandler.delete("0099");
        // byteFileRandler.update("0002");
        // System.out.println(byteFileRandler.select("0002"));
        // System.out.println(byteFileRandler.select("0003"));
    }

}