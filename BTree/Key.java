public class Key {
    int id;
    long address;

    // constructor
    public Key() {
        this.id = 0;
        this.address = 0;
    }

    public Key(int id, long address) {
        this.id = id;
        this.address = address;
    }

    // gets and sets
    public int get_id() {
        return id;
    }
    public void set_id(int id) {
        this.id = id;
    }

    public long get_address() {
        return address;
    }
    public void set_address(long address) {
        this.address = address;
    }

}