import java.io.*;

public class ListaInvertida {
    private File file;
    private RandomAccessFile fileReader;
    private Movie[] movies;

    public void List(String F) throws IOException {
        this.file = new File(F); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw");
    }

    int i, j;

    Matrix m = new Matrix();

    // get the duration
    for(i=0;i<20;i++) {
        for (j = 0; j < 20; j++) {
            if (movies[i].get_duration != movies[j].get_duration && i != j) {
                m.durationArray.push(movies[i].get_duration);
            }
        }
    }

    // get the genres
    for(i=0;i<20;i++) {
        for (int j = 0; j < 20; j++) {
            if (movies[i].get_genres != movies[j].get_genres && i != j) {
                m.genresArray.push(movies[i].get_genres);
            }
        }
    }

    // creating the matrix

    for(i=0;i<durationArray.length;i++) {
        for (j = 0; j < movies.length; j++) {
            if (movies[j].get_duration == durationArray[i]) {

            }
        }
    }
}
