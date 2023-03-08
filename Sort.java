import java.io.*;

public class Sort {

    private File file;
    private RandomAccessFile fileReader;
    private long position;


    File file_1 = new File("arquivo1.txt");
    RandomAccessFile file1 = new RandomAccessFile("arquivo1.txt", "rw");

    File file_2 = new File("arquivo2.txt");
    RandomAccessFile file2 = new RandomAccessFile("arquivo2.txt", "rw");
    /* Intercalação balanceada comum */

    Sort(String file) throws FileNotFoundException {
        this.file = new File(file); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode
    }

    public void intercalacaoBalanceadaComum() throws Exception {

        int fileControl=0;
        fileReader.seek(0); // set the poiter at the beggining of the file
        fileReader.readUTF();// skip last id

        Movie[] array = new Movie[10];

       
         
        int arq = 0; // contabiliza os elementos do array

       while(/*fileReader.getFilePointer() < fileReader.length()*/arq<20 ){
            int currentElement = 0; // contabiliza os elementos do array
            while (currentElement < 10) {
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
                for (int i=0; i<10; i++) {
                    // add array[currentElement] to "arquivo1.txt"
                    writeMovie(array[i], file1);
                }
            } else {
                // add arrays to second file
                for (int i=0; i<10; i++) {
                    // add array[currentElement] to "arquivo2.txt"
                    writeMovie(array[i], file2);
                }
            }
            fileControl++;
        }

















        // int qualArq = 0; // define o arquivo que receberá o array de registros
        // int elementoArray = 0; // contabiliza os elementos do array

        // do {
        //     // Preenche o array
        //     if (elementoArray < 100 && !eof) {
        //         arq.seek(pos); // posiciona o ponteiro no inicio do proximo registro
        //         boolean lapide = arq.readBoolean(); // leitura da lapide
        //         tam = arq.readInt(); // leitura do tamanho do registro
        //         if (lapide == true) { // verifica se o registro e valido
        //             byte[] arrayByte = new byte[tam];
        //             arq.read(arrayByte); // leitura do array de bytes
        //             array[elementoArray].fromByteArray(arrayByte); // transforma o array de bytes em um objeto Movie

        //             // System.out.println(elementoArray +" "+ array[elementoArray].getId());

        //             if (array[elementoArray].get_movieId().equals(cabecalho)) { // testa se o arquivo de leitura chegou
        //                                                                         // no fim
        //                 eof = true;
        //             }
        //             elementoArray++; // acrescenta 1 para acompanhar a proxima posiçao
        //         }
        //         pos += tam + 4 + 1; // adiciona a quantidade de bytes para chegar no inicio do proximo registro

        //         // Segunda parte - Array ja esta preenchido, então será transferido para o
        //         // proximo arquivo
        //     } else {
        //         array = quicksort(array, 0, elementoArray - 1); // ordenação interna
        //         /*
        //          * for(int i=0; i< array.length; i++){
        //          * System.out.println(array[i].getId() + " " + array[i].getTitle());
        //          * }
        //          */
        //         if (qualArq % 2 == 0) { // se o contador de arquivo for par, o vetor sera inserido no primeiro arquivo
        //             for (int i = 0; i < elementoArray; i++) {
        //                 writeArq(array[i], "arquivo1.txt");
        //             }
        //         } else { // caso contrario, o vetor sera inserido no segundo arquivo
        //             for (int i = 0; i < elementoArray; i++) {
        //                 writeArq(array[i], "arquivo2.txt");
        //             }
        //         }
        //         // essa parte so é importante quando o vetor não possui 500 elementos, isso é,
        //         // quando o arquivo lido chego no fim
        //         if (elementoArray == 500) {
        //             qualArq++; // troca o arquivo que ira receber o proximo array
        //             elementoArray = 0; // para o array ser preenchido novamente, é necessario zerar o contador
        //         } else { // se o vetor possuir menos que 500 elementos, significa que a distribuiçao
        //                  // chegou ao fim
        //             elementoArray = -1;
        //         }

        //     }
        // } while (!eof || elementoArray >= 0);
    }

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
        public void quicksort(Movie[]vetor){
            quicksort(vetor, 0,vetor.length-1);
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


    public void clear(){
        file_1.delete();
        file_2.delete();
    }
}
