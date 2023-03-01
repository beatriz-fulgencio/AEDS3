import java.io.*;
import java.util.Scanner;

import javax.print.PrintException;

public class Crud {
    private File file;
    private RandomAccessFile fileReader;

    Crud(String file) throws FileNotFoundException {
        this.file = new File(file); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode
    }

    public void writeMovie(Movie movie) throws IOException {
        byte[] ba = movie.toByteArray(); // creates a byte array from the movie information
        // Write the last id in the beggining of file
        fileReader.seek(0);
        fileReader.writeUTF(movie.get_movieId());

        // add the new movie to the file
        fileReader.seek(fileReader.length()); // goes to the end of the file
        fileReader.writeInt(ba.length); // writes the size of the object
        fileReader.write(ba); // writes the object byte array
    }

    private Movie readMovie(int fileSize, String id, boolean lapide) throws Exception {
        Movie movie = new Movie(); // movie object that will be returned

        // set already read information---

        // set if is valide
        movie.set_lapide(lapide);
        // set movie id
        movie.set_movieId(id);

        // read and set the rest of the movie atributes ---

        fileReader.readInt();
        movie.set_title(fileReader.readUTF()); // set title

        int n = fileReader.readInt();// read the number of genres in the multivalued atribute
        String[] s = new String[n]; // create array
        for (int i = 0; i < n; i++) { // set array
            fileReader.readInt();
            s[i] = fileReader.readUTF();
        }
        movie.set_genres(s); // set genres

        movie.set_duration(fileReader.readInt()); // set duratioin of the movie

        fileReader.readInt();
        movie.set_contentType(fileReader.readUTF()); // set the content type of the movie

        fileReader.readInt();
        movie.set_dateAdded(fileReader.readUTF()); // set the date of the movie

        return movie;
    }

    public void read(String id) throws IOException {
        fileReader.seek(0); // set the poiter at the beggining of the file
        fileReader.readUTF();// skip last id
        int sizeMovie;
        boolean lapide;
        String movieId;
        try {
            while (fileReader.getFilePointer() < fileReader.length()) { // while the file is not done
                sizeMovie = fileReader.readInt(); // read the size of the object being read
                lapide = fileReader.readBoolean(); // see if movie is valid
                if (lapide) {
                    fileReader.readInt();
                    movieId = fileReader.readUTF();
                    if (movieId.equals(id)) { // see if the id is the one being searched
                        System.out.println(readMovie(sizeMovie, id, lapide));
                        break;
                    } else {
                        fileReader.skipBytes(sizeMovie - 1); // if is not the one being searched go to next one
                    }
                } else {
                    fileReader.skipBytes(sizeMovie - 1); // if is not valid go to next one
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Movie read(String id, Movie selectMovie) throws IOException { // same read as the prior function but this one
                                                                         // returns a movie object
        fileReader.seek(0);
        fileReader.readUTF();
        int sizeMovie;
        boolean lapide;
        String movieId;

        try {
            while (fileReader.getFilePointer() < fileReader.length()) {
                sizeMovie = fileReader.readInt();
                lapide = fileReader.readBoolean();
                if (lapide) {
                    fileReader.readInt();
                    movieId = fileReader.readUTF();
                    if (movieId.equals(id)) {
                        selectMovie = readMovie(sizeMovie, id, lapide); // save the movie
                        break;
                    } else {
                        fileReader.skipBytes(sizeMovie - 5);
                    }
                } else {
                    fileReader.skipBytes(sizeMovie - 5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectMovie; // return movie
    }

    /* CRUD */// --------------------
    public void create() throws Exception {
        Scanner sc = new Scanner(System.in); // scanner to read terminal information
        Movie movie = new Movie();

        fileReader.seek(0);// go to the begginning of the file
        long lastPos = Integer.parseInt(fileReader.readUTF()); // get the last id
        lastPos++; // increment last id
        movie.set_movieId(lastPos); // set new id

        /* Get written information ------------------------- */
        System.out.println("Digite o título do filme:");
        movie.set_title(sc.nextLine());

        System.out.println("Digite os gêneros do filme (separe-os com vírgula): ");
        movie.set_genres(sc.nextLine().split(","));

        System.out.println("Digite a duração do filme (em minutos e só em números):");
        movie.set_duration(Integer.parseInt(sc.nextLine()));

        System.out.println("Digite o tipo do conteúdo (filme, documentário...):");
        movie.set_contentType(sc.nextLine());

        System.out.println("Digite a data de lançamento do filme (MMMM dd, yyyy):");
        movie.set_dateAdded(sc.nextLine());

        // System.out.println(movie);

        writeMovie(movie);// add movie to byte file

        sc.close();

    }

    public Movie select(long x) throws Exception {
        x += 6;
        System.out.println("Entrou no select");
        int registerSize;
        long id;
        boolean lapide;

        fileReader.seek(0); // ponteiro na posição inicial
        fileReader.skipBytes(4); // pula 2 bytes iniciais

        try {
            // System.out.println("Entrou no try");
            Movie movie = null;

            while (fileReader.getFilePointer() < fileReader.length()) {
                registerSize = fileReader.readInt(); // temos o tamanho do registro?!
                lapide = fileReader.readBoolean(); // confere se o registro é válido
                id = fileReader.read(); // recebe o id

                if (lapide) {
                    if (id == x) {
                        System.out.println("Id selecionado: " + id);

                        movie = new Movie();

                        // setando as informações do filme cujo id está sendo buscado
                        movie.set_movieId(id);

                        fileReader.readInt();
                        movie.set_title(fileReader.readUTF());

                        int n = fileReader.readInt();// read the number of genres in the multivalued atribute
                        String[] s = new String[n]; 
                        for (int i = 0; i < n; i++) { 
                            fileReader.readInt();
                            s[i] = fileReader.readUTF();
                        }
                        movie.set_duration(fileReader.readInt());

                        fileReader.readInt();
                        movie.set_contentType(fileReader.readUTF());

                        movie.set_dateAdded(fileReader.readUTF());

                    }
                }
            }
            return movie;

        } catch (IOException e) { // Id não foi encontrado
            System.err.println("Id não encontrado");
        }
        return null;
    }

    public void update() {

    }

    public void delete() {

    }

    public void clear() {
        file.delete();
    }
}
