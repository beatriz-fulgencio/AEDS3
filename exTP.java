import java.io.*;
import java.text.*;
import java.util.*;

public class exTP {

    public static String[] readCSV(String[] strs, int maxRead) throws FileNotFoundException {
        File file = new File("netflix.csv");
        Scanner sc = new Scanner(file);
        int place = 0;
        
        while(sc.hasNext()) {
            
            place++;
        }

        sc.close();

        return strs;
    }

    public static void main(String[] args) throws Exception {

        int n =  3800;

        Scanner sc = new Scanner(new File("netflix.csv"));
        Movie movie = new Movie();

        while(sc.hasNext()) {
            movie.read(sc.nextLine());
            movie.toString();
            // System.out.println(movie.toString());
        }

        long  pos = 0;
        long lastPos = 0;
        int len;

        Movie m1= new Movie("e2ef4e91-fb25-42ab-b485-be8e3b23dedb", "#Alive", "Horror Movies, International Movies, Thrillers", 99, "Movie", );
        Movie m2= new Movie("b01b73b7-81f6-47a7-86d8-acb63080d525", "#AnneFrank - Parallel Stories", "Documentaries, International Movies", 95, "Movie", July 1, 2020);
        Movie m3= new Movie("7f2d4170-bab8-4d75-adc2-197f7124c070", "#cats_the_mewvie", "Documentaries, International Movies", 90, "Movie", February 5, 2020);

        Movie m_temp = new Movie();
        byte[] ba;
        long pos0,pos1,pos2;

        try {

            RandomAccessFile arq = new RandomAccessFile("../dados/jogadores.db", "rw");

            pos0=arq.getFilePointer();
            System.out.println("Registro iniciado na posição: "+pos0);
            ba = m1.toByteArray();
            arq.writeInt(ba.length); //Tamano do registro em bytes
            arq.write(ba);
            
            pos1=arq.getFilePointer();
            System.out.println("Registro iniciado na posição: "+pos1);
            ba = m2.toByteArray();
            arq.writeInt(ba.length); //Tamano do registro em bytes
            arq.write(ba);

            pos2=arq.getFilePointer();
            System.out.println("Registro iniciado na posição: "+pos2);
            ba = m3.toByteArray();
            arq.writeInt(ba.length); //Tamano do registro em bytes
            arq.write(ba);
            
            //Lendo por ponteiro de trás para frente
            arq.seek(pos2);
            len = arq.readInt();
            ba = new byte[len];
            arq.read(ba);
            m_temp.fromByteArray(ba);
            System.out.println(m_temp);

            arq.seek(pos1);
            len = arq.readInt();
            ba = new byte[len];
            arq.read(ba);
            m_temp.fromByteArray(ba);
            System.out.println(m_temp);

            arq.seek(pos0);
            len = arq.readInt();
            ba = new byte[len];
            arq.read(ba);
            m_temp.fromByteArray(ba);
            System.out.println(m_temp);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

