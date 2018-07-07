package functions;

import java.util.function.Function;

/**
 * @author noam mantin the class describes a differentiable function which
 *         receives and returns real numbers. it has two functions, excluding
 *         apply, that return the derivative of the function
 */
public abstract class DifferentiableFunction implements Function<Double, Double> {

	/**
	 * the function should calculate and return the first derivative of the
	 * described function
	 * 
	 * @return: the derivative
	 */
	public abstract DifferentiableFunction getDerivative();

	/**
	 * the function calculates and returns the derivative of the described function,
	 * of a received degree, by differentiating it again and again.
	 * 
	 * @param index:
	 *            the degree of the wanted derivative
	 * @return: the index'th derivative of the function.
	 */
	public DifferentiableFunction getDerivative(int index) {
		if (index == 0)
			return this;
		else if (index < 0) {
			// illegal index
			System.out.println("ERROR");
			return null;
		} else
			return getDerivative(index - 1).getDerivative();
	}
}
