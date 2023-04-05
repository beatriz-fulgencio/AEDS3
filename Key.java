public class Key {
    int id;
    long position;

    // gets e construtor
    public Key() {
        this.id = 0;
        this.position = 0;
    }

    public Key(int id, int position) {
        this.id = id;
        this.position = position;
    }

    public int get_id () {
        return id;
    }

    public long get_position () {
        return position;
    }

}
