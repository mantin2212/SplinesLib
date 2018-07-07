package routes;

import utils.Point;

/**
 * this class describes a 2D position of an object, as a point and a yaw angle
 * (direction).
 *
 * @author noam mantin
 *
 */
/**
 * @author noam mantin
 *
 */
public class ArgPoint extends Point {

	// the yaw angle of the point
	private double yawAngle;

	/**
	 * creates a new {@link ArgPoint} object, with specific x,y values and a given
	 * angle.
	 *
	 * @param x
	 *            the x axis value of the point
	 * @param y
	 *            the y axis value of the point
	 * @param yaw
	 *            the wanted angle.
	 */
	public ArgPoint(double x, double y, double yaw) {
		super(x, y);
		this.yawAngle = yaw;
	}

	/**
	 * creates a new {@link ArgPoint} object, with given point and angle.
	 *
	 * @param p
	 *            the physical point of the object
	 * @param yaw
	 *            the wanted angle.
	 */
	public ArgPoint(Point p, double yaw) {
		super(p);
		this.yawAngle = yaw;
	}

	/**
	 * copies a given {@link ArgPoint} object's properties into this one.
	 *
	 * @param ap
	 *            the position of the object.
	 */
	public ArgPoint(ArgPoint ap) {
		this(new Point(ap.getX(), ap.getY()), ap.yawAngle);
	}

	/**
	 * returns the yaw angle of this position.
	 *
	 * @return the yaw angle of this position.
	 */
	public double getYaw() {
		return yawAngle;
	}

	/**
	 * sets the yaw angle of this position to a given one.
	 *
	 * @param yaw
	 *            the new yaw angle.
	 */
	public void setAngle(double yaw) {
		this.yawAngle = yaw;
	}
}
