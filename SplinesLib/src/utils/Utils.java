package utils;

import java.util.function.Function;

import functions.Polynom;

public class Utils {

	/**
	 * the function calculates and returns a polynomial function of degree 3
	 * (as^3+vs^2+cs+d function) according to a result set received
	 * 
	 * @param results:
	 *            should contain 4 elements, in that order: the value of the
	 *            function at 0, the value of the function at 1, the derivative of
	 *            the function at 0, and the derivative of the function at 1
	 * @return: the polynomial function which should fulfill the conditions above
	 */
	public static Polynom calculate3degreePolinom(double[] results) {
		// calculating as^3+vs^2+cs+d function

		double totalDiff = results[1] - results[0];

		double a = results[2] + results[3] - 2 * totalDiff;

		double b = 3 * totalDiff - 2 * results[2] - results[3];

		double c = results[2];

		double d = results[0];

		return new Polynom(d, c, b, a);
	}

	/**
	 * the function approximates the definitive integral of a received function, in
	 * a specific interval
	 * 
	 * @param function:
	 *            the function which should be integrated
	 * @param start:
	 *            the left limit of the interval
	 * @param finish:
	 *            the right limit of the interval
	 * @param n:
	 *            the number of rectangles the function uses to approximate the
	 *            area. the width of each rectangle should be 1/n.
	 * @return: the area between the function and the X axis, between the values of
	 *          start and finish.
	 */
	public static double calculateIntegral(Function<Double, Double> function, double start, double finish,
			double step) {
		System.out.println("INTEGRATING " + function);
		double result = 0;

		for (double x = start; x <= finish; x += step) {
			result += step * function.apply(x);
		}
		System.out.println(result);
		return result;
	}
}
