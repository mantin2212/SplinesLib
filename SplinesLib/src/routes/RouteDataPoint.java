package routes;

import routes.routes.Route;
import utils.Point;

/**
 * this class contains extended information about a point in a route.
 *
 * @author noam mantin
 * 
 * @see Route
 */
public class RouteDataPoint extends Point {

	private final double arg;
	private final double rotationRadius;

	private final double distance;
	private final double totalDistance;

	/**
	 * creates s new {@link RouteDataPoint} with given preferences.
	 * 
	 * @param p
	 *            the physical coordinates of the point.
	 * @param arg
	 *            the argument of the route at this point.
	 * @param rotationRadius
	 *            the rotation radius of the route in this point.
	 * @param distance
	 *            the distance between this point of the route and the previous one.
	 * @param totalDistance
	 *            the total distance (on the route) between this point and the
	 *            beginning point of the route.
	 */
	public RouteDataPoint(Point p, double arg, double rotationRadius, double distance, double totalDistance) {
		super(p);

		this.arg = arg;
		this.rotationRadius = rotationRadius;

		this.distance = distance;
		this.totalDistance = totalDistance;
	}

	/**
	 * creates s new {@link RouteDataPoint} with given preferences.
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
	 *            the distance between this point of the route and the previous one.
	 * @param totalDistance
	 *            the total distance (on the route) between this point and the
	 *            beginning point of the route.
	 */
	public RouteDataPoint(double x, double y, double arg, double radius, double distance, double totalDistance) {
		this(new Point(x, y), arg, radius, distance, totalDistance);
	}

	public double getDistance() {
		return distance;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public double getArg() {
		return arg;
	}

	public double getRotationRadius() {
		return rotationRadius;
	}
}
