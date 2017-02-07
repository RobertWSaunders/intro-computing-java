/**
 * Created by robertsaunders on 2017-02-02.
 */
public class Matrix {

    //Class attributes
    private int m;
    private int n;
    private int[][] matrix;

    //Constructors
    Matrix() {

    }

    Matrix(int rows, int columns) {
        this.m = rows;
        this.n = columns;
        this.matrix = new int[rows][columns];
    }

    //Getters
    public int getM() {
        return m;
    }
    public int getN() {
        return n;
    }
    //where i is the row and j is column
    public double get(int i, int j) {
        return matrix[i][j];
    }

    //Operations

    public Matrix add(Matrix m) {
        return new Matrix(5,6);
    }


    public Matrix subtract(Matrix m) {

    }

    public Matrix multiply(Matrix m) {

    }

    public Matrix multiply(double x) {

    }

    public Matrix divide(Matrix m) {

    }

    public double determinant() {

    }

    public Matrix inverse() {

    }

    public boolean isSquare() {

    }

    public Matrix transpose() {

    }

    //Other

    public String toString(Matrix m) {

    }

    public void print(String fileName) {

    }

    public static Matrix identity(int size) {

    }


    //Used to test
    public static void main(String[] args) {
        Matrix m = new Matrix(5,6);
        System.out.printf("%d",m.getN());
    }

}
