import java.io.*;
import java.util.*;

public class Posting {
    // equivalent to hash buckets

    private File file;
    private RandomAccessFile fileReader;

    long address; // the postings beggining address
    ArrayList<Element> posting; // the actual posting of elements

    int max = 500;

    public Posting(long address, File F) throws FileNotFoundException {
        this.address = address;
        posting = new ArrayList<Element>();
        this.file = F;
        fileReader = new RandomAccessFile(file, "rw");
    }

    // insertion
    public void AddElement(long itemAddress, int id) {
        Element el = new Element(itemAddress, id);
        posting.add(el);
    }

    public ArrayList<Element> getElements() {
        return posting;
    }

    // gets the size of the posting
    public int getElementCount() {
        return posting.size();
    }

    public void writeFile() throws IOException {
        fileReader.seek(address); // seeks the pointer at the address

        fileReader.writeInt(posting.size()); // writes the size of the posting

        for (Element el : posting) { // for each element on posting
            fileReader.writeInt(el.get_id()); // write the id
            fileReader.writeLong(el.get_address()); // write the address
        }


        if(posting.size()<max){ // for all open space left in the posting writes -1 (space holder)
            int length = max - posting.size();
            for(int i=0; i<length;i++){
                fileReader.writeLong(-1);
                fileReader.writeInt(-1);
            }
        }
    }

    public void readFile(long add) throws IOException {
        fileReader.seek(add); // seekks the pointer at the address

        fileReader.readInt(); // reads the size of the posting

        long pos = fileReader.getFilePointer(); // saves the position to seek for next element
        while (fileReader.readInt() != -1) {
            fileReader.seek(pos);
            int id = fileReader.readInt();
            long elAdd = fileReader.readLong();
            Element el = new Element(elAdd, id);
            posting.add(el);
            pos = fileReader.getFilePointer();
        }

    }

    // get the address
    public long getAddress(){
        return address; //returns bucket address
    }
    
    // remove element from posting
    public void removeElement(int id){
        for (Element el : posting) { // for each element on posting
            if(el.get_id()==id){ // if the id searched equals to the id written in that position
                posting.remove(el); // remove element from posting
            }
        }
    }
}
