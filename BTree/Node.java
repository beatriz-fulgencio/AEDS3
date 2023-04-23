public class Node {

    int currentElements; // Atributo que guarda a quantidade de chaves no nó
    Key[] key; // vetor das chaves
    private Node[] filho;// vetor dos filhos
    boolean isLeaf;// Atributo que indica se a nó eh isLeaf ou nao
    public Node[] children;

    public Node() {
        this.key = null;
        this.filho = null;
        this.currentElements = 0;
    }

    public Key[] getKey() {
        return key;
    }

    public void setKey(Key[] key) {
        this.key = key;
    }

    public Node[] getFilho() {
        return filho;
    }

    public void setFilho(Node[] filho) {
        this.filho = filho;
    }

    public boolean getisLeaf() {
        return isLeaf;
    }

    public void setisLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getN() {
        return currentElements;
    }

    public void setN(int currentElements) {
        this.currentElements = currentElements;
    }

    public int searchKey(int k) { // searches a key on a node
        int searchKey = -1;
        for (int i = 0; i < this.currentElements; i++) { // if it still has keys in that node
            if (this.key[i].id == k) { // if found
                searchKey = i; // returns the position of the idin the node
            } else {
                searchKey = -1;
            }
        }
        return searchKey;
    }
}

