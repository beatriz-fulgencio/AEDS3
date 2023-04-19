import java.io.*;
import java.util.ArrayList;

public class Term {
   
     private File file;
    private RandomAccessFile fileReader;
   
    public String genre;
    public long address;

    // constructor
    public Term() {
        this.genre = "";
        this.address = 0;
    }

    public Term(String genre, long address, File F) throws FileNotFoundException {
        this.genre = genre;
        this.address = address;
        
        this.file = F;
        fileReader = new RandomAccessFile(file, "rw");
    }

    // funcao para adicionar no posting
    public void addToPosting(long add, int id) throws IOException{
        Posting p = new Posting(address, file);

        p.readFile(address);

        p.AddElement(add, id);
        p.writeFile();
    }

    public void createPosting(long add, int id) throws IOException{
        Posting p = new Posting(address, file);

        p.AddElement(add, id);
        p.writeFile();
    }

    public ArrayList<Element> getPostingElements() throws Exception{
        Posting p = new Posting(address, file);
        p.readFile(address);

        return p.getElements();
    }
}