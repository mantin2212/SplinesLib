package routes.utils;

import java.awt.geom.Point2D;

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
public class Position2D extends Point2D.Double {

	private static final long serialVersionUID = 1L;

	// the yaw angle of the point
	private double yawAngle;

	/**
	 * creates a new {@link Position2D} object, with specific x,y values and a
	 * given angle.
	 *
	 * @param x
	 *            the x axis value of the point
	 * @param y
	 *            the y axis value of the point
	 * @param yaw
	 *            the wanted angle.
	 */
	public Position2D(double x, double y, double yaw) {
		super(x, y);
		this.yawAngle = yaw;
	}

	/**
	 * creates a new {@link Position2D} object, with given point and angle.
	 *
	 * @param p
	 *            the physical point of the object
	 * @param yaw
	 *            the wanted angle.
	 */
	public Position2D(Point2D p, double yaw) {
		super(p.getX(), p.getY());
		this.yawAngle = yaw;
	}

	/**
	 * copies a given {@link Position2D} object's properties into this one.
	 *
	 * @param position
	 *            the position of the object.
	 */
	public Position2D(Position2D position) {
		this(position.getX(), position.getY(), position.yawAngle);
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
