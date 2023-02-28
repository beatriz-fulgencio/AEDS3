import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static String[] read(String[] strs, int maxRead) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = null;
        int place = 0;

        try {
            sc = new Scanner(file);
            while (place < maxRead) {
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
        int m /*3862 */ = 3862;
        int pos = 0;

        RandomAccessFile arq = new RandomAccessFile("movies.db", "rw");
        arq.setLength(0);

        while (m >= 0) {
            int n = 100;
            if (m < 100) {
                n = 62;
            }
            String[] movies = new String[n];
            Movie[] movieObjects = new Movie[n];
            movies = read(movies, n);

            for (int j = 0; j < movies.length; j++) {
                movieObjects[j] = new Movie(pos);
                movieObjects[j].read(movies[j]);
                pos++;
                //System.out.print(movieObjects[j].toString());
            }
            
            // byte[] ba;
            // long lastPos = 0;
            // int len;
            // Movie m_temp = new Movie();
            // try {
    
            //     lastPos = arq.getFilePointer();
            //     arq.skipBytes((int)lastPos);
            //     //arq.skipBytes(4);
            //     for(Movie M: movieObjects){
            //         ba = M.toByteArray();
            //         arq.writeBoolean(false);
            //         //pos = arq.getFilePointer();
            //         arq.writeInt(ba.length);
            //         arq.write(ba);
            //     }
    
            // } catch (Exception e) {
            //     System.out.println(e.getMessage()); // TODO: handle exception
            // }


            m = m-n;
        }

         //arq.seek(0);
                //arq.writeLong(lastPos);
                
                // arq.seek(pos);
                // lastPos = arq.readLong();
                // arq.seek(0);
                // arq.writeLong(lastPos);
    
            byte[] ba;
            long lastPos = 0;
            int len;
            Movie m_temp = new Movie();
    
                arq.seek(0);
                //arq.skipBytes(4);
                
                boolean lapide;
                
                lapide = arq.readBoolean();
                if(!lapide){
                    len = arq.readInt();
                    String movieId = arq.readUTF();
                    String title = arq.readUTF();
                    String genres = arq.readUTF();
                    int duration = arq.readInt();
                    String contentType = arq.readUTF();
                    long mills = arq.readLong();
            
                    // ba = new byte[len];
                    // arq.read(ba);
                    // m_temp.fromByteArray(ba);
                    // System.out.println(m_temp.toString());
                    // System.out.println(m_temp.toString());

                }

        arq.close();

    }

}