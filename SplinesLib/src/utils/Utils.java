package utils;

import java.util.function.Function;

public class Utils {
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
