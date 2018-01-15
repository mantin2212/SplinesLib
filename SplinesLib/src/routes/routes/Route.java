package routes.routes;

import functions.DifferentiableFunction;
import routes.RoutePointData;
import routes.routes.RouteDescription.Axis;
import utils.Point;

/**
 * the class describes a route, going between 2 certain points in which the
 * angle of the route is known. it contains one main static function which
 * receives the description of a route, and returns the data about each point of
 * the route, with a received frequency.
 * 
 * @author noam mantin
 */
public class Route {

	// the following are the x(s) and y(s) functions
	private static DifferentiableFunction xFunction;
	private static DifferentiableFunction yFunction;

	// the data of the route, in an array of Points and certain values.
	private static RoutePointData[] routeData;

	// the frequency of the points the array contains.
	private static int pointsNumber;

	/**
	 * the function receives details which specify a route {@link RouteDescription},
	 * and returns the data about the route.
	 * 
	 * @param description
	 *            the data which should specify the route.
	 * @param PointNumber
	 *            the frequency of the wanted points. also the length of the
	 *            returned array.
	 * @return the wanted data about the route, in an array of points
	 *         {@link RoutePointData}
	 */
	public static RoutePointData[] getRoute(RouteDescription description, int pointFrequency) {

		// initializing x and y functions according to the route description
		xFunction = description.getFunction(Axis.X);
		yFunction = description.getFunction(Axis.Y);

		// initializing point number
		pointsNumber = pointFrequency;

		// initializing and returning the array
		initializeRouteData();
		return routeData;
	}

	/**
	 * a function which uses the route data functions in order to initialize the
	 * data static field of the class.
	 */
	private static void initializeRouteData() {
		double s;
		int n = pointsNumber;
		routeData = new RoutePointData[pointsNumber + 1];

		for (int k = 0; k <= n; k++) {
			s = (double) k / n;
			routeData[k] = new RoutePointData(get(s), getArgument(s), getRadius(s), getDistance(k),
					getTotalDistance(k));
		}
	}

	//////////////// CONTINOUS ROUTE DATA FUNCTIONS//////////////////////////
	/*
	 * the following functions receive a certain s or k and return some data about
	 * the route at the specific point
	 */

	/**
	 * the function calculates and returns the point of the route at a certain s
	 * 
	 * @param s:
	 *            the certain s.
	 * 
	 * @return: the point where the route passes at the received s.
	 */

	private static Point get(double s) {
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
	private static double getArgument(double s) {

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
	private static double getLinearVelocity(double s) {

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
	private static double getAngularVelocity(double s) {

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
	 * the function calculates and returns the distance between two adjacent (TODO
	 * is that use of the word OK? ) points of the route.
	 * 
	 * @param k
	 *            the index of the second of the two points.
	 * @return the distance between the two points.
	 */
	private static double getDistance(int k) {
		if (k == 0)
			return 0;
		// the points are in the middle of the route
		return Point.distance(get((double) k / pointsNumber), get((double) (k - 1) / pointsNumber));
	}

	/**
	 * the function calculates and returns the total distance, from start to a
	 * certain point, on the route.
	 * 
	 * @param k
	 *            the index of the certain point.
	 * @return the total distance (on the route) from start to the k'th point.
	 */
	private static double getTotalDistance(int k) {
		double distance = 0;
		for (int i = 1; i < k; i++)
			distance += getDistance(i);
		return distance;
	}

	/**
	 * the function calculates the radius of the imaginary circle the route
	 * approximates at a certain s
	 * 
	 * @param s
	 *            the certain s
	 * @return the radius of the circle. the route at that point is approximately a
	 *         part of a circle, and thus: r=v/w (r- the radius, v- linear velocity,
	 *         w- angular velocity)
	 */
	private static double getRadius(double s) {
		return getLinearVelocity(s) / getAngularVelocity(s);
	}

}