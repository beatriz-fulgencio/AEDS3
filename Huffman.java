import java.util.PriorityQueue;

public class Huffman {

    private static int alphabetSize;

    public HuffmanEncodedResult compress(final String data) {

        int[] freq = buildFrequency(data);

        Node root = buildTree(freq);

        return null;
    }

    private static Node buildTree(int[] frequency) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for(char i=0; i < alphabetSize; i++) {
            if(frequency[i] > 0) {
                priorityQueue.add(new Node(i, frequency[i], null, null));
            }
        }

        while(priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            Node parent = new Node('\0', (left.frequency + right.frequency), left, right);
            priorityQueue.add(parent);

            return priorityQueue.poll();
        }
    
        return null;
    }

    public static int[] buildFrequency (String data) {
        int[] freq = new int[alphabetSize];

        for(final char character : data.toCharArray()) { // goes through each caracter
            freq[character]++;
        }

        return freq;
    }

    public String decompress(final HuffmanEncodedResult result) {
        return null;
    }

    static class Node implements Comparable<Node>{
        private char character;
        private int frequency;

        private Node left;
        private Node right;

        private Node(char character, int frequency, Node left, Node right) {
            this.character = character;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        @Override
        public int compareTo(Huffman.Node o) {
            
            int frequencyComp = Integer.compare(this.frequency, o.frequency);

            if(frequencyComp != 0) {
                return frequencyComp;
            }

            return Integer.compare(this.character, o.character);
        }
    }

    static class HuffmanEncodedResult {

    }

    public static void main(String[] args) {
        String test = "abbcdeeeefg";
        int[] frequency = buildFrequency(test);
        Node n = buildTree(frequency);

        System.out.println(n);
    }
}
