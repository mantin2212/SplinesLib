package functions;

public class PolynomialFunction extends DifferentiableFunction {

	// the Coefficients of the polynomial function
	private double[] elements;

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
