package routes.routes;

import functions.DifferentiableFunction;
import routes.utils.RoutePointInfo;
import utils.Point;

/**
 * the class describes a route, going between 2 certain points in which the
 * angle of the route is known. it contains one main method which receives the
 * description of a route, and returns the data about each point of the route,
 * with a received frequency.
 *
 * @author noam mantin
 */
public class RouteProvider {

	// the following are the x(s) and y(s) functions
	private DifferentiableFunction xFunction;
	private DifferentiableFunction yFunction;

	/**
	 * creates a new Route with given preferences and a given point frequency.
	 *
	 * @param description
	 *            the data which should specify the route.
	 * @return the wanted data about the route, in an array of points
	 *         {@link RoutePointInfo}
	 */
	public RouteProvider(RouteFunctionsProvider functionsSupplier) {
		// initializing x and y functions according to the route description
		xFunction = functionsSupplier.getXFunction();
		yFunction = functionsSupplier.getYFunction();

		System.out.println("X AXIS FUNCTION: " + xFunction);
		System.out.println("X AXIS DERIVATIVE: " + xFunction.getDerivative());
		System.out.println("Y AXIS FUNCTION: " + yFunction);
		System.out.println("Y AXIS DERIVATIVE: " + yFunction.getDerivative());
	}

	/**
	 * calculates and returns the information about this route, as an array of
	 * {@link RoutePointInfo} objects.
	 *
	 * @param pointFrequency
	 *            the frequency of the wanted points on the route. also the length
	 *            of the returned array.
	 */
	public RoutePointInfo[] getRoute(int pointsFrequency) {

		double s;

		// the length of the array
		int n = pointsFrequency;
		RoutePointInfo[] routeData = new RoutePointInfo[n + 1];

		System.out.println("ROUTE INFO");

		for (int i = 0; i <= n; i++) {
			// calculating the current s value
			s = (double) i / n;
			// creating the information object about this point
			System.out.println("s: " + s);
			routeData[i] = new RoutePointInfo(get(s), getArgument(s), getRadius(s), getDistance(i, pointsFrequency),
					getTotalDistance(i, pointsFrequency));
			System.out.println(routeData[i]);
		}
		return routeData;
	}

	//////////////// CONTINUOUS ROUTE DATA SUPPLIERS//////////////////////////
	/*
	 * the following refer to the route as a continuous function and calculate
	 * properties of a given point on the route
	 */

	/**
	 * the function calculates and returns the point of the route at a certain s
	 * 
	 * @param s:
	 *            the certain s.
	 * 
	 * @return: the point where the route passes at the received s.
	 */

	private Point get(double s) {
		return new Point(xFunction.apply(s), yFunction.apply(s));
	}

	/**
	 * the function receives a specific s and calculates the argument of the route
	 * relative to the positive direction of the x axis, at this s.
	 * 
	 * @param s:
	 *            the certain s
	 * @return: the argument, moving from 0 to 2pi
	 */
	private double getArgument(double s) {

		// calculating x'(s) and y'(s)
		double dx = xFunction.getDerivative().apply(s);
		double dy = yFunction.getDerivative().apply(s);

		double arg = Math.atan2(dy, dx);

		if (arg < 0)
			arg += Math.PI;

		return arg;
	}

	/**
	 * the functions calculates and returns the linear velocity (relative to s) in a
	 * certain s.
	 * 
	 * @param s
	 *            the certain s.
	 * @return the linear velocity of the route at that s, which is equal to
	 *         sqrt(x'^2+y'^2)
	 */
	private double getLinearVelocity(double s) {

		// calculating the derivatives of the axis functions at s
		double xDiff = xFunction.getDerivative().apply(s);
		double yDiff = yFunction.getDerivative().apply(s);

		// calculating the result- sqrt(x'^2+y'^2)
		double result = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

		return result;
	}

	/**
	 * the functions calculates and returns the angular velocity (relative to s) in
	 * a certain s.
	 * 
	 * @param s
	 *            the certain s.
	 * @return the angular velocity of the route at that s, which is equal to
	 *         (x'^2+y'^2)/(y''x'-x''y')
	 */
	private double getAngularVelocity(double s) {

		// calculating first and second derivatives of the axis functions at s
		double x1 = xFunction.getDerivative().apply(s);
		double y1 = yFunction.getDerivative().apply(s);

		double x2 = xFunction.getDerivative(2).apply(s);
		double y2 = yFunction.getDerivative(2).apply(s);

		// calculating the numerator and denominator of the result
		double denominator = x1 * x1 + y1 * y1;

		double numerator = y2 * x1 - x2 * y1;

		return numerator / denominator;
	}

	/**
	 * the function calculates the radius of the imaginary circle the route
	 * approximates at a certain s
	 * 
	 * @param s
	 *            the certain s
	 * @return the radius of the circle mentioned above. the route at that point is
	 *         approximately a part of a circle, and thus: r=v/w (r- the radius, v-
	 *         linear velocity, w- angular velocity)
	 */
	private double getRadius(double s) {
		if (getAngularVelocity(s) == 0)// the route goes straight
			return 0;
		else
			return getLinearVelocity(s) / getAngularVelocity(s);
	}

	//////////////// DISCRETE ROUTE DATA SUPPLIERS//////////////////////////
	/*
	 * the following functions refer to the route as an array of points, and
	 * calculates discrete properties of points on the route.
	 */

	/**
	 * the function calculates and returns the distance between two adjacent (TODO
	 * is that use of the word OK? ) points of the route.
	 * 
	 * @param index
	 *            the index of the second of the two points.
	 * @param frequency
	 *            the frequency of the points on the route.
	 * @return the distance between the two points.
	 */
	private double getDistance(int index, int frequency) {
		if (index == 0)
			return 0;
		// the points are in the middle of the route
		return Point.distance(get((double) index / frequency), get((double) (index - 1) / frequency));
	}

	/**
	 * the function calculates and returns the total distance, from start to a
	 * certain point, on the route.
	 * 
	 * @param index
	 *            the index of the certain point.
	 * @param frequency
	 *            the frequency of the points on the route.
	 * @return the total distance (on the route) from start to the k'th point.
	 */
	private double getTotalDistance(int index, int frequency) {

		double distance = 0;

		for (int i = 1; i < index; i++)
			distance += getDistance(i, frequency);
		return distance;
	}
}