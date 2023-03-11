import java.io.*;

public class Sort {

    private File file;
    private RandomAccessFile fileReader;
    private long position;

    File file_1 = new File("arquivo1.db");
    RandomAccessFile file1 = new RandomAccessFile("arquivo1.db", "rw");

    File file_2 = new File("arquivo2.db");
    RandomAccessFile file2 = new RandomAccessFile("arquivo2.db", "rw");

    File file_3 = new File("arquivo3.db");
    RandomAccessFile file3 = new RandomAccessFile("arquivo3.db", "rw");

    File file_4 = new File("arquivo4.db");
    RandomAccessFile file4 = new RandomAccessFile("arquivo4.db", "rw");

    int total =100;

    /* Intercalação balanceada comum */

    Sort(String file) throws FileNotFoundException {
        this.file = new File(file); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode
    }

    public void intercalacaoBalanceadaComum() throws Exception {

        int fileControl = 0;
        fileReader.seek(0); // set the poiter at the beggining of the file
        int finalId = Integer.parseInt(fileReader.readUTF());// skip last id

        total = finalId+1;
        Movie[] array = new Movie[total/2];

        int arq = 0; // contabiliza os elementos do array

        while (/* fileReader.getFilePointer() < fileReader.length() */arq < total) {
            int currentElement = 0; // contabiliza os elementos do array
            while (currentElement < total/2) {
                array[currentElement] = new Movie();
                int sizeMovie = fileReader.readInt();
                position = fileReader.getFilePointer();
                setMovie(array[currentElement]);
                fileReader.seek(position);
                fileReader.skipBytes(sizeMovie);
                currentElement++;
                arq++;
            }

            quicksort(array);

            // 2 ways:
            if (fileControl % 2 == 0) {
                // add arrays to first file
                for (int i = 0; i < total/2; i++) {
                    // add array[currentElement] to "arquivo1.db"
                    writeMovie(array[i], file1);
                }
            } else {
                // add arrays to second file
                for (int i = 0; i < total/2; i++) {
                    // add array[currentElement] to "arquivo2.db"
                    writeMovie(array[i], file2);
                }
            }
            fileControl++;
        }

        /* Primeira Intercalação */

        Movie movie1 = new Movie();
        Movie movie2 = new Movie();

        file1.seek(0);
        file2.seek(0);

        int cont1 = 0;
        int cont2 = 0;
        // int contGeral = 0;

        int x = total/4;

        for (int u = 0; u <= (total/8) ; u++) {

            if (u % 2 == 0) { // file control
                for (int i = 0; i < x /* 200 */ ; i++) {

                    String id1 = "";
                    String id2 = "";
                    int sizeMovie1 = 0;
                    int sizeMovie2 = 0;
                    long position1 = 0;
                    long position2 = 0;
                    long firstPosition1 = 0;
                    long firstPosition2 = 0;

                    if (file1.getFilePointer() < file1.length()) {
                        firstPosition1 = file1.getFilePointer();
                        sizeMovie1 = file1.readInt(); // reads the register size
                        position1 = file1.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b1 = file1.readBoolean(); // checks if the register is valid
                        file1.readInt(); // reads 4
                        id1 = file1.readUTF(); // reads the movie id
                        movie1 = readMovie(sizeMovie1, id1, b1, file1);

                    }

                    if (file2.getFilePointer() < file2.length()) {
                        firstPosition2 = file2.getFilePointer();
                        sizeMovie2 = file2.readInt(); // reads the register size
                        position2 = file2.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b2 = file2.readBoolean(); // checks if the register is valid
                        file2.readInt(); // reads 4
                        id2 = file2.readUTF(); // reads the movie id
                        movie2 = readMovie(sizeMovie2, id2, b2, file2);

                    }

                    if (cont1 < total/2 && cont2 < total/2) {
                        if (id1.compareTo(id2) < 0) {
                            writeMovie(movie1, file3);
                            file1.seek(position1);
                            file1.skipBytes(sizeMovie1);
                            file2.seek(firstPosition2);
                            cont1++;
                            // contGeral++;

                        } else {
                            writeMovie(movie2, file3);
                            file2.seek(position2);
                            file2.skipBytes(sizeMovie2);
                            file1.seek(firstPosition1);
                            cont2++;
                            // contGeral++;
                        }
                    } else if (cont1 < total/2) {
                        writeMovie(movie1, file3);
                        file1.seek(position1);
                        file1.skipBytes(sizeMovie1);
                        file2.seek(firstPosition2);
                        cont1++;
                        // contGeral++;

                    } else if (cont2 < total/2) {
                        writeMovie(movie2, file3);
                        file2.seek(position2);
                        file2.skipBytes(sizeMovie2);
                        file1.seek(firstPosition1);
                        cont2++;
                        // contGeral++;

                    }
                }
            } else {
                for (int i = 0; i < x /* 200 */; i++) {

                    String id1 = "";
                    String id2 = "";
                    int sizeMovie1 = 0;
                    int sizeMovie2 = 0;
                    long position1 = 0;
                    long position2 = 0;
                    long firstPosition1 = 0;
                    long firstPosition2 = 0;

                    if (file1.getFilePointer() < file1.length()) {
                        firstPosition1 = file1.getFilePointer();
                        sizeMovie1 = file1.readInt(); // reads the register size
                        position1 = file1.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b1 = file1.readBoolean(); // checks if the register is valid
                        file1.readInt(); // reads 4
                        id1 = file1.readUTF(); // reads the movie id
                        movie1 = readMovie(sizeMovie1, id1, b1, file1);

                    }

                    if (file2.getFilePointer() < file2.length()) {
                        firstPosition2 = file2.getFilePointer();
                        sizeMovie2 = file2.readInt(); // reads the register size
                        position2 = file2.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b2 = file2.readBoolean(); // checks if the register is valid
                        file2.readInt(); // reads 4
                        id2 = file2.readUTF(); // reads the movie id
                        movie2 = readMovie(sizeMovie2, id2, b2, file2);

                    }

                    if (cont1 < total/2 && cont2 < total/2) {
                        if (id1.compareTo(id2) < 0) {
                            writeMovie(movie1, file4);
                            file1.seek(position1);
                            file1.skipBytes(sizeMovie1);
                            file2.seek(firstPosition2);
                            cont1++;
                            // contGeral++;

                        } else {
                            writeMovie(movie2, file4);
                            file2.seek(position2);
                            file2.skipBytes(sizeMovie2);
                            file1.seek(firstPosition1);
                            cont2++;
                            // contGeral++;
                        }
                    } else if (cont1 < total/2) {
                        writeMovie(movie1, file4);
                        file1.seek(position1);
                        file1.skipBytes(sizeMovie1);
                        cont1++;
                        // contGeral++;

                    } else if (cont2 < total/2) {
                        writeMovie(movie2, file4);
                        file2.seek(position2);
                        file2.skipBytes(sizeMovie2);
                        cont2++;
                        // contGeral++;

                    }
                }

            }
        }
        //System.out.print(contGeral);

        /* Segunda intercalação */

        movie1 = new Movie();
        movie2 = new Movie();

        // clear file1 and file2
        file1.setLength(0);
        file2.setLength(0);

        // seeks the pointer at the beggining of the two files
        file3.seek(0);
        file4.seek(0);

        cont1 = 0;
        cont2 = 0;

         x = total/2;

       //int maxRead = total/2 ;

        for (int u = 0; u <= (total/16); u++) {
            System.out.println(u);
            if (u % 2 == 0) { // file control
                for (int i = 0; i < x /* total0 */; i++) {

                    String id1 = "";
                    String id2 = "";
                    int sizeMovie1 = 0;
                    int sizeMovie2 = 0;
                    long position1 = 0;
                    long position2 = 0;
                    long firstPosition1 = 0;
                    long firstPosition2 = 0;

                    if (file3.getFilePointer() < file3.length()-1) {
                        firstPosition1 = file3.getFilePointer();
                        sizeMovie1 = file3.readInt(); // reads the register size
                        position1 = file3.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b1 = file3.readBoolean(); // checks if the register is valid
                        file3.readInt(); // reads 4
                        id1 = file3.readUTF(); // reads the movie id
                        movie1 = readMovie(sizeMovie1, id1, b1, file3);

                    }

                    if (file4.getFilePointer() < file4.length()-1) {
                        firstPosition2 = file4.getFilePointer();
                        sizeMovie2 = file4.readInt(); // reads the register size
                        position2 = file4.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b2 = file4.readBoolean(); // checks if the register is valid
                        file4.readInt(); // reads 4
                        id2 = file4.readUTF(); // reads the movie id
                        movie2 = readMovie(sizeMovie2, id2, b2, file4);

                    }

                    if (cont1 < total/2 && cont2 < total/2) {
                        if (id1.compareTo(id2) < 0) {
                            writeMovie(movie1, file1);
                            file3.seek(position1);
                            file3.skipBytes(sizeMovie1);
                            file4.seek(firstPosition2);
                            cont1++;

                        } else {
                            writeMovie(movie2, file1);
                            file4.seek(position2);
                            file4.skipBytes(sizeMovie2);
                            file3.seek(firstPosition1);
                            cont2++;
                        }
                    } else if (cont1 < total/2) {
                        writeMovie(movie1, file1);
                        file3.seek(position1);
                        file3.skipBytes(sizeMovie1);
                        file4.seek(firstPosition2);
                        cont1++;

                    } else if (cont2 < total/2) {
                        writeMovie(movie2, file1);
                        file4.seek(position2);
                        file4.skipBytes(sizeMovie2);
                        file3.seek(firstPosition1);
                        cont2++;

                    }
                }
            } else {
                for (int i = 0; i < x /* total0 */; i++) {

                    String id1 = "";
                    String id2 = "";
                    int sizeMovie1 = 0;
                    int sizeMovie2 = 0;
                    long position1 = 0;
                    long position2 = 0;
                    long firstPosition1 = 0;
                    long firstPosition2 = 0;

                    if (file3.getFilePointer() < file3.length()) {
                        firstPosition1 = file3.getFilePointer();
                        sizeMovie1 = file3.readInt(); // reads the register size
                        position1 = file3.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b1 = file3.readBoolean(); // checks if the register is valid
                        file3.readInt(); // reads 4
                        id1 = file3.readUTF(); // reads the movie id
                        movie1 = readMovie(sizeMovie1, id1, b1, file3);

                    }

                    if (file4.getFilePointer() < file4.length()-1) {
                        firstPosition2 = file4.getFilePointer();
                        sizeMovie2 = file4.readInt(); // reads the register size
                        position2 = file4.getFilePointer(); // gets pointer to the beginning of the register
                        boolean b2 = file4.readBoolean(); // checks if the register is valid
                        file4.readInt(); // reads 4
                        id2 = file4.readUTF(); // reads the movie id
                        movie2 = readMovie(sizeMovie2, id2, b2, file4);

                    }

                    if (cont1 < total/2 && cont2 < total/2) {
                        if (id1.compareTo(id2) < 0) {
                            writeMovie(movie1, file2);
                            file3.seek(position1);
                            file3.skipBytes(sizeMovie1);
                            file4.seek(firstPosition2);
                            cont1++;

                        } else {
                            writeMovie(movie2, file2);
                            file4.seek(position2);
                            file4.skipBytes(sizeMovie2);
                            file3.seek(firstPosition1);
                            cont2++;
                        }
                    } else if (cont1 < total/2) {
                        writeMovie(movie1, file2);
                        file3.seek(position1);
                        file3.skipBytes(sizeMovie1);
                        cont1++;

                    } else if (cont2 < total/2) {
                        writeMovie(movie2, file2);
                        file4.seek(position2);
                        file4.skipBytes(sizeMovie2);
                        cont2++;

                    }
                }

            }
        }

        /* Terceira intercalação */
        movie1 = new Movie();
        movie2 = new Movie();

        // clear file1 and file2
        file3.setLength(0);

        // seeks the pointer at the beggining of the two files
        file1.seek(0);
        file2.seek(0);

        cont1 = 0;
        cont2 = 0;

         x = total;

         for (int i = 0; i < x /* 200 */ ; i++) {

            String id1 = "";
            String id2 = "";
            int sizeMovie1 = 0;
            int sizeMovie2 = 0;
            long position1 = 0;
            long position2 = 0;
            long firstPosition1 = 0;
            long firstPosition2 = 0;

            if (file1.getFilePointer() < file1.length()) {
                firstPosition1 = file1.getFilePointer();
                sizeMovie1 = file1.readInt(); // reads the register size
                position1 = file1.getFilePointer(); // gets pointer to the beginning of the register
                boolean b1 = file1.readBoolean(); // checks if the register is valid
                file1.readInt(); // reads 4
                id1 = file1.readUTF(); // reads the movie id
                movie1 = readMovie(sizeMovie1, id1, b1, file1);

            }

            if (file2.getFilePointer() < file2.length()) {
                firstPosition2 = file2.getFilePointer();
                sizeMovie2 = file2.readInt(); // reads the register size
                position2 = file2.getFilePointer(); // gets pointer to the beginning of the register
                boolean b2 = file2.readBoolean(); // checks if the register is valid
                file2.readInt(); // reads 4
                id2 = file2.readUTF(); // reads the movie id
                movie2 = readMovie(sizeMovie2, id2, b2, file2);

            }

            if (cont1 < total/2 && cont2 < total/2) {
                if (id1.compareTo(id2) < 0) {
                    writeMovie(movie1, file3);
                    file1.seek(position1);
                    file1.skipBytes(sizeMovie1);
                    file2.seek(firstPosition2);
                    cont1++;
                    // contGeral++;

                } else {
                    writeMovie(movie2, file3);
                    file2.seek(position2);
                    file2.skipBytes(sizeMovie2);
                    file1.seek(firstPosition1);
                    cont2++;
                    // contGeral++;
                }
            } else if (cont1 < total/2) {
                writeMovie(movie1, file3);
                file1.seek(position1);
                file1.skipBytes(sizeMovie1);
                file2.seek(firstPosition2);
                cont1++;
                // contGeral++;

            } else if (cont2 < total/2) {
                writeMovie(movie2, file3);
                file2.seek(position2);
                file2.skipBytes(sizeMovie2);
                file1.seek(firstPosition1);
                cont2++;
                // contGeral++;

            }
        }

    }

    // public void segmentosTamanhoVariavel() throws Exception {

    // }
    

    
    public void writeMovie(Movie movie, RandomAccessFile file) throws IOException {
        byte[] ba = movie.toByteArray(); // creates a byte array from the movie information
        // add the new movie to the file
        file.seek(file.length()); // goes to the end of the file
        file.writeInt(ba.length); // writes the size of the object
        file.write(ba); // writes the object byte array
    }

    private void setMovie(Movie movie) throws Exception {

        // set if is valide
        movie.set_lapide(fileReader.readBoolean());

        fileReader.readInt();

        // set movie id
        movie.set_movieId(fileReader.readUTF());

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

    }

    /* Quicksort -> utilizado para ordenação em memória principal */
    public void quicksort(Movie[] vetor) {
        quicksort(vetor, 0, vetor.length - 1);
    }

    private void quicksort(Movie[] vetor, int inicio, int fim) {
        if (fim > inicio) {
            // chamadas recursivas
            int pivo = dividir(vetor, inicio, fim);
            quicksort(vetor, inicio, pivo - 1);
            quicksort(vetor, pivo + 1, fim);
        }
    }

    private int dividir(Movie[] vetor, int inicio, int fim) {
        String pivo;
        int esq, dir = fim;
        esq = inicio + 1;
        pivo = vetor[inicio].get_movieId();

        while (esq <= dir) {
            // percorre até que passe o outro ponteiro ou o elemento seja menor que o pivô
            while (esq <= dir && vetor[esq].get_movieId().compareTo(pivo) <= 0) {
                esq++;
            }

            // percorre até que passe o outro ponteiro ou o elemento seja maior que o pivô
            while (dir >= esq && vetor[dir].get_movieId().compareTo(pivo) >= 0) {
                dir--;
            }

            // maior e menor valor localizados
            if (esq < dir) {
                swap(vetor, dir, esq);
                esq++;
                dir--;
            }
        }

        swap(vetor, inicio, dir);
        return dir;
    }

    private void swap(Movie[] vetor, int i, int j) {
        Movie temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    public void clear() {
        file_1.delete();
        file_2.delete();
        file_3.delete();
        file_4.delete();
    }

    private Movie readMovie(int fileSize, String id, boolean lapide, RandomAccessFile file) throws Exception {
        Movie movie = new Movie(); // movie object that will be returned

        // set already read information---

        // set if is valide
        movie.set_lapide(lapide);
        // set movie id
        movie.set_movieId(id);

        // read and set the rest of the movie atributes ---

        int x = file.readInt();
        movie.set_title(file.readUTF()); // set title

        int n = file.readInt();// read the number of genres in the multivalued atribute
        String[] s = new String[n]; // create array
        for (int i = 0; i < n; i++) { // set array
            file.readInt();
            s[i] = file.readUTF();
        }
        movie.set_genres(s); // set genres

        movie.set_duration(file.readInt()); // set duratioin of the movie

        file.readInt();
        movie.set_contentType(file.readUTF()); // set the content type of the movie

        file.readInt();
        movie.set_dateAdded(file.readUTF()); // set the date of the movie

        return movie;
    }


    public void read() throws IOException {
        FileWriter fileWrite = new FileWriter("Id.txt");
        int sizeMovie;
        boolean lapide;
        String movieId;
        file3.seek(0);
        try {
            while (file3.getFilePointer() < file3.length()) { // while the file is not done
                sizeMovie = file3.readInt(); // read the size of the object being read
                lapide = file3.readBoolean(); // see if movie is valid
                if (lapide) {
                    file3.readInt();
                    movieId = file3.readUTF();
                    fileWrite.write(movieId + "  ");
                    file3.skipBytes(sizeMovie - 11);
                } else {
                    file3.skipBytes(sizeMovie - 1); // if is not valid go to next one
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileWrite.close();
    }

}
