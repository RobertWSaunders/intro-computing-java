public class RigthTriangle extends Shape {
	private int a; // leg 1
	private int b; // leg 2
	private int c; // hypotenuse

	public RigthTriangle(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	@Override
	public double getArea() {
		return 0.5 * this.getB() * this.getA();
	}


}