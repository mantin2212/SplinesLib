package utils;

public class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this(p.getX(), p.getY());
	}

	public double getY() {
		return y;
	}

	public int getIntY() {
		return (int) y;
	}

	public int getIntX() {
		return (int) x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setXAndY(double x, double y) {
		setX(x);
		setY(y);
	}

	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public void multiply(double a) {
		x *= a;
		y *= a;
	}

	@Override
	public String toString() {

		return "(" + x + "," + y + ")";
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.getY() - p2.getY(), 2) + Math.pow(p1.getX() - p2.getX(), 2));
	}
}
