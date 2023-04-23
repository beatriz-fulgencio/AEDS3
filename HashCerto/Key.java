public class Key {
    private int id;
    private long address;

    public Key() {//initializes key with invalid values
        id =-1; 
        address = -1;
    }

    public Key(int id, long address){ //initializes key with given values
        this.id= id;
        this.address = address;
    }

    public String toString(){
        return "Id = " + this.id + " \nAddress = " + this.address; //returns data from key 
    }

    public int getId(){
        return this.id; //returns keys id
    }

    public long getAddress(){
        return this.address; //return keys address
    }


}
