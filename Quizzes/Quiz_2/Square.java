
public class Square extends Shape {
	private int x;

	public Square(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public double getArea() {
		return this.getX() * this.getX();
	}

}