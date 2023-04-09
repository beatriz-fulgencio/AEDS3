public class Node {

    private int n; //Atributo que guarda a quantidade de chaves no nó
    private int[] chave; //vetor das chaves
    private int[] filho;//vetor dos filhos
    private boolean folha;//Atributo que indica se a nó eh folha ou nao

    public Node(int n) {
        this.chave = null;
        this.filho = null;
        this.n = 0;
    }

    public int[] getChave() {
        return chave;
    }

    public void setChave(int[] chave) {
        this.chave = chave;
    }

    public int[] getFilho() {
        return filho;
    }

    public void setFilho(int[] filho) {
        this.filho = filho;
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}