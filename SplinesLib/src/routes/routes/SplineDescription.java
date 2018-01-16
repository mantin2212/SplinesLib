package routes.routes;

import functions.DifferentiableFunction;
import routes.ArgPoint;
import routes.RouteFunctionData;
import utils.Utils;

/**
 * an implementation of {@link RouteDescription}. uses 3rd degree polynomial
 * functions as axis functions.
 * 
 * @author noam mantin
 *
 */
public class SplineDescription extends RouteDescription {

	/*
	 * Route.getRoute(new SplineDescription(start,target,k),n);
	 */

	// k is a constant of the route, which determines the curvature of the route.
	private double k;

	/**
	 * builds a new {@link SplineDescription}, with received fields
	 * 
	 * @param start
	 * @param finish
	 * @param k
	 */
	public SplineDescription(ArgPoint start, ArgPoint finish, double k) {
		super(start, finish);
		this.k = k;
	}

	private void updateDerivatives(RouteFunctionData data) {
		data.setDerivativeAt0(data.getDerivativeAt0() * k);
		data.setDerivativeAt1(data.getDerivativeAt1() * k);
	}

	@Override
	public DifferentiableFunction getFunction(Axis axis) {

		RouteFunctionData result = getAxisData(axis);

		updateDerivatives(result);

		return Utils.calculate3degreePolinom(result);
	}
}
