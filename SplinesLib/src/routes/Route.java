package routes;

import functions.DifferentiableFunction;
import utils.Point;

/**
 * @author noam mantin the class describes a route, going between 2 certain
 *         points in which the angle of the route is known. it uses two
 *         functions- one for each axis. the class uses a variable- s, which
 *         illustrates the progress of the route as a number between 0 to 1
 */
public abstract class Route {

	enum Axis {
		X, Y
	}

	// the following are the x(s) and y(s) functions
	protected DifferentiableFunction xFunction;
	protected DifferentiableFunction yFunction;

	protected RoutePoint[] data;
	protected int pointsNumber;

	// the start and finish points
	private Point start;
	private Point finish;

	// the start and finish angles
	private double startArg;
	private double finishArg;

	public static final double INTEGRAL_STEP = 0.001;

	/** the parameters used to specify the route, @see Spline for example **/
	protected double[] routeParameters;

	public Route(Point start, Point finish, double startArg, double finishArg, int pointNumber, double... parameters) {

		// initializing class variables
		this.start = start;
		this.finish = finish;

		this.startArg = startArg;
		this.finishArg = finishArg;

		routeParameters = parameters;
		this.pointsNumber = pointNumber;

		// calculating the axis functions
		calculateXAndYFunctions();
		initializeData(pointNumber);
	}

	protected abstract void calculateXAndYFunctions();

	/**
	 * the function receives an axis and returns the relevant data about the axis
	 * 
	 * @param axis:
	 *            the axis the data refers to
	 * @return: the relevant data of the received axis
	 */
	protected double[] getAxisData(Axis axis) {
		switch (axis) {
		case X:
			return getXData();
		case Y:
			return getYData();
		default:
			System.out.println("ERROR");
			return null;
		}
	}

	private double[] getXData() {
		return new double[] { start.getX(), finish.getX(), Math.cos(startArg), Math.cos(finishArg) };
	}

	private double[] getYData() {
		return new double[] { start.getY(), finish.getY(), Math.sin(startArg), Math.sin(finishArg) };
	}

	//////////////// CONTINOUS ROUTE DATA FUNCTIONS//////////////////////////
	/*
	 * the following functions receive a certain s and return some data about the
	 * route at the specific point
	 */

	/**
	 * the function calculates and returns the point of the route at a certain s
	 * 
	 * @param s:
	 *            the certain s.
	 * 
	 * @return: the point where the route passes at the received s.
	 */

	public Point get(double s) {
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
	public double getArgument(double s) {

		// calculating x'(s) and y'(s)
		double dx = xFunction.getDerivative().apply(s);
		double dy = yFunction.getDerivative().apply(s);

		double arg = Math.atan2(dy, dx);

		if (arg < 0)
			arg += Math.PI;

		return arg;
	}

	private double getLinearVelocity(double s) {

		// calculating the derivatives of the axis functions at s
		double xDiff = xFunction.getDerivative().apply(s);
		double yDiff = yFunction.getDerivative().apply(s);

		// calculating the result- sqrt(x'^2+y'^2)
		double result = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

		return result;
	}

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

	private double getDistance(int k) {
		if (k == 0)
			return 0;
		return Point.distance(data[k], data[k - 1]);
	}

	private double getTotalDistance(int k) {
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
	private double getCurrentRadius(double s) {
		return getLinearVelocity(s) / getAngularVelocity(s);
	}

	private void initializeData(int n) {
		double s;

		for (int k = 0; k <= n; k++) {
			s = (double) k / n;
			data[k] = new RoutePoint(get(s), getArgument(s), getCurrentRadius(s), getDistance(k), getTotalDistance(k));
		}
	}
}
