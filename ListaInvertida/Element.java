import java.io.*;

public class Element {

    private long address; // the buckets beggining address
    private int id; // movie id

    // File file;
    // RandomAccessFile fileReader;

    // constructor
    public Element() {
        this.address = 0;
        this.id = 0;
    }

    public Element(long address, int id) {
        this.address = address;
        this.id = id;
       // fileReader = new RandomAccessFile(file, "rw");
    }

    // gets and sets
    public long get_address() {
        return address;
    }

    public void set_address(long address) {
        this.address = address;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    // file reader and writer
    // public void WriteFile() throws IOException {
    //     fileReader.seek(address); // seeks the buckets addres in the hash file
    //     fileReader.writeInt(id); // writes movie id
    //     fileReader.writeLong(address); // writes address
    // }

    // public void readFile(long pos) throws IOException {
    //     fileReader.seek(pos); // seeks the address provided
    //     id = fileReader.readInt(); // reads the id
    //     address = fileReader.readLong(); // reads address
    // }
}
