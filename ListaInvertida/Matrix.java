public class Matrix {
    int[] durationArray;
    String[] genresArray;
    private String[][] matrix = new String[genresArray.length][durationArray.length];

    // constructors
    public Matrix() {
        this.durationArray = null;
        this.genresArray = null;
        this.matrix = null;
    }

    public Matrix(int[] durationArray, String[] genresArray, String[][] matrix) {
        this.durationArray = durationArray;
        this.genresArray = genresArray;
        this.matrix = matrix;
    }

    // gets and sets
    public int[] get_durationArray () {
        return durationArray;
    }
    public void set_durationArray (int[] durationArray) {
        this.durationArray = durationArray;
    }

    public String[] get_genresArray () {
        return genresArray;
    }
    public void set_genresArray (String[] genresArray) {
        this.genresArray = genresArray;
    }

    public String[][] get_matrix () {
        return matrix;
    }
    public void set_matrix (String[][] matrix) {
        this.matrix = matrix;
    }
}
