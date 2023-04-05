public class Node {

    private int control = 0;
    private Node[] children;
    private Key[] parent;
    private int maxElements = 8;
    private int maxChildren;

    public Node (int maxChildren) {
        this.maxChildren = maxChildren;
        this.maxElements = maxChildren -1;
        this.parent = new Key[maxElements];
        this.children = new Node[maxChildren];
    }

    // checks if the node is a leaf
    public boolean isLeaf() {
        boolean isLeaf;
        if (this.control==0) {
            isLeaf = true;
        }
        else isLeaf = false;
        return isLeaf;
    }

    // checks if the node is full
    public boolean isFull() {
        boolean isFull;
        if (this.control==maxElements) {
            isFull = true;
        }
        else isFull = false;
        return isFull;
    }

    // add element
    public void addToNode(Key key) {
        if(control != 0) {
            parent[control] = key;

            if(parent[control].get_id() < parent[control-1].get_id()) {
                quicksort(parent);
            }

            control++;
        } else {
            parent[control++] = key;
        }
    }

    // quicksort
    public void quicksort(Key[] parent) {
        quicksort(parent, 0, control);
    }

    private void quicksort(Key[] parent, int left, int right) {
        int i = left, j = right;
        int pivo = parent[(left + right)/2].get_id();

        while(i <= j) {
            while(parent[i].get_id() < pivo) i++;
            while(parent[j].get_id() > pivo) j--;

            if(i <= j) {
                Key temp = parent[i];
                parent[i] = parent[j];
                parent[j] = temp;
                temp = null;

                i++;
                j--;
            }
        }

        if(left < j) quicksort(parent, left, j);
        if(i < right) quicksort(parent, i, right);
    }
}
