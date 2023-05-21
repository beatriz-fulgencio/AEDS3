public class  HuffmanEncodedResult {
    private Node root;
    private String encodedData;

    public HuffmanEncodedResult(String generateEncodedData, Node root) {
        this.encodedData = generateEncodedData;
        this.root=root;
    }

    public Node getRoot() {
        return root;
    }

    public String getEncodedData() {
        return encodedData;
    }
    
}
