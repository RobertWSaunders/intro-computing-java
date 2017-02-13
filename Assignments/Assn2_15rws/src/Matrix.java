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
//used for file i/o, will write to a file.
import java.io.FileWriter;
//used for file i/o, file not found exception.
import java.io.FileNotFoundException;
//used for file i/o, catches input exceptions.
import java.io.IOException;
//used for user input
import java.util.Scanner;
//used to catch incorrect user input exception
import java.util.InputMismatchException;

public class Matrix {

    //class attributes
    //value of m, represents the number of rows in matrix
    private int m;
    //value of n, represents the number of columns in matrix
    private int n;
    //two dimensional array that will represent the matrix
    private double[][] matrix;
    //define a scanner class attribute that can be shared between methods
    private Scanner scanner = new Scanner(System.in);

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor that prompts the user with questions through the console to create a matrix.
     */
    Matrix() {
        //set the value for m, by asking the user for it through the console
        this.m = askUserNumber("How many rows would you like in the matrix?");
        //set the value for n, by asking the user for it through the console
        this.n = askUserNumber("How many columns would you like in the matrix?");
        //create a matrix of the desired size the user chose
        this.matrix = new double[getM()][getN()];
        //ask the user to fill the values for the elements
        askUserForElements();
    }

    /**
     * Constructor that will create a matrix given values for rows and columns.
     * @param rows Number of rows in the matrix.
     * @param columns Number of columns in the matrix.
     */
    Matrix(int rows, int columns) {
        //set values of class attributes
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
                    System.out.print("Error closing reader.");
                }
            }
        }
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the value for m class attribute, represents rows in the matrix.
     * @return Integer value for m class attribute.
     */
    public int getM() {
        return m;
    }

    /**
     * Gets the value for n class attribute, represents columns in the matrix.
     * @return Integer value for n class attribute.
     */
    public int getN() {
        return n;
    }

    /**
     * Gets the value in the matrix at the i and j entry, where i is row and j is column.
     * @param i Represents the row in the matrix.
     * @param j Represents the column in the matrix.
     * @return Decimal value in the matrix at the i and j entry.
     */
    public double get(int i, int j) {
        return matrix[i][j];
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the value in the matrix at the i and j entry, where i is row and j is column.
     * @param i Represents the row in the matrix.
     * @param j Represents the column in the matrix.
     * @param value The value to be set in the matrix.
     */
    public void set(int i, int j, double value) {
        this.matrix[i][j] = value;
    }

    /////////////////
    /* OPERATIONS */
    ///////////////

    /**
     * Adds a matrix to the current instance, returns the resulting matrix.
     * @param m The matrix to be added to the current instance.
     * @return The resultant matrix after addition, if matrices are not same size returns null.
     */
    public Matrix add(Matrix m) {
        //first check that the matrices are the same size, they must for addition
        if (isSameDimensions(m)) {
            //create the resulting matrix, would be the same size as current matrix
            Matrix resultMatrix = new Matrix(getM(), getN());
            //iterate through the row of the matrix
            for (int row = 0; row < getM(); row++) {
                //iterate through each column of the matrix
                for (int column = 0; column < getN(); column++) {
                    //input the added value of both matrices into the result matrix
                    resultMatrix.matrix[row][column] = m.get(row,column) + get(row,column);
                }
            }
            //return the result matrix
            return resultMatrix;
        }
        System.out.print("The matrices are not the same size, therefore cannot be added.");
        //return null otherwise, the two matrices cannot be added together
        return null;
    }

    /**
     * Subtracts a matrix to the current instance, returns the resulting matrix.
     * @param m The matrix to be subtracted to the current instance.
     * @return The subtraction matrix after addition, if matrices are not same size returns null.
     */
    public Matrix subtract(Matrix m) {
        //first check that the matrices are the same size, they must for subtraction
        if (isSameDimensions(m)) {
            //create the resulting matrix, would be the same size as current matrix
            Matrix resultMatrix = new Matrix(getM(), getN());
            //iterate through the row of the matrix
            for (int row = 0; row < getM(); row++) {
                //iterate through each column of the matrix
                for (int column = 0; column < getN(); column++) {
                    //input the added value of both matrices into the result matrix
                    resultMatrix.matrix[row][column] = m.get(row,column) - get(row,column);
                }
            }
            //return the result matrix
            return resultMatrix;
        }
        System.out.print("The matrices are not the same size, therefore cannot be subtracted.");
        //return null otherwise, the two matrices cannot be subtracted together
        return null;
    }

    /**
     * Multiplies a matrix to the current instance matrix, returns resulting matrix.
     * @param m Matrix that is to be multiplied to the current instance matrix.
     * @return The resulting matrix.
     */
    public Matrix multiply(Matrix m) {
        //create the resulting matrix
        Matrix resultMatrix = new Matrix(getM(), m.getN());
        //iterate through the rows
        for (int i = 0; i < getM(); i++) {
            //iterate through the columns
            for (int j = 0; j < m.getN(); j++) {
                for (int k = 0; k < getN(); k++) {
                    //input the result of multiplication into the resulting matrix
                    resultMatrix.matrix[i][j] += get(i,k) * m.get(k,j);
                }
            }

        }
        //return the resulting matrix
        return resultMatrix;
    }

    /**
     * Multiplies the current instance matrix by a given scalar and returns the result.
     * @param x The scalar that will be multiplied with the matrix.
     * @return The resulting matrix after being multiplied by the scalar.
     */
    public Matrix multiply(double x) {
        //create the resulting matrix
        Matrix resultMatrix = new Matrix(getM(), getN());
        //iterate through the rows
        for (int row = 0; row < getM(); row++) {
            //iterate through the columns
            for (int column = 0; column < getN(); column++) {
                //input the result of multiplication into the resulting matrix
                resultMatrix.matrix[row][column] = get(row,column) * x;
            }
        }
        //return the resulting matrix
        return resultMatrix;
    }

    /**
     *
     * @param m
     * @return
     */
    public Matrix divide(Matrix m) {
        return null;
    }

    /**
     * Calculates and returns the determinant of the matrix.
     * @return The determinant of the matrix, if the matrix is not square or bigger than 3x3 returns null.
     */
    public double determinant() {
        //first check if we can calculate the determinant, must be square and less than 3x3
        if (satisfiesDimensionRequirement(this)) {
            //check what type of square matrix it is
            switch(getM()) {
                //if the matrix is a 1x1 just return the single element as the determinant
                case 1:
                    return matrix[0][0];
                //if the matrix is a 2x2 compute the determinant
                case 2:
                    return (matrix[0][0]*matrix[1][1]) - (matrix[0][1]*matrix[1][0]);
                //if the matrix is a 3x3 compute the determinant
                case 3:
                    return (matrix[0][0]*(matrix[1][1]*matrix[2][2]-matrix[1][2]*matrix[2][1]))-(matrix[0][1]*(matrix[1][0]*matrix[2][2]-matrix[1][2]*matrix[2][0]))+(matrix[0][2]*(matrix[1][0]*matrix[2][1]-matrix[1][1]*matrix[2][0]));
            }
        }
        System.out.print("The matrix is bigger than 3x3 or not square.");
        //return zero if the matrix is not square or bigger than 3x3
        return 0.0;
    }

    /**
     * Returns the inverse of the matrix.
     * @return The inverse matrix, if the matrix is not square or bigger than 3x3 returns null.
     * NOTE: if the matrix has a determinant of zero then it will also return null.
     */
    public Matrix inverse() {
        //first check if we can calculate the determinant, must be square and less than 3x3
        if (satisfiesDimensionRequirement(this)) {
            //determinat of matrix cannot be zero when calculating the inverse
            if (determinant() != 0) {
                //create the inverse matrix
                Matrix inverseMatrix = new Matrix(getM(), getN());
                switch (getM()) {
                    //if the matrix is a 1x1 just return the single element as the determinant
                    case 1:
                        inverseMatrix.matrix[0][0] = 1 / matrix[0][0];
                        break;
                    //if the matrix is a 2x2 compute the determinant
                    case 2:
                        //swap the value of a with d
                        inverseMatrix.matrix[0][0] = matrix[1][1];
                        //swap the value of d with a
                        inverseMatrix.matrix[1][1] = matrix[0][0];
                        //multiply c and d with negative
                        inverseMatrix.matrix[0][1] = matrix[0][1] * -1;
                        inverseMatrix.matrix[1][0] = matrix[1][0] * -1;
                        //multiply new inverted matrix by 1/determinant of original matrix
                        inverseMatrix = inverseMatrix.multiply(1 / determinant());
                        break;
                    //if the matrix is a 3x3 compute the determinant
                    case 3:
                        //perform computations to calculate inverse elements
                        inverseMatrix.matrix[0][0] = matrix[1][1]*matrix[2][2]-matrix[1][2]*matrix[2][1];
                        inverseMatrix.matrix[0][1] = matrix[0][2]*matrix[2][1]-matrix[0][1]*matrix[2][2];
                        inverseMatrix.matrix[0][2] = matrix[0][1]*matrix[1][2]-matrix[0][2]*matrix[1][1];
                        inverseMatrix.matrix[1][0] = matrix[1][2]*matrix[2][0]-matrix[1][0]*matrix[2][2];
                        inverseMatrix.matrix[1][1] = matrix[0][0]*matrix[2][2]-matrix[0][2]*matrix[2][0];
                        inverseMatrix.matrix[1][2] = matrix[0][2]*matrix[1][0]-matrix[0][0]*matrix[1][2];
                        inverseMatrix.matrix[2][0] = matrix[1][0]*matrix[2][1]-matrix[1][1]*matrix[2][0];
                        inverseMatrix.matrix[2][1] = matrix[0][1]*matrix[2][0]-matrix[0][0]*matrix[2][1];
                        inverseMatrix.matrix[2][2] = matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
                        //multiply new inverted matrix by 1/determinant of original matrix
                        inverseMatrix = inverseMatrix.multiply(1 / determinant());
                        break;
                }
                //return the inverse matrix
                return inverseMatrix;
            }
        }
        System.out.print("The matrix is bigger than 3x3 or not square.");
        //return null otherwise
        return null;
    }



    /**
     * Checks to see if a matrix is square.
     * @return True if the matrix is square, otherwise returns false.
     */
    public boolean isSquare() {
        //if the columns are equal to the rows then the matrix is square
        if (getM() == getN()) {
            //return true if this is the case
            return true;
        }
        //otherwise return false
        return false;
    }

    /**
     * Returns the transpose of the matrix.
     * @return The transpose of the current matrix.
     */
    public Matrix transpose() {
        //create the transpose matrix, where the number of rows and column are flipped.
        Matrix transposeMatrix = new Matrix(getN(),getM());
        for (int row = 0; row < getM(); row++) {
            for (int column = 0; column < getN(); column++) {
                transposeMatrix.matrix[column][row] = get(row,column);
            }
        }
        return transposeMatrix;
    }

    ////////////
    /* OTHER */
    //////////

    /**
     * Returns a string representation of the matrix.
     * @return The string representation of the matrix.
     */
    public String toString() {
        String stringRepresentation = "";
        for (int row = 0; row < getM(); row++) {
            for (int column = 0; column < getN(); column++) {
                matrix[column][row] = get(row,column);
            }
        }
        return stringRepresentation;
    }

    /**
     * Prints the matrix to a file whose name is passed to the method.
     * @param fileName Desired name and output location of output file.
     */
    public void print(String fileName) {
        //define the file writer
        FileWriter fileWriter = null;
        try {
            //create a new file writer and pass file name
            fileWriter = new FileWriter(fileName);
            //input the row value into file
            fileWriter.append(String.valueOf(getM()));
            //add delimiter bewtween value
            fileWriter.append(",");
            //input the column value into file
            fileWriter.append(String.valueOf(getN()));
            fileWriter.append("\n");

            //iterate through rows
            for (int row = 0; row < getM(); row++) {
                //iterate through columns
                for (int column = 0; column < getN(); column++) {
                    //input the value of matrix entry into file
                    fileWriter.append(String.valueOf(get(row,column)));
                    //if we aren't on the last column add the delimiter
                    if (column+1 != getN()) {
                        //append the delimiter
                        fileWriter.append(",");
                    }
                }
                //add the new line character before going to next row in matrix
                fileWriter.append("\n");
            }
        } catch (Exception e) {
            System.out.println("Error in file writer.");
        } finally {
            //close and flush the file writer
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                //catch the exception if the file writer can't be closed
                System.out.print("Error closing/flushing writer.");
            }
        }
    }

    /**
     * Returns an identity matrix whose size is passed to the method.
     * @param size The desired size of the identity matrix.
     * @return The identity matrix.
     */
    public static Matrix identity(int size) {
        //create a matrix of the desired size
        Matrix identityMatrix = new Matrix(size,size);
        //iterate through the rows of the matrix
        for (int row = 0; row < size; row++) {
                //set the correct element in the row to one
                identityMatrix.matrix[row][row] = 1.0;
        }
        //return the identity matrix
        return identityMatrix;
    }

    //////////////////////
    /* UTILITY METHODS */
    ////////////////////

    /**
     * Checks if the passed in matrix has the same dimensions of the instance matrix.
     * @param m The matrix that is being compared to the current instance matrix.
     * @return True if the matrices have the same dimensions.
     */
    private boolean isSameDimensions(Matrix m) {
        //check if the instance has the same dimensions of passed matrix
        if ((m.getN() == getN()) && (m.getM() == getM())) {
            //if so return true
            return true;
        }
        return false;
    }

    /**
     * Checks if the matrix is not greater than a 3x3 and that it is square.
     * @param m The matrix to be checked, normally just passes "this" for instance matrix.
     * @return True if the matrix is smaller than 3x3 and if it is square, false otherwise.
     */
    private boolean satisfiesDimensionRequirement(Matrix m) {
        //if the matrix is square and smaller than a 4x4 matrix return true
        if (m.isSquare() && (m.getM() <= 3)) {
            return true;
        }
        //otherwise return false
        return false;
    }

    /**
     * Asks the user to enter a number through the console, also prompts user with question that is passed to method.
     * @param question Question that prompts the user.
     * @return Returns the number the user entered.
     */
    private int askUserNumber(String question) {
        int userNumber = 0;
        //define a boolean to check if the user input is good
        boolean inputOK = false;
        //define dump string to clear keyboard buffer, scanner caveat
        String dump;
        while (!inputOK) {
            try {
                //ask the user how many rows they want in the matrix
                System.out.println(question);
                //get the response from the user
                userNumber = scanner.nextInt();
                //clear the buffer
                dump = scanner.nextLine();
                //input is ok
                inputOK = true;
            } catch (InputMismatchException e) {
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //notify user that exception occurred
                System.out.println("\"" + dump + "\" is not a legal integer, " +
                        "please try again!");
            }
        }
        //return the number the user has entered
        return userNumber;
    }

    /**
     * Asks the user to enter the elements in the matrix they are creating through the console.
     */
    private void askUserForElements() {
        //define dump string to clear keyboard buffer, scanner caveat
        String dump;
        //iterate through rows of matrix
        for (int row = 0; row < getM(); row++) {
            //iterate through columns of matrix
            for (int column = 0; column < getN(); column++) {
                //boolean to check if the user input is ok
                boolean inputOK = false;
                //check if the input is ok
                while (!inputOK) {
                    try {
                        //ask the user for the element in the matrix
                        System.out.printf("Enter element [%d,%d]:",row+1,column+1);
                        //get the users input
                        double userVal = scanner.nextDouble();
                        //set the matrix value to the user value
                        set(row,column,userVal);
                        //input is ok
                        inputOK = true;
                        //clear the keyboard buffer
                        dump = scanner.nextLine();
                    } catch (InputMismatchException e) {
                        //clear the keyboard buffer
                        dump = scanner.nextLine();
                        //notify user that exception occurred
                        System.out.println("\"" + dump + "\" is not a legal double, " +
                                "please try again!");
                    }
                }
            }
        }
    }

    //////////////////////
    /* TESTING METHODS */
    ////////////////////

    /**
     * Used to test the Matrix class, utilizing every method.
     * @param args
     */
    public static void main(String[] args) {

    }

}
