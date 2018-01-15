package routes;

import utils.Point;

public class ArgPoint extends Point {

	private double angle;

	public ArgPoint(Point p, double angle) {
		super(p);
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
