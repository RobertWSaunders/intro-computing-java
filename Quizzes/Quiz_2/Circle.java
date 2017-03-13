
/**
 * Circle extends Shape, as Circle is a Shape.
 * @author 15rws
 */
public class Circle extends Shape {
	private int r;

	public Circle(int r) {
		this.r = r;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	/**
	 * Override the abstract method from the Shape class.
	 */
	@Override
	public double getArea() {
		//return the result of the formula
		return Math.PI * this.getR() * this.getR();
	}

	
}
