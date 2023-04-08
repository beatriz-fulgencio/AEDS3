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

    public int HashFunc(int id) {
        return id % (2 * p);
    }

    public int bitExtracted(int number, int p) {
        return (((1 << p) - 1) & (number >> 1));
    }

    public void AddItem(int id, long address) throws IOException {
        Key item = new Key(id, address);
        int bucket = HashFunc(item);

        int pos = bitExtracted(bucket, p); // witch bucket
        Bucket b;
        try {
            b = readBucket(dir.get(pos));
        } catch (Exception e) {
            b = new Bucket(p, fileReader.length());
            dir.add(pos, b.getAddress());// add addres to hash table
        }

        boolean tryAdd = b.AddKey(item);
        if (tryAdd) {
            b.WriteFile();
        } else { // AddKey failled and Rehash is needed
            Rehash(pos, b.getKeys(), b, item);
        }

    }

    private void Rehash(int pos, ArrayList<Key> keys, Bucket b, Key item) throws IOException {
        p++;
        int posKey;
        Bucket b2;
        ArrayList<Key> k1 = new ArrayList<Key>();
        ArrayList<Key> k2 = new ArrayList<Key>();

        for (Key key : keys) {
            posKey = HashFunc(key);
            if (pos == posKey) {
                k1.add(key);
            } else {
                k2.add(key);
            }
        }

        posKey = HashFunc(item);
        if (pos == posKey) {
            k1.add(item);
        } else {
            k2.add(item);
        }

        b.Resetbucket(k1);
        if (k2.size() > 0) {
            b2 = new Bucket(p, fileReader.length());
            b2.Resetbucket(k2);
            dir.add(b2.getAddress());
        }

    }

    public Bucket readBucket(long address) throws IOException {
        Bucket b = new Bucket(p, address);
        b.readFile(address);

        return b;
    }

    public boolean search(int id) {
        int pos = HashFunc(id);
        long add = -1;
        try {
            add = dir.get(pos);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(e.toString());
        }
        if (add != -1) {
            Bucket b = new Bucket(p, add);
            return b.search(id);
        }

        return false;
    }

}
