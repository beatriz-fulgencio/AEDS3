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

        
        // Directory hash = new Directory("Hash.db");

        // Menu(byteFileRandler, hash);

        // ClearDataBases(byteFileRandler, hash);
        
    }


    // private static void ClearDataBases(Crud byteFileRandler, Directory hash) { //clear data bases
    //     byteFileRandler.clear();
    //     hash.clear();
    // }


    // private static void Menu(Crud byteFileRandler, Directory hash) throws IOException { //Menu that shows in terminal for interaction
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Carregando base...");
    //     read(byteFileRandler);
    //     System.out.println("Carregando base indexada com Hash...");
    //     byteFileRandler.read(hash);
    //     System.out.println("Bases Carregadas");
    //     System.out.println("Deseja buscar um filme pelo seu Id? (1 para sim e 0 para nao)");
    //     int resp = Integer.parseInt(sc.nextLine());
    //     if(resp == 1){
    //         do{
    //         System.out.println("Digite o Id para buscar:");
    //         int id = Integer.parseInt(sc.nextLine());
    //         byteFileRandler.getAddress(hash.search(id));
    //         System.out.println();
    //         System.out.println();
    //         System.out.println("Deseja buscar um filme pelo seu Id? (1 para sim e 0 para nao)");
    //          resp = Integer.parseInt(sc.nextLine());
    //     } while(resp==1);
    //     }
    //     sc.close();
    // }

}