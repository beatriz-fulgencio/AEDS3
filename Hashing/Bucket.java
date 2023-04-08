package Hashing;

import java.io.*;
import java.util.ArrayList;

public class Bucket {

    //private File file;
    private RandomAccessFile fileReader;
    
    int p;
    int maxLenght;
    long address;
    ArrayList<Key> bucket;

    public Bucket(int p, long address){
        this.p = p;
        maxLenght= 194;
        this.address = address;
        bucket = new ArrayList<Key>();
    }

    public boolean AddKey(Key key) throws IOException{
        boolean ret = false;
        if(bucket.size()<=maxLenght){
            ret = bucket.add(key);
        }
        WriteFile();
        return ret;
    }

    public ArrayList<Key> getKeys(){
        return bucket;
    }

    // public void Resetbucket(ArrayList<Key> newKeys) throws IOException{
    //     bucket.clear();
    //     bucket = newKeys;
    //     WriteFile();
    // }

    public void WriteFile() throws IOException{
        fileReader = new RandomAccessFile("Hash.db", "rw");

        fileReader.seek(address);
        fileReader.writeInt(p);
        fileReader.writeInt(bucket.size());

        for (Key key : bucket) {
            fileReader.writeInt(key.getId());
            fileReader.writeLong(key.getAddress());
        }

        if(bucket.size()<maxLenght){
            int length = maxLenght - bucket.size();
            for(int i=0; i<length;i++){
                fileReader.writeInt(-1);
                fileReader.writeLong(-1);
            }
        }
        
    }

    public void readFile(long pos) throws IOException{
        fileReader.seek(pos);
       p = fileReader.readInt();

       long position = fileReader.getFilePointer();
       while(fileReader.readInt()!=-1){
        fileReader.seek(position);
        int id = fileReader.readInt();
        long add = fileReader.readLong();
        Key key = new Key(id, add);
        bucket.add(key);
        position = fileReader.getFilePointer();
       }
    
    }

    public long getAddress(){
        return address;
    }
}
