import java.io.*;
import java.text.*;
import java.util.*;

public class Movie {

    static SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

    private long pos;
   // private String movieId;
    private String title;
    private ArrayList<String> genres;
    private int duration;
    private String contentType;
    private Date dateAdded;

    public Movie() {
        this.pos = 0;
        //this.movieId = "";
        this.title = "";
        this.genres = new ArrayList<String>();
        this.duration = 0;
        this.contentType = "";
        this.dateAdded = null;
    }

    public Movie(String movieId, String title, ArrayList<String> genres, int duration, String contentType,
            Date dateAdded) {
       // this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.duration = duration;
        this.contentType = contentType;
        this.dateAdded = dateAdded;
    }

    // Gets e Sets
    public void set_pos(long p){
        this.pos = p;
    }

    public long get_pos(){
        return this.pos;
    }

    // public void set_movieId(String movieId) {
    //     this.movieId = movieId;
    // }

    // public String get_movieId() {
    //     return movieId;
    // }

    public void set_title(String title) {
        this.title = title;
    }

    public String get_title() {
        return title;
    }

    public void set_genres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> get_genres() {
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

    public void read(String line) {
        int index = 0, atr_index = 0;

        // ---------------------------------- //
        // Find "ID"
        while (true) {
            index++;
            if (line.charAt(index) == ';') {
                //this.movieId = line.substring(atr_index, index);
                atr_index = ++index;
                break;
            }
        }
        // ---------------------------------- //
        // Find "title"
        if (line.charAt(atr_index) != ';') {
            if (line.charAt(atr_index) == '#')
                atr_index++;

            while (true) {
                index++;
                if (line.charAt(index) == ';') {
                    this.title = line.substring(atr_index, index);
                    index++;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;
        // ---------------------------------- //
        // Find "genres"
        if (line.charAt(index) != ';') {
            while (true) {
                if(line.charAt(index) == ';'){
                    index++;
                    atr_index = index;
                    break;
                }else{
                int wordStart = index++;
                while (true) {
                    index++;
                    if (line.charAt(index) == ',' ||line.charAt(index) == ';') {
                        this.genres.add(line.substring(wordStart, index));
                        break;
                    } 
                }
            }

            }
        } else {
            atr_index = ++index;
        }
        // ---------------------------------- //
        // Find "duration"
        if (line.charAt(index) != ';') {
            while (true) {
                index++;
                if (line.charAt(index) == ';') {
                    this.duration = Integer.parseInt(line.substring(atr_index, index));
                    atr_index = ++index;
                    break;
                }
            }
        } else {
            atr_index = ++index;
        }
        // ---------------------------------- //
        // Find "contentType"
        if (line.charAt(index) != ';') {
            while (true) {
                index++;
                if (line.charAt(index) == ';') {
                    this.contentType = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else {
            atr_index = ++index;
        }
        // ---------------------------------- //
        // Find "Date"
        if (line.charAt(atr_index) != ';') {
            while (index != line.length()) {
                index++;
            }
            try {
                this.dateAdded = format.parse(line.substring(atr_index, index));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        } else
            atr_index = ++index;
    }


    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);


        //dos.writeByte(0); //lapide

        dos.writeLong(pos); 
        
        dos.writeUTF(title);

        dos.writeShort(genres.size());
        for(String s:genres){
            dos.writeUTF(s);
        }

        dos.writeShort(duration);

        byte[] s = contentType.getBytes();
        dos.write(s, 0, 5);

        long mills = dateAdded.getTime();

        dos.writeLong(mills);


        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        //bais.skip(1); //pular lapide

        this.pos = dis.readLong();

        this.title = dis.readUTF();

        for(short i = 0; i< dis.readShort(); i++){
            genres.add(dis.readUTF());
        }

        this.duration = dis.readShort();

        byte[] s = dis.readNBytes(5);
        this.contentType = s.toString();

    
        long mills = dis.readLong();
        this.dateAdded.setTime(mills);
    }

    public String toString(){
        return "\n ID:" + pos +
                "\n Title:" + title +
                "\n Genres:" + genres.toString() +
                "\n Duration:" + duration +
                "\n Content Type:" + contentType +
                "\nDate Added:" + format.format(dateAdded);

    }
}
