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

	public enum Axis {
		X, Y
	}

	private ArgPoint start;
	private ArgPoint finish;

	public RouteDescription(ArgPoint start, ArgPoint finish) {
		this.start = start;
		this.finish = finish;
	}

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

	private RouteFunctionData getXData() {
		return new RouteFunctionData(start.getX(), finish.getX(), Math.cos(start.getAngle()),
				Math.cos(finish.getAngle()));
	}

	private RouteFunctionData getYData() {
		return new RouteFunctionData(start.getY(), finish.getY(), Math.sin(start.getAngle()),
				Math.sin(finish.getAngle()));
	}

}
