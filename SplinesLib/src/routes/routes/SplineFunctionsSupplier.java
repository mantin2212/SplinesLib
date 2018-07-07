package routes.routes;

import functions.DifferentiableFunction;
import routes.utils.Position2D;
import utils.Utils;

/**
 * an implementation of {@link RouteDescription}. uses 3rd degree polynomial
 * functions as axis functions.
 * 
 * @author noam mantin
 *
 */
public class SplineFunctionsSupplier implements RouteFunctionsSupplier {

	// k is a constant of the route, which determines the curvature of the route.
	private double k;
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
	 */
	public SplineFunctionsSupplier(Position2D start, Position2D finish, double k) {
		this.start = start;
		this.finish = finish;
	}

	@Override
	public DifferentiableFunction getXFunction() {

		return Utils.calculate3degreePolinom(start.getX(), finish.getX(), k * Math.cos(start.getYaw()),
				k * Math.cos(finish.getYaw()));
	}

	@Override
	public DifferentiableFunction getYFunction() {
		return Utils.calculate3degreePolinom(start.getY(), finish.getY(), k * Math.sin(start.getYaw()),
				k * Math.sin(finish.getYaw()));
	}

}
