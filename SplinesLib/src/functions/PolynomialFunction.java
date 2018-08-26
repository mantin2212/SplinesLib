package functions;

/**
 * this class describes a polynomial function- a function of the form: f(x) =
 * a0*x^0+a1*x^1...+an*x^n. the function is presented as an array of real
 * coefficients- a0...an.
 *
 * @author noam mantin
 */
public class PolynomialFunction extends DifferentiableFunction {

	// the Coefficients of the polynomial function
	private double[] elements;

	/**
	 * creates a new polynomial function with given coefficients. the result should
	 * be the function: f(x) = elements[0]*x^0+elements[1]*x^1...
	 *
	 * @param elements
	 */
	public PolynomialFunction(double... elements) {
		this.elements = elements;
	}

	/*
	 * (non-Javadoc) the function calculates and returns the value of the polynomial
	 * function at a specific x.
	 */
	@Override
	public Double apply(Double x) {

		double result = 0;

		for (int i = 0; i < elements.length; i++) {
			result += elements[i] * Math.pow(x, i);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see functions.DifferentiableFunction#getDerivative()
	 */
	@Override
	public PolynomialFunction getDerivative() {
		double[] diffElements = new double[elements.length - 1];

		for (int i = 0; i < diffElements.length; i++) {
			diffElements[i] = (i + 1) * elements[i + 1];
		}

		PolynomialFunction result = new PolynomialFunction(diffElements);

		return result;
	}

	@Override
	public String toString() {
		String str = "";

		for (int i = elements.length - 1; i >= 2; i--) {
			str = str + elements[i] + "x^" + i + "+";
		}

		str += elements[1] + "x+" + elements[0];

		return str;
	}
}