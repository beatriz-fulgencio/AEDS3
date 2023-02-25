import java.util.Arrays;
import java.io.*;
import java.text.*;
import java.util.*;

public class teste {
    public static void main(String[] args) throws ParseException {
        // String to byte
        String str = "jAva";
        byte[] byteStr = str.getBytes();
        System.out.println("String to byte: " + Arrays.toString(byteStr));

        // Int to byte
        int inteiro = 901;
        byte byteInt = (byte) inteiro;
        System.out.println("Int to byte: " + byteInt);

        // Array List to byte
        List<String> list = new ArrayList<String>();
        list.add("Array");
        list.add("list");
        list.add("to");
        list.add("byte");
        list.add("array");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream obj;
        try {
            obj = new ObjectOutputStream(output);
            obj.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = output.toByteArray();
        System.out.println("Array list to byte: " + Arrays.toString(bytes));

        // Date to long
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse("1970-01-01");
        long mills = dt.getTime();
        System.out.println("Date to long: " + mills);

        // Long to bytes
        byte byteLong = (byte) mills;
        System.out.println("Long to byte: " + byteLong);
    }
}