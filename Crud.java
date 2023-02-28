import java.io.*;

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
        fileReader.writeBoolean(true); // writes the movie as valid
        fileReader.writeInt(ba.length); //writes the size of the object
        fileReader.write(ba); //writes the object byte array
    }
    
}
