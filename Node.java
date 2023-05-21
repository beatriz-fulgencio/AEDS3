public class Node implements Comparable<Node>{
    private char character;
    private int frequency;

    private Node left;
    private Node right;

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
    
    public Node(char character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(Node o) {
        int frequencyComp = Integer.compare(this.frequency, o.frequency);

        if(frequencyComp != 0) {
            return frequencyComp;
        }

        return Integer.compare(this.character, o.character);
    }
}

