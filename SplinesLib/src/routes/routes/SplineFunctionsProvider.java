package routes.routes;

import functions.DifferentiableFunction;
import functions.PolynomialFunction;
import routes.utils.Position2D;

/**
 * an implementation of {@link RouteDescription}. uses 3rd degree polynomial
 * functions as axis functions.
 *
 * @author noam mantin
 */
public class SplineFunctionsProvider implements RouteFunctionsProvider {

	// k is a constant of the route, which determines the curvature of the route.
	private double k;
	// start and finish points
	private Position2D start;
	private Position2D finish;

	/**
	 * the function builds a new {@link RouteDescription} object, with received
	 * start and finish points
	 * 
	 * @param start
	 *            the point from which the route should start.
	 * @param finish
	 *            the point which should be the end of the route.
	 * @param k
	 *            a parameter which defines the curvature of the route.
	 */
	public SplineFunctionsProvider(Position2D start, Position2D finish, double k) {
		this.start = start;
		this.finish = finish;
	}

	@Override
	public DifferentiableFunction getXFunction() {
		return calculate3degreePolynomial(start.getX(), finish.getX(), k * Math.cos(start.getYaw()),
				k * Math.cos(finish.getYaw()));
	}

	@Override
	public DifferentiableFunction getYFunction() {
		return calculate3degreePolynomial(start.getY(), finish.getY(), k * Math.sin(start.getYaw()),
				k * Math.sin(finish.getYaw()));
	}

	/**
	 * calculates and returns a polynomial function of degree 3 (as^3+vs^2+cs+d
	 * function) according to a given set of properties of the wanted function.
	 * 
	 * @param valueAt0
	 *            the value of the function at 0
	 * @param valueAt1
	 *            the value of the function at 1
	 * @param derivativeAt0
	 *            the derivative of the function at 0
	 * @param derivativeAt1
	 *            the derivative of the function at 1
	 * @return: the polynomial function which should fit the conditions above.
	 */
	private PolynomialFunction calculate3degreePolynomial(double valueAt0, double valueAt1, double derivativeAt0,
			double derivativeAt1) {
		// calculating as^3+vs^2+cs+d function

		double totalDiff = valueAt1 - valueAt0;

		double a = derivativeAt0 + derivativeAt1 - 2 * totalDiff;

		double b = 3 * totalDiff - 2 * derivativeAt0 - derivativeAt1;

		double c = derivativeAt0;

		double d = valueAt0;

		return new PolynomialFunction(d, c, b, a);
	}
}
