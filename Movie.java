import java.io.*;
import java.text.*;
import java.util.*;

public class Movie {

    static SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

     private long pos;
    private String movieId;
    private String title;
    private String genres;
    private int duration;
    private String contentType;
    private Date dateAdded;


    public Movie() {
        this.movieId = "";
        this.title = "";
        this.genres = null;
        this.duration = 0;
        this.contentType = "";
        this.dateAdded = null;
    }

    public Movie(long pos, String movieId, String title, String genres, int duration, String contentType,
            Date dateAdded) {
        this.pos = pos;
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.duration = duration;
        this.contentType = contentType;
        this.dateAdded = dateAdded;
    }

    public Movie(long pos){
        this.pos = pos;
    }

    // Gets e Sets

    public void set_pos(long p) {
        pos++;
    }

    public long get_pos() {
        return pos;
    }

    public void set_movieId(String movieId) {
        this.movieId = movieId;
    }

    public String get_movieId() {
        return movieId;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public String get_title() {
        return title;
    }

    public void set_genres(String genres) {
        this.genres = genres;
    }

    public String get_genres() {
        return genres;
    }

    public void set_duration(int duration) {
        this.duration = duration;
    }

    public int get_duration() {
        return duration;
    }

    public void set_contentType(String contentType) {
        this.contentType = contentType;
    }

    public String get_contentType() {
        return contentType;
    }

    public void set_dateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date get_dateAdded() {
        return dateAdded;
    }

    private String[] splitLine(String str) {
    String[] atributos = new String[6];
    int cont = 0;
    String aux = "";

    for (int i = 0; i < str.length(); i++) {
    if (i != str.length() - 1) {
    if (str.charAt(i) != ';') {
    aux += str.charAt(i);
    } else {
    atributos[cont++] = aux;
    aux = "";
    }
    } else {
    aux += str.charAt(i);
    atributos[cont++] = aux;
    aux = "";
    }
    }
    return atributos;
    }

    public Date convertToDate(String strData) throws Exception {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        date = sdf.parse(strData);
        return date;
    }

    public void read(String line) throws Exception {
    String[] atributos = splitLine(line);

    // Transforma a posição em uma String de tamanho fixo
    String padded = String.format("%04d" , pos);

    // System.out.println(padded);
    //set_pos(pos);
    set_movieId(padded);
    set_title(atributos[1]);
    set_genres(atributos[2]);
    set_duration(Integer.parseInt(atributos[3]));
    set_contentType(atributos[4]);
    set_dateAdded(convertToDate(atributos[5]));

    // System.out.println("\nID: " + movieId +
    // "\nTitle: " + title +
    // "\nGenres: " + genres +
    // "\nDuration: " + duration +
    // "\nContent Type: " + contentType +
    // "\nDate Added: " + format.format(dateAdded));
    }

    
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeBytes(movieId);
        dos.writeUTF(title);
         dos.writeUTF(genres);
        dos.writeInt(duration);
        dos.writeUTF(contentType);
        dos.writeLong(dateAdded.getTime());

        return baos.toByteArray();
    }

    public void fromByteArray(byte ba[]) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        movieId = dis.readUTF();
        title = dis.readUTF();
        genres = dis.readUTF();
        duration = dis.readInt();
        contentType = dis.readUTF();
        long mills = dis.readLong();

    }

    public String toString() {
        return
        // "\nPosição: " + padded +
        "\nID: " + movieId +
                "\nTitle: " + title +
                "\nGenres: " + genres +
                "\nDuration: " + duration +
                "\nContent Type: " + contentType +
                "\nDate Added: " + format.format(dateAdded);
    }
}
