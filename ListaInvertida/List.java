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

    public void addItem(String gen, long address, int id) throws Exception {
        boolean resp = false;
        for (Term term : terms) {
            if (gen.equals(term.genre)) {
                // chamar funcao para adicionar endereco no posting
                resp = true;
                term.addToPosting(address, id);
                break;
            }
        }
        if (resp == false) {
            Term t = new Term(gen, fileReader.length(), file);
            t.createPosting(address, id);// adicionar endereco ao posting
            terms.add(t);
        }
    }

    // read -> for each term retornar posting e buscar no movie.db cada item
    public ArrayList<Element> readOneParameter(String gen) throws Exception {
        //long ad = -1;

        // searchs each genre in that movie
        for (Term term : terms) {
            // if genre exists in that movie
            if (gen.equals(term.genre)) {
                return term.getPostingElements();
                // // get posting address
                // ad = p.getAddress();
                // // seek the address
                // fileReader.seek(ad);
                // // reads the id
                // int id = fileReader.readInt();
                // // prints the id
                // System.out.println(id);
            } else { // if genre doesnt exist in that movie
               // ad = -1;
            }
        }
        return null;
    }

    // read relacionado com 2 -> pega o posting de cada um criar um novo posting com
    // os ids em ambos e buscar esses no movie.db

    public ArrayList<Element> readTwoParameter(String gen1, String gen2) throws Exception {
        ArrayList<Element> list1 =  null;
        ArrayList<Element> list2 = null;
        ArrayList<Element> list3 = new ArrayList<Element>();

        for (Term term : terms) {
            if (gen1.equals(term.genre)) {
                list1 = term.getPostingElements();
            } 
            if (gen2.equals(term.genre)) {
                list2 = term.getPostingElements();
            } 

            if(list1!=null && list2!= null){
                break;
            }
        }

        for (Element el : list1) {
            for (Element el2: list2) {
                if(el.get_id()==el2.get_id()){
                    list3.add(el2);
                    break;
                }
            }
        }

        return list3;
    }

    // delete -> entrar no termo, entrar no posting e deletar element

    public void delete(int id){
        
    }

    public void clear() {
        file.delete();
    }
}
