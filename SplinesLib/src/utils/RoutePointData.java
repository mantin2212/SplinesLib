package utils;

public class RoutePointData extends Point {

	private final double arg;
	private final double radius;

	private final double distance;
	private final double totalDistance;

	public RoutePointData(Point p, double arg, double radius, double distance, double totalDistance) {
		super(p);

		this.arg = arg;
		this.radius = radius;

		this.distance = distance;
		this.totalDistance = totalDistance;
	}

	public RoutePointData(double x, double y, double arg, double radius, double distance, double totalDistance) {
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

	public double getRadius() {
		return radius;
	}
}
