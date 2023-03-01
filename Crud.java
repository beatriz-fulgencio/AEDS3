import java.io.*;

import javax.print.PrintException;

public class Crud {
    private File file;
    private RandomAccessFile fileReader;

    Crud(String file) throws FileNotFoundException{
        this.file = new File(file); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); //opens the file in read and write mode
    }

    public void writeMovie(Movie movie) throws IOException{
        byte[] ba = movie.toByteArray(); //creates a byte array from the movie information
        //Write the last id in the beggining of file
        fileReader.seek(0);
        fileReader.writeBytes(movie.get_movieId());
        
        //add the new movie to the file
        fileReader.seek(fileReader.length()); //goes to the end of the file
        fileReader.writeInt(ba.length); //writes the size of the object
        fileReader.write(ba); //writes the object byte array
    }

    private Movie readMovie(int fileSize, String id, boolean lapide) throws Exception{
        Movie movie = new Movie();

        movie.set_lapide(lapide);
        movie.set_movieId(id);

        fileReader.readInt();
        movie.set_title(fileReader.readUTF());

        int n = fileReader.readInt();
        String[] s = new String[n];
        for(int i=0; i< n; i++){
            fileReader.readInt();
           s[i] = fileReader.readUTF();
        }
        movie.set_genres(s);

        movie.set_duration(fileReader.readInt());

        fileReader.readInt();
        movie.set_contentType(fileReader.readUTF());

        fileReader.readInt();
        movie.set_dateAdded(fileReader.readUTF());

        return movie;
    }

    public void read(String id) throws IOException{
        fileReader.seek(0);
        fileReader.skipBytes(4);
        int sizeMovie;
        boolean lapide;
        String movieId;

        try {
            while(fileReader.getFilePointer() < fileReader.length()){
                sizeMovie = fileReader.readInt();
                lapide = fileReader.readBoolean();
                if(lapide){
                    fileReader.readInt();
                    movieId= fileReader.readUTF();
                    if(movieId.equals(id)){
                        System.out.println(readMovie(sizeMovie, id, lapide));
                        break;
                    }else{
                        fileReader.skipBytes(sizeMovie - 5);
                    }
                }else{
                    fileReader.skipBytes(sizeMovie - 5);
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public Movie read(String id, Movie selectMovie) throws IOException{
        fileReader.seek(0);
        fileReader.skipBytes(4);
        int sizeMovie;
        boolean lapide;
        String movieId;

        try {
            while(fileReader.getFilePointer() < fileReader.length()){
                sizeMovie = fileReader.readInt();
                lapide = fileReader.readBoolean();
                if(lapide){
                    fileReader.readInt();
                    movieId= fileReader.readUTF();
                    if(movieId.equals(id)){
                        selectMovie = readMovie(sizeMovie, id, lapide);
                        break;
                    }else{
                        fileReader.skipBytes(sizeMovie - 5);
                    }
                }else{
                    fileReader.skipBytes(sizeMovie - 5);
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return selectMovie;
    }

    /*CRUD *///--------------------
    public void create(){

    }

    public void Select(){

    }

    public void update(){

    }

    public void delete(){

    }

    public void clear(){
        file.delete();
    }
}
