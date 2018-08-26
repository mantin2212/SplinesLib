package routes.routes;

import functions.DifferentiableFunction;

/**
 * this interface describes an object which should supply the x(s) and y(s)
 * functions of a route.
 *
 * @author noam mantin
 * 
 * @see RouteProvider
 */
public interface RouteFunctionsProvider {

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
