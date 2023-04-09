public class Key {
    private int id;
    private long address;

    public Key() {
        id =-1;
        address = -1;
    }

    public Key(int id, long address){
        this.id= id;
        this.address = address;
    }

    public void ResetKey(int id, long address){
        this.id= id;
        this.address = address;
    }


    public String toString(){
        return "Id = " + this.id + " \nAddress = " + this.address;
    }

    public int getId(){
        return this.id;
    }

    public long getAddress(){
        return this.address;
    }


}
