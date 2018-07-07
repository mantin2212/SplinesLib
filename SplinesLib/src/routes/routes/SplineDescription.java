package routes.routes;

import functions.DifferentiableFunction;
import routes.utils.Position2D;
import routes.utils.FunctionEdgesInfo;
import utils.Utils;

/**
 * an implementation of {@link RouteDescription}. uses 3rd degree polynomial
 * functions as axis functions.
 * 
 * @author noam mantin
 *
 */
public class SplineDescription extends RouteDescription {

	// k is a constant of the route, which determines the curvature of the route.
	private double k;

	/**
	 * builds a new {@link SplineDescription}, with received fields
	 * 
	 * @param start
	 * @param finish
	 * @param k
	 */
	public SplineDescription(Position2D start, Position2D finish, double k) {
		super(start, finish);
		this.k = k;
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
	public DifferentiableFunction getFunction(Axis axis) {

		FunctionEdgesInfo result = getAxisData(axis);

		updateDerivatives(result);

		return Utils.calculate3degreePolinom(result);
	}
}
