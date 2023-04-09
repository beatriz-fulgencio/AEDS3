public class Key {
    int id;
    long address;
    int position; // position on the node

    // gets e construtor
    public Key() {
        this.id = 0;
        this.address = 0;
    }

    public Key(int id, int address) {
        this.id = id;
        this.address = address;
    }

    public int get_id () {
        return id;
    }

    public long get_address () {
        return address;
    }
}