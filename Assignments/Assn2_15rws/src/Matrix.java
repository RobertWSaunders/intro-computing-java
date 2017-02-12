/**
 * The purpose of this assignment is to practice designing classes, using 2D arrays and File I/O.
 * The class provides a matrix calculator that can be used create matrices and perform mathematical operations on them.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

//used for file i/o, reads characters from file.
import java.io.FileReader;
//used for file i/o, buffers file reader characters for efficiency.
import java.io.BufferedReader;
//used for file i/o, file not found exception.
import java.io.FileNotFoundException;
//used for file i/o, catches input exceptions.
import java.io.IOException;

public class Matrix {

    //class attributes
    //value of m, represents the number of rows in matrix
    private int m;
    //value of n, represents the number of columns in matrix
    private int n;
    //two dimensional array that will represent the matrix
    private double[][] matrix;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor that prompts the user with questions to create a matrix.
     */
    Matrix() {

    }

    /**
     * Constructor that will create a matrix given values for rows and columns.
     * @param rows Number of rows in the matrix.
     * @param columns Number of columns in the matrix.
     */
    Matrix(int rows, int columns) {
        this.m = rows;
        this.n = columns;
        this.matrix = new double[rows][columns];
    }

    /**
     * Constructor that will create a matrix given a comma separated value file.
     * The file must specify the number of rows and columns on the first line.
     * Every line thereafter should represent a row in the matrix.
     * This constructor can support m x n sizes of a matrix.
     * @param filename The name of the comma separated value file.
     */
    Matrix(String filename) {
        //define the buffered reader instance
        BufferedReader reader = null;
        //will hold string read from the csv
        String line = "";
        //the csv delimiter
        String delimiter = ",";
        try {
            //set the reader instance, pass a file reader instance to read characters
            reader = new BufferedReader(new FileReader(filename));
            //counter used to see what line is being read from file and to keep track of the row in the matrix
            int lineCounter = -1;
            //if the line read is not null keep going through the file
            while ((line = reader.readLine()) != null) {
                //store the read values into an array, items are read as string objects
                String[] values = line.split(delimiter);
                //see if this is the first line in the file
                if (lineCounter == -1) {
                    //set the value of m
                    this.m = Integer.parseInt(values[0]);
                    //set the value of n
                    this.n = Integer.parseInt(values[1]);
                    //create the matrix for the instance
                    this.matrix = new double[getM()][getN()];
                }
                else {
                    //if this is not the first cell then the line must represent a row in the matrix
                    //iterate through the columns in the current row
                    for (int column = 0; column < getN(); column++) {
                        //store the current column value into the current row in the matrix
                        this.matrix[lineCounter][column] = Double.parseDouble(values[column]);
                    }
                }
                //increment to the line counter to proceed to the next row
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            System.out.print("File could not be found, cannot create matrix.");
        } catch (IOException e) {
            System.out.print("Items read from the file are not of type string, please use a different file.");
        } finally {
            //if the reader is not null then close it
            if (reader != null) {
                try {
                    //close the reader
                    reader.close();
                } catch (IOException e) {
                    //handle closing exception, just print the stack
                    e.printStackTrace();
                }
            }
        }
    }

    //////////////
    /* GETTERS */
    ////////////

    private int getM() {
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
        return null;

    }

    public Matrix multiply(Matrix m) {
        return null;
    }

    public Matrix multiply(double x) {
        return null;
    }

    public Matrix divide(Matrix m) {
        return null;
    }

    public double determinant() {
        return 1.0;
    }

    public Matrix inverse() {
        return null;
    }

    public boolean isSquare() {
        return false;
    }

    public Matrix transpose() {
        return null;
    }

    //Other

    public String toString(Matrix m) {
        return null;
    }

    public void print(String fileName) {

    }

    public static Matrix identity(int size) {
        return null;
    }


    //Used to test
    public static void main(String[] args) {
        Matrix m = new Matrix(5,6);
        System.out.printf("%d",m.getN());
        System.out.printf("%d",m.getM());


        Matrix fromCSV = new Matrix("./matrix.csv");

    }

}
