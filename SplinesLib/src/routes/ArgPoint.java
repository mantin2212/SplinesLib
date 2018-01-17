package routes;

import utils.Point;

/**
 * this class describes a point with an angle. it is used for a route's start
 * and finish points.
 * 
 * @author noam mantin
 *
 */
public class ArgPoint extends Point {

	// the angle of the point
	private double angle;

	/**
	 * creates a new {@link ArgPoint} object, with specific x,y and angle.
	 * 
	 * @param p
	 *            the physical point of the object
	 * @param angle
	 *            the angle of the point
	 */
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
