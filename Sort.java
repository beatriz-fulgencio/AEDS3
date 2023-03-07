public class Sort {
    
     /* Intercalação balanceada comum */
    
    // public static void intercalacaoBalanceadaComum() throws IOException {
    //     RandomAccessFile arq = new RandomAccessFile("arquivo.txt", "rw");

    //     // DISTRIBUIÇAO
    //     arq.seek(0); // posiciona o ponteiro no início do arquivo
    //     int cabecalho = arq.readInt(); // descobre qual o id do último registro do arquivo
    //     int pos = 4; // posição do primeiro registro
    //     int tam = 0;
    //     Movie[] array = new Movie[100];
    //     boolean eof = false;

    //     for (int i = 0; i < 100; i++) { // cria o array de Movies
    //         array[i] = new Movie();
    //     }

    //     int qualArq = 0; // define o arquivo que receberá o array de registros
    //     int elementoArray = 0; // contabiliza os elementos do array

    //     do {
    //         // Preenche o array
    //         if (elementoArray < 100 && !eof) {
    //             arq.seek(pos); // posiciona o ponteiro no inicio do proximo registro
    //             boolean lapide = arq.readBoolean(); // leitura da lapide
    //             tam = arq.readInt(); // leitura do tamanho do registro
    //             if (lapide == true) { // verifica se o registro e valido
    //                 byte[] arrayByte = new byte[tam];
    //                 arq.read(arrayByte); // leitura do array de bytes
    //                 array[elementoArray].fromByteArray(arrayByte); // transforma o array de bytes em um objeto Movie

    //                 // System.out.println(elementoArray +" "+ array[elementoArray].getId());

    //                 if (array[elementoArray].get_movieId().equals(cabecalho)) { // testa se o arquivo de leitura chegou
    //                                                                             // no fim
    //                     eof = true;
    //                 }
    //                 elementoArray++; // acrescenta 1 para acompanhar a proxima posiçao
    //             }
    //             pos += tam + 4 + 1; // adiciona a quantidade de bytes para chegar no inicio do proximo registro

    //             // Segunda parte - Array ja esta preenchido, então será transferido para o
    //             // proximo arquivo
    //         } else {
    //             array = quicksort(array, 0, elementoArray - 1); // ordenação interna
    //             /*
    //              * for(int i=0; i< array.length; i++){
    //              * System.out.println(array[i].getId() + " " + array[i].getTitle());
    //              * }
    //              */
    //             if (qualArq % 2 == 0) { // se o contador de arquivo for par, o vetor sera inserido no primeiro arquivo
    //                 for (int i = 0; i < elementoArray; i++) {
    //                     writeArq(array[i], "arquivo1.txt");
    //                 }
    //             } else { // caso contrario, o vetor sera inserido no segundo arquivo
    //                 for (int i = 0; i < elementoArray; i++) {
    //                     writeArq(array[i], "arquivo2.txt");
    //                 }
    //             }
    //             // essa parte so é importante quando o vetor não possui 500 elementos, isso é,
    //             // quando o arquivo lido chego no fim
    //             if (elementoArray == 500) {
    //                 qualArq++; // troca o arquivo que ira receber o proximo array
    //                 elementoArray = 0; // para o array ser preenchido novamente, é necessario zerar o contador
    //             } else { // se o vetor possuir menos que 500 elementos, significa que a distribuiçao
    //                      // chegou ao fim
    //                 elementoArray = -1;
    //             }

    //         }
    //     } while (!eof || elementoArray >= 0);
    // }



    // /* Quicksort -> utilizado para ordenação em memória principal */

    // private void quicksort(Movie[] vetor, int inicio, int fim) {
    //     if (fim > inicio) {
    //         // chamadas recursivas
    //         int pivo = dividir(vetor, inicio, fim);
    //         quicksort(vetor, inicio, pivo - 1);
    //         quicksort(vetor, pivo + 1, fim);
    //     }
    // }

    // private int dividir(Movie[] vetor, int inicio, int fim) {
    //     Movie pivo;
    //     int esq, dir = fim;
    //     esq = inicio + 1;
    //     pivo = vetor[inicio];

    //     while (esq <= dir) {
    //         // percorre até que passe o outro ponteiro ou o elemento seja menor que o pivô
    //         while (esq <= dir && vetor[esq] <= pivo) {
    //             esq++;
    //         }

    //         // percorre até que passe o outro ponteiro ou o elemento seja maior que o pivô
    //         while (dir >= esq && vetor[dir] > pivo) {
    //             dir--;
    //         }

    //         // maior e menor valor localizados
    //         if (esq < dir) {
    //             swap(vetor, dir, esq);
    //             esq++;
    //             dir--;
    //         }
    //     }

    //     swap(vetor, inicio, dir);
    //     return dir;
    // }

    // private void swap(Movie[] vetor, int i, int j) {
    //     int temp = vetor[i];
    //     vetor[i] = vetor[j];
    //     vetor[j] = temp;
    // }

}
