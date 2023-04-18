import java.io.*;

public class ListaInvertida {
    private File file;
    private RandomAccessFile fileReader;
    private Movie[] movies;

    public void List(String F) throws IOException {
        this.file = new File(F); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw");
    }
}
