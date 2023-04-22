import java.io.*;
import java.util.ArrayList;

public class List {
    private File file;
    private RandomAccessFile fileReader;

    ArrayList<Term> terms;

    public List(String F) throws IOException {
        this.file = new File(F); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw");

        terms = new ArrayList<Term>();
    }

    // insertion
    public void addItem(String gen, long address, int id) throws Exception {

        gen = gen.strip(); // retorna uma string, com todos os espaços em branco à esquerda e à direita
                           // removidos
        boolean resp = false;
        for (Term term : terms) { // for each term
            if (gen.equals(term.genre)) { // if the genre equals to a genre thats already in the array
                resp = true;
                term.addToPosting(address, id); // add address to posting
                break;
            }
        }
        if (resp == false) { // if the genre is not in the array
            Term t = new Term(gen, fileReader.length(), file); // create a new term wit that genre
            t.createPosting(address, id); // add address to posting
            terms.add(t);
        }

        if (id == 3860) {
            String x = "s";
        }
    }

    // read -> for each term retornar posting e buscar no movie.db cada item
    public ArrayList<Element> readOneParameter(String gen) throws Exception {
        gen = gen.strip();
        // searchs each genre in that movie
        for (Term term : terms) {
            // if genre exists in that movie
            if (gen.equals(term.genre)) {
                return term.getPostingElements();
            }
        }
        return null;
    }

    // read relacionado com 2 -> pega o posting de cada um criar um novo posting com
    // os ids em ambos e buscar esses no movie.db

    public ArrayList<Element> readTwoParameter(String gen1, String gen2) throws Exception {
        ArrayList<Element> list1 = null;
        ArrayList<Element> list2 = null;
        ArrayList<Element> list3 = new ArrayList<Element>();

        gen1 = gen1.strip();
        gen2 = gen2.strip();

        for (Term term : terms) { // for each term
            if (gen1.equals(term.genre)) { // if the genre equals to a term thats already on the array
                list1 = term.getPostingElements();
            }
            if (gen2.equals(term.genre)) { // if the genre equals to a term thats already on the array
                list2 = term.getPostingElements();
            }

            if (list1 != null && list2 != null) {
                break;
            }
        }

        for (Element el : list1) { // for each element on list1
            for (Element el2 : list2) { // for each element on the list2
                if (el.get_id() == el2.get_id()) { // if theres any element in common
                    list3.add(el2);// add it to list3
                    break;
                }
            }
        }

        return list3;
    }

    // delete -> entrar no termo, entrar no posting e deletar element

    public void delete(int id, String[] genres) throws Exception {
        for (String g : genres) { // for each genre
            g = g.strip();
            for (Term term : terms) { // for each term
                if (term.genre.equals(g)) { // if theres a movie with that genre 
                    term.removeFromPosting(id); // remove it from posting
                }
            }
        }
    }

    // clear file
    public void clear() {
        file.delete();
    }
}
