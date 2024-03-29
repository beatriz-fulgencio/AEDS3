import java.io.*;
import java.util.*;


public class Crud {
    private File file;
    private RandomAccessFile fileReader;
    private long position;
    // private Directory Hash;

    Crud(String file) throws FileNotFoundException {
        this.file = new File(file); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode

    }

    public void writeMovie(Movie movie, List l) throws Exception {
        byte[] ba = movie.toByteArray(); // creates a byte array from the movie information
        // Write the last id in the beggining of file
        fileReader.seek(0);
        fileReader.writeUTF(movie.get_movieId());

        long address = fileReader.length();
        // add the new movie to the file
        fileReader.seek(address); // goes to the end of the file
        fileReader.writeInt(ba.length); // writes the size of the object
        fileReader.write(ba); // writes the object byte array
        AddMovieToPosting(movie, l, address);
    }

    private void AddMovieToPosting(Movie movie, List l, long address) throws Exception {
        String[] genres = movie.get_genres();

        for (String g : genres) {
            l.addItem(g, address, Integer.parseInt(movie.get_movieId()));
        }
    }

    public Movie readMovie(int fileSize, String id, boolean lapide) throws Exception {
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
                position = fileReader.getFilePointer();
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


    public void clear() {
        file.delete();
    }


    public void getAddress(long add) throws IOException{
        fileReader.seek(add);
        //fileReader.readUTF();
        int sizeMovie;
        boolean lapide;
        String movieId;

        try {
            
                position = fileReader.getFilePointer();
                sizeMovie = fileReader.readInt();
                lapide = fileReader.readBoolean();
                if (lapide) {
                    fileReader.readInt();
                    movieId = fileReader.readUTF();
                    System.out.print(readMovie(sizeMovie, movieId, lapide).toString()); // save the movie
            }
        } catch (Exception e) {
            System.err.println("Id " +  "não encontrado");
        }
        //return selectMovie; 
    }


    public void getMoviesInPosting(ArrayList<Element> elements) throws Exception{
        
        for (Element el : elements) {
            fileReader.seek(el.get_address());
        //fileReader.readUTF();
        int sizeMovie;
        boolean lapide;
        String movieId;

        try {
                position = fileReader.getFilePointer();
                sizeMovie = fileReader.readInt();
                lapide = fileReader.readBoolean();
                if (lapide) {
                    fileReader.readInt();
                    movieId = fileReader.readUTF();
                    System.out.print(readMovie(sizeMovie, movieId, lapide).toString()+ "\n"); // save the movie
            } 
        } catch (Exception e) {
            System.err.println("Id " +  "não encontrado");
        }
        }

    }

    /* CRUD */// --------------------

    //Create -> chamar o adiconar da lista invertida
    public void create(List l) throws Exception {
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

        writeMovie(movie, l);// add movie to byte file
        System.out.println("Novo filme:\n" + movie.toString());

        sc.close();
    }

    //update -> se genero alterar, chamar um delete e add dos generos do id 

    //update-> se o endereço mudar chamar um add e delete

    public Movie select(String id) throws IOException {
        Movie movie = new Movie();
        movie = read(id, movie);
        return movie;
    }

    public void update(String id, List l) throws Exception {
        Movie movie = select(id); // get the movie that is being updated
        Scanner sc = new Scanner(System.in);
        if (movie != null) {
            System.out.println("Filme selecionado: ----------------");
            System.out.println(movie); // show movie selected
            System.out.println("--------------------------");
            System.out.println(
                    "Qual informação deseja alterar:\n a)Nome do filme\nb)Gêneros\nc)Duração\nd)Tipo do conteúdo\ne)Data de lançamento");
            String option = sc.nextLine();

            switch (option) { // modify wanted atribute
                case "a":
                case "A":
                    System.out.println("Digite o novo título:");
                    movie.set_title(sc.nextLine());
                    break;
                case "b":
                case "B":
                    l.delete(Integer.parseInt(id), movie.get_genres());
                    System.out.println("Digite o novos gêneros (separe-os com vírgula):");
                    movie.set_genres(sc.nextLine().split(","));
                    break;
                case "c":
                case "C":
                    System.out.println("Digite a nova duração (em minutos):");
                    movie.set_duration(Integer.parseInt(sc.nextLine()));
                    break;
                case "d":
                case "D":
                    System.out.println("Digite o novo tipo de conteúdo:");
                    movie.set_contentType(sc.nextLine());
                    break;
                case "e":
                case "E":
                    System.out.println("Digite a nova data de lançamento (MMM dd, YYYY)");
                    movie.set_dateAdded(sc.nextLine());
                    break;
                default:
                    System.out.println("Arquivo não alterado");
            }
            sc.close();

            byte[] ba = movie.toByteArray();
            fileReader.seek(position);
            int sizeLastMovie = fileReader.readInt();
            if (ba.length <= sizeLastMovie) { // if the size is the same write in the same place
                fileReader.write(ba);
                if(option.equals("B")|| option.equals("b")){
                    for (String g : movie.get_genres()) {
                        l.addItem(g, position, Integer.parseInt(id));
                    }
                }

            } else { // else delete the current file and save the modified one as new
                fileReader.writeBoolean(false);
                writeMovie(movie, l);
            }
            System.out.println();

        }
    }

    //delete -> chamar todos os generos do filme e remover o id

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
    
}
