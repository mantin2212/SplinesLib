package routes;

import utils.Point;
import utils.Utils;

public class Spline extends Route {

	public Spline(Point start, Point finish, double startArg, double finishArg, int pointsNumber, double k) {
		super(start, finish, startArg, finishArg, pointsNumber, k);
	}

	@Override
	public void calculateXAndYFunctions() {
		System.out.println("CALCULATING AXIS POLINOMS:");

		// calculating functions
		this.xFunction = Utils.calculate3degreePolinom(calculateAxisResults(Axis.X));
		this.yFunction = Utils.calculate3degreePolinom(calculateAxisResults(Axis.Y));

		System.out.println("    " + xFunction);
		System.out.println("    " + yFunction);
	}

	private double[] calculateAxisResults(Axis axis) {
		// receiving axis data from getAxisData
		double[] axisData = getAxisData(axis);

		// getting k
		double k = this.routeParameters[0];

		/*
		 * the array should contain in places 2,3 the normalized derivative of the axis
		 * function at 0 and 1. thus, they should be multiplied by K.
		 */
		axisData[2] *= k;
		axisData[3] *= k;

		return axisData;
	}

}
