package routes.utils;

import java.awt.geom.Point2D;

import routes.routes.RouteDataProvider;

/**
 * this class contains extended information about a point in a route:
 * <ul>
 * <li>position</li>
 * <li>argument</li>
 * <li>rotationRadius</li>
 * <li>the distance between this point and the previous point</li>
 * <li>the distance between this point and the first point</li>
 * </ul>
 *
 * @author noam mantin
 * 
 * @see RouteDataProvider
 */
public class RoutePointInfo {

	/**
	 * the positions of the point
	 */
	private final Point2D position;

	/**
	 * the argument (yaw angle) of point
	 */
	private final double arg;
	/**
	 * the rotation radius of the point
	 */
	private final double rotationRadius;

	/**
	 * the distance between this point and the previous point on the route
	 */
	private final double distance;
	/**
	 * the distance between this point and the first point on the route
	 */
	private final double totalDistance;

	/**
	 * creates s new {@link RoutePointInfo} with given preferences.
	 * 
	 * @param p
	 *            the physical coordinates of the point.
	 * @param arg
	 *            the argument of the route at this point.
	 * @param rotationRadius
	 *            the rotation radius of the route in this point.
	 * @param distance
	 *            the distance between this point of the route and the previous
	 *            one.
	 * @param totalDistance
	 *            the total distance (on the route) between this point and the
	 *            beginning point of the route.
	 */
	public RoutePointInfo(Point2D p, double arg, double rotationRadius, double distance, double totalDistance) {
		this.position = p;

		this.arg = arg;
		this.rotationRadius = rotationRadius;

		this.distance = distance;
		this.totalDistance = totalDistance;
	}

	/**
	 * creates s new {@link RoutePointInfo} with given preferences.
	 * 
	 * @param x
	 *            the x coordinate of this point.
	 * @param y
	 *            the y coordinate of this point.
	 * @param arg
	 *            the argument of the route at this point.
	 * @param rotationRadius
	 *            the rotation radius of the route in this point.
	 * @param distance
	 *            the distance between this point of the route and the previous
	 *            one.
	 * @param totalDistance
	 *            the total distance (on the route) between this point and the
	 *            beginning point of the route.
	 */
	public RoutePointInfo(double x, double y, double arg, double radius, double distance, double totalDistance) {
		this(new Point2D.Double(x, y), arg, radius, distance, totalDistance);
	}

	/**
	 * returns the position of the point
	 * 
	 * @return the position of the point
	 */
	public Point2D getPosition() {
		return position;
	}

	/**
	 * returns the distance between this point and the previous point on the
	 * route
	 * 
	 * @return the distance between this point and the previous point on the
	 *         route
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * returns the distance between this point and the first point on the route
	 * 
	 * @return the distance between this point and the first point on the route
	 */
	public double getTotalDistance() {
		return totalDistance;
	}

	/**
	 * returns the argument of the point (its yaw angle)
	 * 
	 * @return the argument of the point (its yaw angle)
	 */
	public double getArg() {
		return arg;
	}

	/**
	 * returns the rotation radius of the point
	 * 
	 * @return the rotation radius
	 */
	public double getRotationRadius() {
		return rotationRadius;
	}
}