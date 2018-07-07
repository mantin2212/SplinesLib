package routes.routes;

import functions.DifferentiableFunction;
import routes.utils.FunctionEdgesInfo;
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

	/**
	 * multiplies the derivatives of a {@link FunctionEdgesInfo} object by k
	 * 
	 * @param data
	 *            the specific object
	 */
	private void updateDerivatives(FunctionEdgesInfo data) {
		data.setDerivativeAt0(data.getDerivativeAt0() * k);
		data.setDerivativeAt1(data.getDerivativeAt1() * k);
	}

	@Override
	public DifferentiableFunction getXFunction() {
		FunctionEdgesInfo result = getXAxisData();

		updateDerivatives(result);

		return Utils.calculate3degreePolinom(result);
	}

	@Override
	public DifferentiableFunction getYFunction() {
		FunctionEdgesInfo result = getYAxisData();

		updateDerivatives(result);

		return Utils.calculate3degreePolinom(result);
	}

	/**
	 * returns the function data which is relevant to the X axis.
	 * 
	 * @return a {@link FunctionEdgesInfo} object which contains the mentioned data.
	 */
	private FunctionEdgesInfo getXAxisData() {
		return new FunctionEdgesInfo(start.getX(), finish.getX(), Math.cos(start.getYaw()), Math.cos(finish.getYaw()));
	}

	/**
	 * returns the function data which is relevant to the Y axis.
	 * 
	 * @return a {@link FunctionEdgesInfo} object which contains the mentioned data.
	 */
	private FunctionEdgesInfo getYAxisData() {
		return new FunctionEdgesInfo(start.getY(), finish.getY(), Math.sin(start.getYaw()), Math.sin(finish.getYaw()));
	}
}
