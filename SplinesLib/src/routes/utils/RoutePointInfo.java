package routes.utils;

import routes.routes.RouteProvider;
import utils.Point;

/**
 * this class contains extended information about a point in a route.
 *
 * @author noam mantin
 * 
 * @see RouteProvider
 */
public class RoutePointInfo {

	private final Point position;

	private final double arg;
	private final double rotationRadius;

	private final double distance;
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
	 *            the distance between this point of the route and the previous one.
	 * @param totalDistance
	 *            the total distance (on the route) between this point and the
	 *            beginning point of the route.
	 */
	public RoutePointInfo(Point p, double arg, double rotationRadius, double distance, double totalDistance) {
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
	 *            the distance between this point of the route and the previous one.
	 * @param totalDistance
	 *            the total distance (on the route) between this point and the
	 *            beginning point of the route.
	 */
	public RoutePointInfo(double x, double y, double arg, double radius, double distance, double totalDistance) {
		this(new Point(x, y), arg, radius, distance, totalDistance);
	}

	public Point getPosition() {
		return position;
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

	@Override
	public String toString() {
		String result = "";

		// result += "position: " + position;
		// result += "\nargument: " + arg;
		// result += "\ndistance: " + distance;
		// result += "\ntotal distance: " + totalDistance;
		result += "radius: " + rotationRadius;

		return result;
	}
}