import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;

public class TestShapes {

	public static void main(String[] args) {
		//create an array list to store our shapes
		//could use a basic array but this is mutable and more flexible for scalability 
		ArrayList<Shape> shapes = new ArrayList();
		
		//lets create some shapes
		//create a square 
		Square s = new Square(2);
		//create a circle
		Circle c = new Circle(4);
		//create a right triangle
		RigthTriangle r = new RigthTriangle(3, 4, 5);
		c.testInheritance();
		//now lets add those shapes to our list
		shapes.add(s);
		shapes.add(c);
		shapes.add(r);
		s.testString = "Helow";
		//lets create a variable to hold our total area 
		double total = 0;
		RigthTriangle r2 = new RigthTriangle();
		//now lets loop through the list and calculate the total area
		for(Shape shape : shapes) {
			//using polymorphism, will use the getArea() for whatever type of shape it is, through late binding
			total += shape.getArea();
		}
		
		//lets print out our total area
		System.out.println("total = "+ total); //output: total = 60.2654
		
		
	}
}
