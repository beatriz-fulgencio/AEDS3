import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static int qntde = 0/* 3860 */ ;

    public static void read(Crud bfr, List l) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;

        int i = 0;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Movie movie = new Movie(qntde++);
                movie.read(sc.nextLine());
                bfr.writeMovie(movie, l);
                System.out.print(i + " ");
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

        List list = new List("InvIndex.db");

        // Directory hash = new Directory("Hash.db");

        Menu(byteFileRandler, list);

        ClearDataBases(byteFileRandler, list);

    }

    private static void ClearDataBases(Crud byteFileRandler, List list) { // clear data bases
        byteFileRandler.clear();
        list.clear();
    }

    private static void Menu(Crud byteFileRandler, List listIndex) throws Exception { // Menu that shows in terminal for
                                                                                      // interaction
        Scanner sc = new Scanner(System.in);
        System.out.println("Carregando base...");
        read(byteFileRandler, listIndex);
        System.out.println("Bases Carregadas");
        // --------------------------------------------
        // MENU
        System.out.println("\n ------------------------------------ \n");
        System.out.println("Pesquisar Filmes por Genero\n");

        System.out.println(
                "Opções\na)\"Horror Movies\"\nb)\"International Movies\"\nc)\"Thrillers\"\nd)\"Documentaries\"\ne)\"Dramas\"\nf)\"Romantic Movies\"\ng)\"Comedies\"\nh)\"Sports Movies\"\ni)\"Movies\"\nj)\"Independent Movies\"\nk)\"Faith & Spirituality\"\nl)\"Action & Adventure\"\nm)\"Sci-Fi & Fantasy\"\nn)\"LGBTQ Movies\"\no)\"Children & Family Movies\"\np)\"Music & Musicals\"\nq)\"Classic Movies\"\nr)\"Cult Movies\"\ns)\"Anime Features\"\nt)\"Stand-Up Comedy\"\n");

        System.out.println("Deseja filtrar por 1 gênero ou 2? Digite 1 ou 2\n");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            searchList1(byteFileRandler, listIndex, sc);
        } else if (tipo == 2) {
            searchList2(byteFileRandler, listIndex, sc);
        } else {
            System.out.println("Opção não válida\n");
        }

        sc.close();
    }
    
    // search for 2 parameters 
    private static void searchList2(Crud byteFileRandler, List listIndex, Scanner sc) throws Exception {

        System.out.println("Reveja as opções de gêneros e digite a letra do primeiro genero que deseja filtar\n");
        String op1 = sc.nextLine();

        op1 = op1.strip(); // retorna uma string, com todos os espaços em branco à esquerda e à direita removidos

        System.out.println("Reveja as opções de gêneros e digite a letra do segundo genero que deseja filtar\n");
        String op2 = sc.nextLine();

        op2 = op2.strip();

        String op = null;
        String opp = null;

        // all the genres
        switch (op1) {
            case "A":
            case "a":
                op = ("Horror Movies");
                break;
            case "B":
            case "b":
                op = ("International Movies");
                break;
            case "C":
            case "c":
                op = ("Thrillers");
                break;
            case "D":
            case "d":
                op = ("Documentaries");
                break;
            case "E":
            case "e":
                op = ("Dramas");
                break;
            case "F":
            case "f":
                op = ("Romantic Movies");
                break;
            case "G":
            case "g":
                op = ("Comedies");
                break;
            case "H":
            case "h":
                op = ("Sports Movies");
                break;
            case "I":
            case "i":
                op = ("Movies");
                break;
            case "J":
            case "j":
                op = ("Independent Movies");
                break;
            case "K":
            case "k":
                op = ("Faith & Spirituality");
                break;
            case "L":
            case "l":
                op = ("Action & Adventure");
                break;
            case "M":
            case "m":
                op = ("Sci-Fi & Fantasy");
                break;
            case "N":
            case "n":
                op = ("LGBTQ Movies");
                break;
            case "O":
            case "o":
                op = ("Children & Family Movies");
                break;
            case "P":
            case "p":
                op = ("Music & Musicals");
                break;
            case "Q":
            case "q":
                op = ("Classic Movies");
                break;
            case "R":
            case "r":
                op = ("Cult Movies");
                break;
            case "S":
            case "s":
                op = ("Anime Features");
                break;
            case "T":
            case "t":
                op = ("Stand-Up Comedy");
                break;
        }

        switch (op2) {
            case "A":
            case "a":
                opp = ("Horror Movies");
                break;
            case "B":
            case "b":
                opp = ("International Movies");
                break;
            case "C":
            case "c":
                opp = ("Thrillers");
                break;
            case "D":
            case "d":
                opp = ("Documentaries");
                break;
            case "E":
            case "e":
                opp = ("Dramas");
                break;
            case "F":
            case "f":
                opp = ("Romantic Movies");
                break;
            case "G":
            case "g":
                opp = ("Comedies");
                break;
            case "H":
            case "h":
                opp = ("Sports Movies");
                break;
            case "I":
            case "i":
                opp = ("Movies");
                break;
            case "J":
            case "j":
                opp = ("Independent Movies");
                break;
            case "K":
            case "k":
                opp = ("Faith & Spirituality");
                break;
            case "L":
            case "l":
                opp = ("Action & Adventure");
                break;
            case "M":
            case "m":
                opp = ("Sci-Fi & Fantasy");
                break;
            case "N":
            case "n":
                opp = ("LGBTQ Movies");
                break;
            case "O":
            case "o":
                opp = ("Children & Family Movies");
                break;
            case "P":
            case "p":
                opp = ("Music & Musicals");
                break;
            case "Q":
            case "q":
                opp = ("Classic Movies");
                break;
            case "R":
            case "r":
                opp = ("Cult Movies");
                break;
            case "S":
            case "s":
                opp = ("Anime Features");
                break;
            case "T":
            case "t":
                opp = ("Stand-Up Comedy");
                break;
        }

    byteFileRandler.getMoviesInPosting(listIndex.readTwoParameter(op, opp));

    }

    // search for 1 parameter
    private static void searchList1(Crud byteFileRandler, List listIndex, Scanner sc) throws Exception {
        System.out.println("Reveja as opções de gêneros e digite a letra do que deseja pesquisar\n");
        String op = sc.nextLine();

        op = op.strip(); // retorna uma string, com todos os espaços em branco à esquerda e à direita removidos

        switch (op) {
            case "A":
            case "a":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Horror Movies"));
                break;
            case "B":
            case "b":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("International Movies"));
                break;
            case "C":
            case "c":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Thrillers"));
                break;
            case "D":
            case "d":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Documentaries"));
                break;
            case "E":
            case "e":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Dramas"));
                break;
            case "F":
            case "f":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Romantic Movies"));
                break;
            case "G":
            case "g":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Comedies"));
                break;
            case "H":
            case "h":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Sports Movies"));
                break;
            case "I":
            case "i":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Movies"));
                break;
            case "J":
            case "j":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Independent Movies"));
                break;
            case "K":
            case "k":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Faith & Spirituality"));
                break;
            case "L":
            case "l":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Action & Adventure"));
                break;
            case "M":
            case "m":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Sci-Fi & Fantasy"));
                break;
            case "N":
            case "n":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("LGBTQ Movies"));
                break;
            case "O":
            case "o":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Children & Family Movies"));
                break;
            case "P":
            case "p":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Music & Musicals"));
                break;
            case "Q":
            case "q":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Classic Movies"));
                break;
            case "R":
            case "r":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Cult Movies"));
                break;
            case "S":
            case "s":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Anime Features"));
                break;
            case "T":
            case "t":
                byteFileRandler.getMoviesInPosting(listIndex.readOneParameter("Stand-Up Comedy"));
                break;
        }

    }

}