public class Node implements Comparable<Node>{  // comparable allow us to compare objects

    // each node has two elements: the character and its frequency
    private char character;
    private int frequency;

    private Node left;
    private Node right;

    // getters
    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public int getFrequency(){
        return frequency;

    }

    public char getCharacter(){
        return character;
    }
    
    // constructors
    public Node(char character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // checks if the node is a leaf
    boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    // compares two node by the frequency of the element
    @Override
    public int compareTo(Node o) {
        int frequencyComp = Integer.compare(this.frequency, o.frequency);

        if(frequencyComp != 0) {
            return frequencyComp;
        }

        return Integer.compare(this.character, o.character);
    }
}

