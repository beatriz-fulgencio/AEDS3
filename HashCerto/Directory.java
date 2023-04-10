import java.io.*;
import java.util.ArrayList;

public class Directory {
    private File file;
    private RandomAccessFile fileReader;

    private static int p;
    ArrayList<Long> dir = new ArrayList<Long>(); //actual directory 

    public Directory(String F) throws IOException {
        this.file = new File(F); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode
        p = 1; //initializes depth as 1
    }

    public int HashFunc(Key item) {
        int id = item.getId();
        return id % (2 * p); // returns k mod 2p
    }

    public int HashFunc(int id) {
        return id % (2 * p); // returns k mod 2p
    }

    public int bitExtracted(int number, int p) {
        return (((1 << p) - 1) & (number >> 0)); //returns the p significant bits of number in integer format
    }

    public void AddItem(int id, long address) throws IOException {
        Key item = new Key(id, address); //creates key from info provided
        int hashFuncValue = HashFunc(item); //gets the hash function value for id of item

        int pos = bitExtracted(hashFuncValue, p); // witch bucket
        Bucket b;
        try {
            b = readBucket(dir.get(pos)); //if bucket already exists it sets b with bucket info
        } catch (Exception e) {
            b = new Bucket(p, fileReader.length(), file); //creates bucket in the end of file
            dir.add(pos, b.getAddress());// add address to hash table
        }

        boolean tryAdd = b.AddKey(item);
        if (tryAdd) { //if item was added correctly writes bucket in file
            b.WriteFile();
        } else { // AddKey failled and Rehash is needed
            Rehash(pos, b.getKeys(), b, item);
        }

    }

    private void Rehash(int pos, ArrayList<Key> keys, Bucket b, Key item) throws IOException {
        p++; //increases the directory depth
        int posKey;
        Bucket b2;
        ArrayList<Key> k1 = new ArrayList<Key>();
        ArrayList<Key> k2 = new ArrayList<Key>();

        for (Key key : keys) {
            posKey = HashFunc(key); //redos the hash function for each of the buckets items 
            if (pos == posKey) {
                k1.add(key);
            } else {
                k2.add(key);
            }
        }

         //adds the new item to correct bucket
        posKey = HashFunc(item);
        if (pos == posKey) {
            k1.add(item);
        } else {
            k2.add(item);
        }

        //resets the old bucket with new info
        b.setP((b.getP() + 1));
        b.Resetbucket(k1);

        //if second bucket was created adds it to directory
        if (k2.size() > 0) {
            b2 = new Bucket(b.getP(), fileReader.length(), file);
            b2.Resetbucket(k2);
            dir.add(b2.getAddress());
        }

    }

    public Bucket readBucket(long address) throws IOException {
        Bucket b = new Bucket(p, address, file);
        b.readFile(address); //sets b with info from that address 

        return b;
    }

    public long search(int id) throws FileNotFoundException {
        int hashFuncValue = HashFunc(id);
        int pos = bitExtracted(hashFuncValue, p); // witch bucket
        long add = -1;
        Bucket b;
        try {
            b = readBucket(dir.get(pos)); //see if bucket exists
            add = b.getAddress(); //gets bucket address
            if (add != -1) {
                return b.search(id); //searches in that bucket for id 
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return -1; //if not found return an offBounds value
    }

    public void clear() {
        file.delete(); //deletes file
    }

    //reads and prints all of the buckets
    public void readFile() throws IOException {
        //RandomAccessFile fileReader = new RandomAccessFile("Hash.db", "rw");
        fileReader.seek(0);
        //Bucket b = new Bucket(p, p, null);
        for (int i = 0; i < 3860 && fileReader.getFilePointer()< fileReader.length(); i++) {
            System.out.println("p" + fileReader.readInt());

            fileReader.readInt();
            int count = 0;

            long position = fileReader.getFilePointer();
            while (count < 193 && fileReader.getFilePointer()< fileReader.length()) {
                fileReader.seek(position);
                System.out.println("id = " + fileReader.readInt());
                System.out.println("address = " + fileReader.readLong());
                position = fileReader.getFilePointer();
                count++;
            }
        }
    }

}
