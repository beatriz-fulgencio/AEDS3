public class Key {
    int id;
    long address;
    int position; // position on the node

    // gets e construtor
    public Key() {
        this.id = 0;
        this.address = 0;
        this.position = 0;
    }

    public Key(int id, int address) {
        this.id = id;
        this.address = address;
        this.position = position;
    }

    public int get_id () {
        return id;
    }

    public long get_address () {
        return address;
    }

    public void set_position(int position) {
        this.position = position;
    }

    public int get_position () {
        return position;
    }

}
