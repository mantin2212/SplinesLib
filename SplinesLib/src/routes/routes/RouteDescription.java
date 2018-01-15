package routes.routes;

import functions.DifferentiableFunction;
import routes.ArgPoint;
import routes.RouteFunctionData;

/**
 * the class is used to specify a route. it has the start and finish points of
 * the route, and uses two functions, one for each axis. the class uses a
 * variable- s, which illustrates the progress of the route as a number between
 * 0 to 1.
 * 
 * @author noam mantin
 */
public abstract class RouteDescription {

	/**
	 * the following enum describes an axis, and can contain one of two values- X
	 * and Y.
	 * 
	 * @author noam mantin
	 *
	 */
	public enum Axis {
		X, Y
	}

	// the start and finish points of the route
	private ArgPoint start;
	private ArgPoint finish;

	/**
	 * the function builds a new {@link RouteDescription} object, with received
	 * start and finish points
	 * 
	 * @param start
	 *            the point from which the route should start.
	 * @param finish
	 *            the point which should be the end of the route.
	 */
	public RouteDescription(ArgPoint start, ArgPoint finish) {
		this.start = start;
		this.finish = finish;
	}

	/**
	 * calculates and returns the axis function (x(s) or y(s)) of the route.
	 * 
	 * @param axis
	 *            the axis the function refers to.
	 * @return the received axis's function of the route.
	 */
	public abstract DifferentiableFunction getFunction(Axis axis);

	/**
	 * the function receives an axis and returns the relevant data about the axis
	 * 
	 * @param axis:
	 *            the axis the data refers to
	 * @return: the relevant data of the received axis
	 */
	protected RouteFunctionData getAxisData(Axis axis) {
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

	/**
	 * returns the function data which is relevant to the X axis.
	 * 
	 * @return a {@link RouteFunctionData} object which contains the mentioned data.
	 */
	private RouteFunctionData getXData() {
		return new RouteFunctionData(start.getX(), finish.getX(), Math.cos(start.getAngle()),
				Math.cos(finish.getAngle()));
	}

	/**
	 * returns the function data which is relevant to the Y axis.
	 * 
	 * @return a {@link RouteFunctionData} object which contains the mentioned data.
	 */

	private RouteFunctionData getYData() {
		return new RouteFunctionData(start.getY(), finish.getY(), Math.sin(start.getAngle()),
				Math.sin(finish.getAngle()));
	}

}
