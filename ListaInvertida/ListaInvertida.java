import java.io.*;
import java.util.ArrayList;

public class ListaInvertida {
    private File file;
    private RandomAccessFile fileReader;

    ArrayList<Term> terms;

    public void List(String F) throws IOException {
        this.file = new File(F); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw");

        terms = new ArrayList<Term>();
    }

    public void addItem(String gen, long address) {
        boolean resp = false;
        for (Term term : terms) {
            if (gen.equals(term.genre)) {
                // chamar funcao para adicionar endereco no posting
                resp = true;
            }
        }
        if (resp == false) {
            Term t = new Term();
            // t.add(add); adicionar endereco ao posting
            terms.add(t);
        }
    }

    // read -> for each term retornar posting e buscar no movie.db cada item
    public void readOneParameter(String gen, Posting p) throws IOException {
        long ad = -1;

        // searchs each genre in that movie
        for (Term term : terms) {
            // if genre exists in that movie
            if (gen.equals(term.genre)) {
                // get posting address
                ad = p.getAddress();
                // seek the address
                fileReader.seek(ad);
                // reads the id
                int id = fileReader.readInt();
                // prints the id
                System.out.println(id);
            } else { // if genre doesnt exist in that movie
                ad = -1;
            }
        }
    }

    // read relacionado com 2 -> pega o posting de cada um criar um novo posting com
    // os ids em ambos e buscar esses no movie.db

    // delete -> entrar no termo, entrar no posting e deletar element
}
