import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static int qntde = /* 3860 */ 0;

    public static void read(Crud bfr) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;

        int i = 0;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
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

        Menu(byteFileRandler, hash);

        ClearDataBases(byteFileRandler, hash);

    }

    private static void ClearDataBases(Crud byteFileRandler, Directory hash) { // clear data bases
        byteFileRandler.clear();
        hash.clear();
    }

    private static void Menu(Crud byteFileRandler, Directory hash) throws Exception { // Menu that shows in terminal
                                                                                        // for interaction
        Scanner sc = new Scanner(System.in);
        System.out.println("Carregando base...");
        read(byteFileRandler);
        System.out.println("Carregando base indexada com Hash...");
        byteFileRandler.read(hash);
        System.out.println("Bases Carregadas");
        System.out.println();
        CrudMenu(byteFileRandler, hash, sc);
        sc.close();
    }

    private static void CrudMenu(Crud byteFileRandler, Directory hash, Scanner sc) throws Exception {
        System.out.println(
                "O que deseja fazer?\n1 - criar novo filme\n2 - ler filme por seu ID;\n3 - atualizar os dados de um filme;\n4 - deletar um filme");
        int op = Integer.parseInt(sc.nextLine());
        int id;
        do{
        switch (op) {
            case 1:
                byteFileRandler.createHash(hash);
                System.out.println();
                System.out.println();
                System.out.println("Digite nova opção ou 0 para sair");
                op = Integer.parseInt(sc.nextLine());
                System.out.println();

                break;
            case 2:
                System.out.println("Digite o Id para buscar:");
                id = Integer.parseInt(sc.nextLine());
                byteFileRandler.getAddress(hash.search(id));
                System.out.println();
                System.out.println();
                System.out.println("Digite nova opção ou 0 para sair");
                op = Integer.parseInt(sc.nextLine());
                System.out.println();
                break;
            case 3:
                System.out.println("Digite o Id para alterar:");
                id = Integer.parseInt(sc.nextLine());
                byteFileRandler.update((hash.search(id)), hash);
                System.out.println();
                //System.out.println("Digite nova opção ou 0 para sair");
                op = 0;
                System.out.println();

                break;
            case 4:
                System.out.println("Digite o Id para deletar:");
                id = Integer.parseInt(sc.nextLine());
                byteFileRandler.delete((hash.search(id)));
                System.out.println();
                System.out.println();
                System.out.println("Digite nova opção ou 0 para sair");
                op = Integer.parseInt(sc.nextLine());
                System.out.println();
                break;
                case 0:
                System.out.println();
                break;
            default:
            System.out.println("Opção inválida");
            System.out.println();
            System.out.println("Digite nova opção ou 0 para sair");
            op = Integer.parseInt(sc.nextLine());
            System.out.println();
                break;
        }} while (op>0);

    }

}