import java.util.ArrayList;
import java.util.List;

public class KMP {
    
    static int operations = 0;
    void findKMP(String str, String pattern){
        // this.str= str;
        // this.pattern = pattern;

        long startTime = System.nanoTime();
        List<Integer> matchesPos = searchPattern(str, pattern);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        for (Integer i : matchesPos) {
            System.out.println("Padrão encontrado na posição "+ i +" do texto");
        }
        System.out.println("-------------------------");
        System.out.println("Tempo total: " + totalTime + " nanosegundos");
        System.out.println("Operações totais: "+ operations);
        System.out.println("-------------------------");
    }
   
    

    public List<Integer> searchPattern(String text, String pattern){
        int t=0; //position of the current character in text 
        int p =0; //the position of the  current character in pattern

        int tLen = text.length();
        int pLen = pattern.length();

        List<Integer> matches = new ArrayList<>();
        int[] prefixLen = calcPrefixLen(pattern); //gets the prefix array

        while(t<tLen){
            if(pattern.charAt(p) == text.charAt(t)){
                operations++;
                p++;
                t++;

                if(p == pLen){//occurence found
                    matches.add(t-p);
                    p = prefixLen[p]; //resets
                }
            }else{ //mismatch
                operations++;
                p = prefixLen[p]; //reasigns p to that value in the prefix array
                if( p<0 ){
                    t++;
                    p++;
                }
            }
        }

        return matches; //returns the postions in wich the pattern is found
    }


    private int[] calcPrefixLen(String pattern) { //constructing the prefix array (failure function)
        int[] helperArray = new int[pattern.length()+1]; //create a array of 1 more than the pattern lenght to store -1 in pos 0

        helperArray[0]=-1;
        helperArray[1]=0;

        int prefixLen = 0;

        for(int i = 1; i<pattern.length();){
            if(pattern.charAt(prefixLen)==pattern.charAt(i)){
                operations++;
                prefixLen++; //increments the lenght of the prefix when the characters match
                i++;
                helperArray[i]=prefixLen;
            }else{ //if they don't match
                if(prefixLen>0){ 
                    operations++;
                    prefixLen = helperArray[prefixLen]; //set to the value of the array at that index
                }else{
                    operations++;
                    helperArray[i]=0; //prefixLen reacj=hed 0, so save that into the array and move forward
                    i++;
                }
            }
        }
 
        return helperArray;
    }
}
