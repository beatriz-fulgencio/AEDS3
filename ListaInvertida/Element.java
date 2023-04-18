public class Element {

    private long address; // the buckets beggining address
    private int id; // movie id

    // constructor
    public Element() {
        this.address = 0;
        this.id = 0;
    }

    public Element(long address, int id) {
        this.address = address;
        this.id = id;
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
}
