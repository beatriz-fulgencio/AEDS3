import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BTree {

    private File file;
    private RandomAccessFile fileReader;
    
    BTree(String file) throws FileNotFoundException {
        this.file = new File(file); // creates the "file" file
        fileReader = new RandomAccessFile(file, "rw"); // opens the file in read and write mode
    }

    private int elements; // min number of elements in each node
    private int nodeControl = 0; // number of nodes
    private boolean found = false;

    // creates the node
    public class Node {
        Key key[] = new Key[2 * elements - 1]; // creates the node
        Node children[] = new Node[2 * elements]; // creates the node's children
        boolean isLeaf = true;
        int currentElements; // current elements on the node

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

    // creates a new tree
    public BTree(int t) {
        elements = t;
        root = new Node();
        root.currentElements = 0;
        root.isLeaf = true;
    }

    private Node root;

    // searches the id in the tree
    private Node search(Node node, int key) { // receives a node and a key
        Node search = null;
        int i = 0;

        if (node != null) { // if the node exists
            for (i = 0; i < node.currentElements; i++) { // id doesnt exist in this node
                if (key < node.key[i].id) {
                    break;
                }
                if (key == node.key[i].id) { // id found
                    search = node;
                }
            }
            if (node.isLeaf) { // id doesnt exist in this tree
                search = null;
            } else { // continues the search
                search = search(node.children[i], key);
            }
        } else { // if the node doesnt exist
            search = null;
        }
        return search;
    }

    // splitting the node
    private void split(Node node1, int pos, Node node2) {
        Node newNode = new Node();
        newNode.isLeaf = node2.isLeaf;
        newNode.currentElements = elements - 1;
        for (int j = 0; j < elements - 1; j++) {
            newNode.key[j] = node2.key[j + elements];
        }
        if (!node2.isLeaf) {
            for (int j = 0; j < elements; j++) {
                newNode.children[j] = node2.children[j + elements];
            }
        }
        node2.currentElements = elements - 1;
        for (int j = node1.currentElements; j >= pos + 1; j--) {
            node1.children[j + 1] = node1.children[j];
        }
        node1.children[pos + 1] = newNode;

        for (int j = node1.currentElements - 1; j >= pos; j--) {
            node1.key[j + 1] = node1.key[j];
        }
        node1.key[pos] = node2.key[elements - 1];
        node1.currentElements = node1.currentElements + 1;
    }

    // insertion
    public void insertion(final Key key) {
        Node currentRoot = root; // gets the current root
        if (currentRoot.currentElements == 2 * elements - 1) { // root is full
            Node newNode = new Node(); // creates new node
            root = newNode; // this new node is the root
            newNode.isLeaf = false;
            newNode.currentElements = 0;
            newNode.children[0] = currentRoot;
            split(newNode, 0, currentRoot);
            insertKey(newNode, key);
        } else { // the key can be inserted on the root
            insertKey(currentRoot, key);
        }
    }

    // inserts on the node
    final private void insertKey(Node node, Key key) {
        if (node.isLeaf) {
            int i = 0;
            for (i = node.currentElements - 1; i >= 0 && key.id < node.key[i].id; i--) { // if the id is smaller
                node.key[i + 1] = node.key[i];
            }
            node.key[i + 1] = key; // inserts
            node.currentElements = node.currentElements++; // elements++
        } else {
            int i = 0;
            for (i = node.currentElements - 1; i >= 0 && key.id < node.key[i].id; i--) {
                // acha a posicao do novo element que sera inserido
            }
            i++; 
            Node temp = node.children[i]; 
            if (temp.currentElements == 2 * elements - 1) { // if the node is full
                split(node, i, temp);
                if (key.id > node.key[i].id) {
                    i++;
                }
            }
            insertKey(node.children[i], key); // tries to insert again
        }
    }

    // prints the ids
    public void show() throws IOException {
        show(root);
    }

    private void show(Node node) { // starting by the root
        for (int i = 0; i < node.currentElements; i++) {  // goes through all the elements
            System.out.print(node.key[i].id + " ");
        }
        System.out.print("\n");
        if (!node.isLeaf) { // if the node is not a leaf yet
            for (int i = 0; i < node.currentElements + 1; i++) {
                show(node.children[i]); // continues printing
            }
        }
    }

    // writing on the index file
    public void writeIndex() throws IOException {
        writeIndex(root);
    }

    public void writeIndex(Node node) throws IOException { 
        RandomAccessFile file = new RandomAccessFile("file.db", "rw"); // what file?
        int pointer = 0;

        if (pointer == 0) {
            file.writeInt(4);
            pointer = 4;
        }

        int cont = 0;
        if (node == root) {
            file.seek(pointer);
            file.writeInt(node.currentElements); // number of elements in the node
            
            for (int i=0; i<node.currentElements; i++) {
                file.writeInt(pointer);
                file.writeInt(node.key[i].id);
                file.writeLong(node.key[i].address);
            }
            pointer = writeIndex(node.children[cont++], pointer);
            file.writeInt(pointer);
        }

        file.close();
    }

    public int writeIndex(Node node, int pointer) throws IOException {
        RandomAccessFile file = new RandomAccessFile("file.db", "rw"); // what file?
        
        int pointerLeaf = -1;
        int cont = 0;

        if (node != null) { // if the node exists
            nodeControl++;
            if (!node.isLeaf) { // if the node is not a leaf
                file.seek(pointer);
                file.writeInt(node.currentElements); 
                
                for (int i=0; i<node.currentElements; i++) {
                    file.writeInt(pointer);
                    file.writeInt(node.key[i].id);
                    file.writeLong(node.key[i].address);
                }
                file.writeInt(pointer);
                pointer = writeIndex(node.children[cont++], pointer);
            } 
            else { // is the node is a leaf
                file.seek(pointer); 
                file.writeInt(node.currentElements); 
                for (int i=0; i<node.currentElements; i++) {
                    file.writeInt(pointer);
                    file.writeInt(node.key[i].id);
                    file.writeLong(node.key[i].address);
                }
                file.writeInt(pointerLeaf);
            }
        }
        file.close();
        return pointer;
    }


    // read file
    public void readFile() throws IOException {
        RandomAccessFile file = new RandomAccessFile("file.db", "rw");
        int pos = 4;
        while(pos <= file.length()) {
            file.seek(pos);
            int counter = file.readInt();
            System.out.println();
            for (int i = 0; i < counter; i++) {
                System.out.println("pont: " + file.readInt());
                System.out.println("id: " + file.readInt());
                System.out.println("end: " + file.readInt());
            }
            System.out.println("pont: " + file.readInt());
        }
        file.close();
    }

    public int searchFile(int id) throws IOException {
        found = false;
        int address = searchFile(0, id);
        return address;
    }

    private int searchFile(int pos, int id) throws IOException {
        RandomAccessFile file = new RandomAccessFile("file.db", "rw");
        int pointer1 = 0;
        int pointer2;
        int element;
        int address = -1;

        if (pos == 0) {
            file.seek(pos);
            pos = file.readInt();
        }

        file.seek(pos);
        int counter = file.readInt();
        for (int i = 0; i < counter; i++) {
            pointer1 = file.readInt();
            element = file.readInt();
            address = file.readInt();
            if (element == id) {
                found = true;
                return address;
            } else if (id < element && pointer1 != -1) {
                address = searchFile(pointer1, id);
            } else {
                address = 0;
            }
            if (found) {
                break;
            }
        }

        if (address == 0 && pointer1 != -1) {
            pointer2 = file.readInt();
            address = searchFile(pointer2, id);
        }
        if (found == false) {
            address = -1;
        }
        file.close();
        return address;
    }

    public void updateAddress(int id, int address) throws IOException {
        RandomAccessFile file = new RandomAccessFile("file.db", "rw");
        int pos = 4;
        int pointer;
        do {
            file.seek(pos);
            int counter = file.readInt();
            for (int i = 0; i < counter; i++) {
                pointer = file.readInt();
                int element = file.readInt();
                if (element == id) {
                    file.writeInt(address);
                    break;
                } else {
                    pointer = file.readInt();
                }
            }
            pointer = file.readInt();
            pos += 92;
        } while (pos <= file.length());
        file.close();
    }
}
