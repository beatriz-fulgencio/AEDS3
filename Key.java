public class Key {
    int id;
    long address;
    int position; // position on the node

    // constructor
    public Key() {
        this.id = 0;
        this.address = 0;
        this.position = 0;
    }

    public Key(int id, long address, int position) {
        this.id = id;
        this.address = address;
        this.position = position;
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

    public int get_position() {
        return position;
    }
    public void set_position(int position) {
        this.position = position;
    }

}