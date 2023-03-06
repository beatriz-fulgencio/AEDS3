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
                        fileReader.skipBytes(sizeMovie - 11); // if is not the one being searched go to next one
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
                        fileReader.skipBytes(sizeMovie - 11);
                    }
                } else {
                    fileReader.skipBytes(sizeMovie - 1);
                }
            }
        } catch (Exception e) {
            System.err.println("Id " + id + "não encontrado");
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

    public Movie select(String id) throws IOException {
        Movie movie = new Movie();
        movie = read(id, movie);
        return movie;
    }

    public void update() {

    }

    // public void update(Movie m) {
    // fileReader.seek(0); // set the poiter at the beggining of the file
    // fileReader.readUTF();// skip last id
    // int sizeMovie;
    // boolean lapide;
    // String movieId;
    // try {
    // while (fileReader.getFilePointer() < fileReader.length()) { // while the file
    // is not done
    // sizeMovie = fileReader.readInt(); // read the size of the object being read
    // lapide = fileReader.readBoolean(); // see if movie is valid
    // if (lapide) {
    // fileReader.readInt();
    // movieId = fileReader.readUTF();
    // if (movieId.equals(id)) { // see if the id is the one being searched
    // System.out.println(readMovie(sizeMovie, id, lapide));
    // break;
    // } else {
    // fileReader.skipBytes(sizeMovie - 11); // if is not the one being searched go
    // to next one
    // }
    // } else {
    // fileReader.skipBytes(sizeMovie - 1); // if is not valid go to next one
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    public void delete(String id) throws IOException {
        fileReader.seek(0); // set the poiter at the beggining of the file
        fileReader.readUTF();// skip last id
        int sizeMovie;
        boolean lapide;
        String movieId;
        long lapidePos;
        try {
            while (fileReader.getFilePointer() < fileReader.length()) { // while the file is not done
                sizeMovie = fileReader.readInt(); // read the size of the object being read
                lapidePos = fileReader.getFilePointer(); // saves the position of the lapide
                lapide = fileReader.readBoolean(); // see if movie is valid
                if (lapide) {
                    fileReader.readInt();
                    movieId = fileReader.readUTF();
                    if (movieId.equals(id)) { // see if the id is the one being searched
                        fileReader.seek(lapidePos);// return to lapide's position
                        fileReader.writeBoolean(false); // delete the archive
                        fileReader.skipBytes(10);// skip the bytes returned
                        System.out.println("Filme deletado:\n " + readMovie(sizeMovie, id, false));
                        break;
                    } else {
                        fileReader.skipBytes(sizeMovie - 11); // if is not the one being searched go to next one
                    }
                } else {
                    fileReader.skipBytes(sizeMovie - 1); // if is not valid go to next one
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void clear() {
        file.delete();
    }


    /* Intercalação balanceada comum */
    
    public static void intercalacaoBalanceadaComum() throws IOException {
        RandomAccessFile arq = new RandomAccessFile("arquivo.txt", "rw");

        // DISTRIBUIÇAO
        arq.seek(0); // posiciona o ponteiro no início do arquivo
        int cabecalho = arq.readInt(); // descobre qual o id do último registro do arquivo
        int pos = 4; // posição do primeiro registro
        int tam = 0;
        Movie[] array = new Movie[100];
        boolean eof = false;

        for (int i = 0; i < 100; i++) { // cria o array de Movies
            array[i] = new Movie();
        }

        int qualArq = 0; // define o arquivo que receberá o array de registros
        int elementoArray = 0; // contabiliza os elementos do array

        do {
            // Preenche o array
            if (elementoArray < 100 && !eof) {
                arq.seek(pos); // posiciona o ponteiro no inicio do proximo registro
                boolean lapide = arq.readBoolean(); // leitura da lapide
                tam = arq.readInt(); // leitura do tamanho do registro
                if (lapide == true) { // verifica se o registro e valido
                    byte[] arrayByte = new byte[tam];
                    arq.read(arrayByte); // leitura do array de bytes
                    array[elementoArray].fromByteArray(arrayByte); // transforma o array de bytes em um objeto Movie

                    // System.out.println(elementoArray +" "+ array[elementoArray].getId());

                    if (array[elementoArray].get_movieId().equals(cabecalho)) { // testa se o arquivo de leitura chegou
                                                                                // no fim
                        eof = true;
                    }
                    elementoArray++; // acrescenta 1 para acompanhar a proxima posiçao
                }
                pos += tam + 4 + 1; // adiciona a quantidade de bytes para chegar no inicio do proximo registro

                // Segunda parte - Array ja esta preenchido, então será transferido para o
                // proximo arquivo
            } else {
                array = quicksort(array, 0, elementoArray - 1); // ordenação interna
                /*
                 * for(int i=0; i< array.length; i++){
                 * System.out.println(array[i].getId() + " " + array[i].getTitle());
                 * }
                 */
                if (qualArq % 2 == 0) { // se o contador de arquivo for par, o vetor sera inserido no primeiro arquivo
                    for (int i = 0; i < elementoArray; i++) {
                        writeArq(array[i], "arquivo1.txt");
                    }
                } else { // caso contrario, o vetor sera inserido no segundo arquivo
                    for (int i = 0; i < elementoArray; i++) {
                        writeArq(array[i], "arquivo2.txt");
                    }
                }
                // essa parte so é importante quando o vetor não possui 500 elementos, isso é,
                // quando o arquivo lido chego no fim
                if (elementoArray == 500) {
                    qualArq++; // troca o arquivo que ira receber o proximo array
                    elementoArray = 0; // para o array ser preenchido novamente, é necessario zerar o contador
                } else { // se o vetor possuir menos que 500 elementos, significa que a distribuiçao
                         // chegou ao fim
                    elementoArray = -1;
                }

            }
        } while (!eof || elementoArray >= 0);
    }



    /* Quicksort -> utilizado para ordenação em memória principal */

    private void quicksort(Movie[] vetor, int inicio, int fim) {
        if (fim > inicio) {
            // chamadas recursivas
            int pivo = dividir(vetor, inicio, fim);
            quicksort(vetor, inicio, pivo - 1);
            quicksort(vetor, pivo + 1, fim);
        }
    }

    private int dividir(Movie[] vetor, int inicio, int fim) {
        Movie pivo;
        int esq, dir = fim;
        esq = inicio + 1;
        pivo = vetor[inicio];

        while (esq <= dir) {
            // percorre até que passe o outro ponteiro ou o elemento seja menor que o pivô
            while (esq <= dir && vetor[esq] <= pivo) {
                esq++;
            }

            // percorre até que passe o outro ponteiro ou o elemento seja maior que o pivô
            while (dir >= esq && vetor[dir] > pivo) {
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
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

}
