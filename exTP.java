import java.io.*;
import java.text.*;
import java.util.*;

public class ExTP {

    public static String[] read(String[] strs, int maxRead) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;
        int place = 0;

        try {
            sc = new Scanner(file);
            while (place<maxRead) {
                strs[place] = sc.nextLine();
                place++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sc.close();

        return strs;
    }

    public static void main(String[] args) throws Exception {

        int n =  10/*3900*/;
        String[] movies = new String[n];
        Movie[] movieObjects = new Movie[n];
        movies = read(movies, n);

        int countMovies = 0;
        //for (int i = 0; i < n; i++) {
            for (int j = 0; j < movies.length; j++) {
                movieObjects[countMovies] = new Movie();
                movieObjects[countMovies].read(movies[j]);
                movieObjects[countMovies].set_pos(j);
                countMovies++;
                //j = movies.length;
            }
        //}


        byte[] ba;
        long  pos = 0;
        long lastPos = 0;
        int len;
        Movie m_temp = new Movie();
        try {

            RandomAccessFile arq = new RandomAccessFile("movies.db", "rw");
            //arq.skipBytes(4);
            for(Movie m: movieObjects){
                //lastPos = m.get_pos();
                ba = m.toByteArray();
                arq.writeBoolean(false);
                //pos = arq.getFilePointer();
                arq.writeInt(ba.length);
                arq.write(ba);
            }
            arq.seek(0);
            //arq.writeLong(lastPos);
            
            // arq.seek(pos);
            // lastPos = arq.readLong();
            // arq.seek(0);
            // arq.writeLong(lastPos);


            arq.seek(0);
            //arq.skipBytes(4);
            
            boolean lapide;
            
            lapide = arq.readBoolean();
            if(!lapide){
                len = arq.readInt();
                ba = new byte[len];
                arq.read(ba);
                m_temp.fromByteArray(ba);
                System.out.println(m_temp);
            }
                arq.close();
            

        } catch (Exception e) {
            System.out.println(e.getMessage()); // TODO: handle exception
        }

    }

}

