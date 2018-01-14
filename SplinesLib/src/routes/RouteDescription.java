package routes;

import functions.DifferentiableFunction;
import routes.Route.Axis;
import utils.ArgPoint;

public abstract class RouteDescription {

	private ArgPoint start;
	private ArgPoint finish;

	public RouteDescription(ArgPoint start, ArgPoint finish) {
		this.start = start;
		this.finish = finish;
	}

	protected abstract DifferentiableFunction getXFunction();

	protected abstract DifferentiableFunction getYFunction();

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
		return new double[] { start.getX(), finish.getX(), Math.cos(start.getAngle()), Math.cos(finish.getAngle()) };
	}

	private double[] getYData() {
		return new double[] { start.getY(), finish.getY(), Math.sin(start.getAngle()), Math.sin(finish.getAngle()) };
	}

}
