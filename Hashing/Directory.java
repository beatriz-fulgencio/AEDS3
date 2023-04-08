package Hashing;

import java.io.*;
import java.util.ArrayList;

public class Directory {
    private File file;
    private RandomAccessFile fileReader;

    private static int p;
    ArrayList<Long> dir = new ArrayList<Long>();
    int position;
    Bucket actualBucket;

    public Directory(String F) throws IOException {
        this.file = new File(F); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode
        p = 1;
        position = 0;
        Bucket b = new Bucket(p, position);
        dir.add(b.getAddress());
    }

    public int HashFunc(Key item) {
        int id = item.getId();
        return id % (2 * p);
    }

    public int bitExtracted(int number, int p) {
        return (((1 << p) - 1) & (number >> 1));
    }

    public boolean AddItem(int id, long address) throws IOException{
       
        return false;
    }

    private void Rehash(int pos) throws IOException {
        p++;
        dir.get(pos);

        Bucket b = new Bucket(p, pos);
        
        

    }

    public Bucket readBucket(int pos) throws IOException {
        Bucket b = new Bucket(p, pos);
        b.readFile(pos);
        
        return b;  
    }


}
