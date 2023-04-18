import java.io.*;
import java.util.*;

public class Posting {
    // equivalent to hash buckets

    private File file;
    private RandomAccessFile fileReader;

    long address; // the postings beggining address
    ArrayList<Element> posting; // the actual posting of elements

    public Posting(long address, File F) throws FileNotFoundException {
        this.address = address;
        posting = new ArrayList<Element>();
        this.file = F;
        fileReader = new RandomAccessFile(file, "rw");
    }

    public void AddElement(long itemAddress, int id) {
        Element el = new Element(itemAddress, id);
        posting.add(el);
    }

    public ArrayList<Element> getElements() {
        return posting;
    }

    public int getElementCount() {
        return posting.size();
    }

    public void writeFile() throws IOException {
        fileReader.seek(address);

        fileReader.writeInt(posting.size());

        for (Element el : posting) {
            fileReader.writeInt(el.get_id());
            fileReader.writeLong(el.get_address());
        }

        fileReader.writeInt(-1);
    }

    public void readFile(long add) throws IOException {
        fileReader.seek(add);

        fileReader.readInt();

        long pos = fileReader.getFilePointer(); // saves the position to seek for next element
        while (fileReader.readInt() != -1) {
            fileReader.seek(pos);
            int id = fileReader.readInt();
            long elAdd = fileReader.readLong();
            Element el = new Element(elAdd, id);
            posting.add(el);
        }

    }

    public long getAddress(){
        return address; //returns bucket address
    }
 
}
