import java.util.Scanner;

public class BoyerMoore {

    static int NUM_CHARS = 256;
    private static Integer quant = 0;
    private static Integer cont = 0;

    public static void patternSearch(char text[], char pattern[]) {
        int j;
        int patternLength = pattern.length;
        int textLength = text.length;

        int badChar[] = new int[NUM_CHARS];
        int[] badPosition = new int[patternLength + 1];
        int[] aux = new int[patternLength + 1];

        // Bad Character Heuristic
        badCharHeuristic(pattern, patternLength, badChar);
        int position = 0;

        for (int i = 0; i < patternLength + 1; i++) {
            aux[i] = 0;
        }

        // Good Sufix Heuristic
        // asks the three questions

        isPreceededByADifferentChar(aux, badPosition, pattern, patternLength);
        isPrefix(aux, badPosition, pattern, patternLength);

        while (position <= (textLength - patternLength)) {
            j = patternLength - 1;

            // identifies the bad character
            while (j >= 0 && pattern[j] == text[position + j]) {
                j--;
            }

            // if the pattern was found
            if (j < 0) {
                position += (position + patternLength < textLength) ? patternLength - badChar[text[position + patternLength]] : 1;
                quant++;
            } else { // bad character found
                int pos = text[position + j];
                int val1 = 1;

                if (pos <= 256) {
                    // uses bad char heuristic
                    val1 = max(1, j - badChar[text[position + j]]);
                }

                int val2 = aux[j + 1]; // uses good prefix heuristic

                // checks which one is used
                if (val1 > val2) {
                    position += val1;
                } else {
                    position += val2;
                }
            }
        }
    }

    // compares the number of moves
    static int max(int a, int b) { 
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    static void badCharHeuristic(char[] text, int size, int badChar[]) {
        
        for (int i = 0; i < NUM_CHARS; i++)
            badChar[i] = -1;

        for (int i = 0; i < size; i++) {
            badChar[(int) text[i]] = i;
        }
    }

    public static void isPreceededByADifferentChar(int[] aux, int[] badPosition, char[] pattern, int patternLength) {
        int i = patternLength;
        int j = patternLength + 1; 
        badPosition[i] = j;

        // goes through the pattern
        while (i > 0) {
            // while the positions are !=
            while (j <= patternLength && pattern[i - 1] != pattern[j - 1]) {
                if (aux[j] == 0) {
                    cont++;
                    aux[j] = j - i;
                }
                j = badPosition[j];
            }
            i--;
            j--;
            badPosition[i] = j;
        }
    }

    public static void isPrefix(int[] aux, int[] badPosition, char[] pattern, int patternLength) {
        int i;
        int position = badPosition[0];

        // goes through the pattern
        for (i = 0; i <= patternLength; i++) {
            if (aux[i] == 0) {
                cont++;
                aux[i] = position;
            }
            if (i == position) {
                cont++;
                position = badPosition[position];
            }
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o texto: ");
        String str = sc.next();
        char[] text = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            text[i] = str.charAt(i);
        }

        System.out.print("Digite o padrão: ");
        String str2 = sc.next();
        char[] pattern = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            pattern[i] = str.charAt(i);
        }

        patternSearch(text, pattern);

        System.out.println("The pattern was found " + quant + " time(s)");
        System.out.println(cont + " comparisons");
    }
}