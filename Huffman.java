import java.util.*;

public class Huffman {

    // defining the alphabet size
    private static final int alphabetSize = 256;

    // COMPRESSION
    public HuffmanEncodedResult compress(final String data) {

        final int[] freq = buildFrequency(data);

         Node root = buildTree(freq);

         Map<Character, String> lookupTable = buildLookupTable(root);

        return new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);
    }

    private String generateEncodedData(String data, Map<Character, String> lookupTable) {

        StringBuilder builder = new StringBuilder();
        for(final char character : data.toCharArray()){
            builder.append(lookupTable.get(character));
        }

        return builder.toString();
    }

    private static Map<Character, String> buildLookupTable(Node root){

        Map<Character, String> lookupTable = new HashMap<>();

        buildLookupTableImpl(root, "", lookupTable);

        return lookupTable;
    }

    private static void buildLookupTableImpl(Node node, String s, Map<Character, String> lookupTable) {

        if(node.isLeaf()==false){
            buildLookupTableImpl(node.getLeft(), s + '0', lookupTable);
            buildLookupTableImpl(node.getRight(), s + '1', lookupTable);
        }else{
            lookupTable.put(node.getCharacter(), s);
        }
    }

    // builds the huffman tree
    private static Node buildTree(int[] frequency) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        // adding the node to the priority queue
        for(char i=0; i < alphabetSize; i++) {
            if(frequency[i] > 0) {
                priorityQueue.add(new Node(i, frequency[i], null, null));
            }
        }

        // if the queue only has one element
        if(priorityQueue.size()==1){
            priorityQueue.add(new Node('\0', 1, null, null));
        }

        while(priorityQueue.size() > 1) {
            Node left = priorityQueue.poll(); // This method returns the element at the front of the container or the head of the Queue. It returns null when the Queue is empty
            Node right = priorityQueue.poll();

            Node parent = new Node('\0', (left.getFrequency() + right.getFrequency()), left, right);
            priorityQueue.add(parent);

        }
    
        return priorityQueue.poll();
   }

    public static int[] buildFrequency (String data) {
        int[] freq = new int[alphabetSize];

        for(final char character : data.toCharArray()) { // goes through each caracter
            freq[character]++;
        }

        return freq;
    }

    
    // DECOMPRESSION
    public String decompress(final HuffmanEncodedResult result) {
        StringBuilder resultBuilder = new StringBuilder();

        Node current = result.getRoot();

        int i=0;

        while(i<result.getEncodedData().length()){
            
            // while the node is not leaf
            while(current.isLeaf()==false){
                char bit = result.getEncodedData().charAt(i);
                if(bit=='1'){ // if right, then write 1
                    current = current.getRight();
                }else if (bit=='0'){ // if left, then write 0
                    current = current.getLeft();
                }else {
                    throw new IllegalArgumentException("Invalid bit!" + bit);
                }
                i++;
            }

            resultBuilder.append(current.getCharacter());
            current= result.getRoot();
            
        }

        return resultBuilder.toString();
    }

    
    public static void main(String[] args) {
        // entry
        String test = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ligula felis, bibendum vel sem tincidunt, maximus malesuada ligula. In eu elit non lorem fermentum blandit eu sit amet lorem. Nam non consequat diam, eget auctor nisl. Vestibulum id est ac lectus hendrerit laoreet. Donec pretium augue ut ligula interdum lobortis. Ut iaculis nisi a finibus bibendum. Suspendisse accumsan orci eu diam euismod molestie. Morbi porttitor in orci sed auctor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Curabitur pellentesque suscipit condimentum. Integer maximus ipsum lectus, et sodales arcu sagittis eu. Proin porta scelerisque magna, sit amet laoreet mi fringilla eget. Praesent eget arcu bibendum, facilisis risus at, ultrices lectus. Mauris ligula dolor, posuere id scelerisque a, placerat ut sem. Vestibulum molestie purus risus, non consectetur leo volutpat eget. Proin mauris risus, viverra sit amet justo sit amet, vehicula eleifend elit. Nunc semper euismod diam a interdum. Quisque lorem dolor, pellentesque egestas sagittis nec, ultrices at mi. Aliquam eget posuere odio. Fusce mollis commodo est sit amet bibendum. Fusce vel leo sed nisi rhoncus lobortis eget rhoncus odio. Nulla facilisi. Integer sit amet quam id orci semper interdum pulvinar quis arcu. Praesent id ante lorem. Donec.";
        Huffman encoder = new Huffman();
        HuffmanEncodedResult result  = encoder.compress(test);
        System.out.println(result.getEncodedData());
        System.out.println(encoder.decompress(result));
    }
}



