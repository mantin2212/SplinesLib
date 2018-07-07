package routes.routes;

import functions.DifferentiableFunction;

public interface RouteFunctionsSupplier {

	/**
	 * calculates and returns the x axis function (x(s)) of the route.
	 * 
	 * @return the x axis function of the route.
	 */
	public abstract DifferentiableFunction getXFunction();

	/**
	 * calculates and returns the y axis function (y(s)) of the route.
	 * 
	 * @return the y axis function of the route.
	 */
	public abstract DifferentiableFunction getYFunction();
}
