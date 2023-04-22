import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static int qntde = 3860;

    public static void read(Crud bfr) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;

        int i = 0;
        try {
            sc = new Scanner(file);
            while (/* sc.hasNextLine() */ i < 40) {
                Movie movie = new Movie(qntde--);
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

        bTree tree = new bTree("movies.db");

        Menu(byteFileRandler, tree);

        ClearDataBases(byteFileRandler, tree);
        
    }

    // Menu that shows in terminal for interaction
    private static void Menu(Crud byteFileRandler, bTree tree) throws IOException { 
        Scanner sc = new Scanner(System.in);
        System.out.println("Carregando base...");
        read(byteFileRandler);
        System.out.println("Carregando base indexada...");
        byteFileRandler.read(tree);
        System.out.println("Bases Carregadas");
        sc.close();
    }

    // clear data bases
    private static void ClearDataBases(Crud byteFileRandler, bTree tree) { 
        byteFileRandler.clear();
        tree.clear();
    }

}