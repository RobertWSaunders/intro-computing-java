/**
 * Created by robertsaunders on 2017-02-12.
 */

public class TestMatrix {
    public static void main(String[] args) {
       /* Matrix m = new Matrix(5,6);
        System.out.printf("%d",m.getN());
        System.out.printf("%d",m.getM());




       // Matrix me = new Matrix("./matrix.csv");
       // Matrix bob = me.add(test);
       // System.out.print(test.get(0,0));

        Matrix test = new Matrix();
        */
        Matrix test = new Matrix("./test.csv");
        Matrix other = new Matrix("./matrix.csv");

        Matrix output = test.multiply(other);
        output.print("./mul.csv");
        //Matrix inverse = test.inverse();
        System.out.print("Hey");

    }
}
