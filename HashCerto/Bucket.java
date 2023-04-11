import java.io.*;
import java.util.ArrayList;

public class Bucket {

    private File file;
    private RandomAccessFile fileReader;
    
    int p; //the depth of the bucket
    int maxLenght; //the max lenght of the bucket
    long address; //the buckets beggining address
    ArrayList<Key> bucket; //the actual bucket of keys

    public Bucket(int p, long address, File F) throws FileNotFoundException{
        this.p = p;
        maxLenght = 193; //initialized as 5% of our dataBase
        this.address = address; 
        bucket = new ArrayList<Key>();
        this.file = F;
        fileReader = new RandomAccessFile(file, "rw");
    }

    public boolean AddKey(Key key) throws IOException{
        boolean ret = false;
        if(bucket.size()<maxLenght){ //if there is still space in the bucket
            ret = bucket.add(key); //adds the key 
        }
        return ret; //returns if the key was added correctly
    }

    public ArrayList<Key> getKeys(){
        return bucket; //return the bucket's keys
    }

    public void Resetbucket(ArrayList<Key> newKeys) throws IOException{
        bucket.clear(); //clears the old bucket
        bucket = newKeys; //resets the keys
        WriteFile(); //rewrites the bucket in the file
    }

    public void WriteFile() throws IOException{
    
        fileReader.seek(address); //seeks the buckets addres in the hash file
        fileReader.writeInt(p); //writes the local depth of said bucket
        fileReader.writeInt(bucket.size()); //writes bucket size

        for (Key key : bucket) { //for-each key in the bucket writes its id and address
            fileReader.writeInt(key.getId());
            fileReader.writeLong(key.getAddress());
        }

        if(bucket.size()<maxLenght){ //for all open space left in the bucket writes -1 (space holder)
            int length = maxLenght - bucket.size();
            for(int i=0; i<length;i++){
                fileReader.writeInt(-1);
                fileReader.writeLong(-1);
            }
        }
        
    }

    public void readFile(long pos) throws IOException{
        fileReader.seek(pos); //seeks the address provided
       p = fileReader.readInt(); //reads and sets the local depth 

       fileReader.readInt(); //reads the size 
       int count = 0;

       long position = fileReader.getFilePointer(); //saves the position to seek for next key
       while(fileReader.readInt()!=-1 && count<maxLenght){ //while it has not reached the end of bucket or space holders adds read key to bucket
        fileReader.seek(position);
        int id = fileReader.readInt();
        long add = fileReader.readLong();
        Key key = new Key(id, add);
        bucket.add(key);
        position = fileReader.getFilePointer();
        count++;
       }
       int x = count-1;
    
    }

    public long getAddress(){
        return address; //returns bucket address
    }

    public long search(int id){
        
        try {
            readFile(address); //reads the file for keys
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Key key : bucket) {
            int keyId = key.getId();
            if(keyId==id){
                return key.getAddress(); //if id is found returns the address of actual file
            }
        }

        return -1; //return if id not found
    }

    public int getP(){
        return p; //return the bucket's depth
    }
    public void setP(int p){
        this.p = p; //sets the bucket depth
    }
}
