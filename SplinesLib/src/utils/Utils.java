package utils;

import java.util.function.Function;

import functions.PolynomialFunction;

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
	public static PolynomialFunction calculate3degreePolinom(double valueAt0, double valueAt1, double derivativeAt0, double derivativeAt1) {
		// calculating as^3+vs^2+cs+d function

		double totalDiff = valueAt1 - valueAt0;

		double a = derivativeAt0 + derivativeAt1 - 2 * totalDiff;

		double b = 3 * totalDiff - 2 * derivativeAt0 - derivativeAt1;

		double c = derivativeAt0;

		double d = valueAt0;

		return new PolynomialFunction(d, c, b, a);
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

	public static double getSpeedWithConstantAcceleration(double acceleration, double initSpeed, double distance) {
		return Math.sqrt(initSpeed * initSpeed + 2 * acceleration * distance);
	}

}
