public class Node {

    private int control;
    private Node[] children;
    private Key[] parent;
    private int maxElements;
    private int maxChildren;
    private Key root;

    public Node() {
        this.maxChildren = 8;
        this.maxElements = maxChildren - 1;
        this.parent = new Key[maxElements];
        this.children = new Node[maxChildren];
        this.control = 0;
        this.root = null;
    }

    public Node(int maxChildren) {
        this.maxChildren = maxChildren;
        this.maxElements = maxChildren - 1;
        this.parent = new Key[maxElements];
        this.children = new Node[maxChildren];
        this.control = 0;
        this.root = null;
    }

    public Node(int control, Node[] children, Key[] parent, int maxElements, int maxChildren, Key root) {
        this.maxChildren = maxChildren;
        this.maxElements = maxElements;
        this.parent = parent;
        this.children = children;
        this.control = control;
        this.root = root;
    }

    // searches the element
    public Key search(Key key, int id) {
        int i = 1;
        while ((i <= maxElements) && (id > key.get_id())) {
            i++;
        }
        if ((i <= maxElements) && (id == key.get_id())) {
            return key;
        }
        if (isLeaf()) {
            return null;
        } else {
            // return (search(key.getFilho().get(i - 1), id));
        }
    }

    // initializes an empty b tree
    public void bTree() {
        root = new Key();
    }

    // checks if the node is a leaf
    public boolean isLeaf() {
        boolean isLeaf;
        if (this.control == 0) {
            isLeaf = true;
        } else
            isLeaf = false;
        return isLeaf;
    }

    // checks if the node is the root
    public boolean isRoot() {
        boolean isRoot;
        if (this.parent == null) {
            isRoot = true;
        } else {
            isRoot = false;
        }
        return isRoot;
    }

    // checks if the node is full
    public boolean isFull() {
        boolean isFull;
        if (this.control == maxElements) {
            isFull = true;
        } else
            isFull = false;
        return isFull;
    }

    // detecting the type of insertion
    public void insertion(Key key) {
        if (search(root, key.get_id()) == null) { // only adds new ids, not repeated ones
            if (isLeaf()) {
                if (!isFull()) { // if it is possible to insert in that node
                    addToNode(key);
                } else { // if the node has to be splitted
                    Key[] levelUp = new Key[8];
                    Key[] splitted1 = new Key[8];
                    Key[] splitted2 = new Key[8];

                    levelUp[0] = parent[4];

                    for (int i = 0; i < 4; i++) {
                        splitted1[i] = parent[i];
                    }

                    for (int i = 5; i < 8; i++) {
                        splitted2[i - 5] = parent[i];
                    }
                }
            }
        }
    }

    // add element
    public void addToNode(Key key) {
        if (control != 0) {
            parent[control] = key;

            if (parent[control].get_id() < parent[control - 1].get_id()) {
                quicksort(parent);
            }

            control++;
        } else {
            parent[control++] = key;
        }
        key.set_position(control);
    }

    public void removal(Key key) {
        if (search(this.root, key.get_id()) == null) { // verifies if the element exists

            // if the element is on a leaf
            if (isLeaf()) {
                removeFromNode(key);
            }
            if (isRoot()) {
                // gets the smallest element from the right tree

            }
        }
    }

    public void removeFromNode(Key key) {
        int currentPosition = key.get_position();
        if (currentPosition == control) { // if the element removed is on the last position of the node
            parent[control] = null;
        } else {
            parent[currentPosition] = null; // element removed
            while (currentPosition < control) {
                parent[currentPosition] = parent[currentPosition + 1]; // reorganizing the node
                currentPosition++;
            }
        }

        // remove the root
        control--;
    }

    // quicksort -> orders in primary memory
    public void quicksort(Key[] parent) {
        quicksort(parent, 0, control);
    }

    private void quicksort(Key[] parent, int left, int right) {
        int i = left, j = right;
        int pivo = parent[(left + right) / 2].get_id();

        while (i <= j) {
            while (parent[i].get_id() < pivo)
                i++;
            while (parent[j].get_id() > pivo)
                j--;

            if (i <= j) {
                Key temp = parent[i];
                parent[i] = parent[j];
                parent[j] = temp;
                temp = null;

                i++;
                j--;
            }
        }

        if (left < j)
            quicksort(parent, left, j);
        if (i < right)
            quicksort(parent, i, right);
    }
}
