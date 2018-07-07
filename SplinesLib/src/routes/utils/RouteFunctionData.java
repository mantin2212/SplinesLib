package routes.utils;

/**
 * the class describes the data needed to specify a 3rd degree polynomial
 * function. it contains the values of the function at x=0,1, and it's
 * derivative at x=0,1.
 * 
 * @author noam mantin
 */
public class RouteFunctionData {

	/**
	 * the values of the function at x=0,1
	 */
	private double functionAt0;
	private double functionAt1;

	/**
	 * the derivative of the function at x=0,1
	 */
	private double derivativeAt0;
	private double derivativeAt1;

	/**
	 * creates a new {@link RouteFunctionData} object, with received values
	 * 
	 * @param functionAt0
	 *            the value of the specified function at x=0
	 * @param functionAt1
	 *            the value of the specified function at x=1
	 * @param derivativeAt0
	 *            the derivative of the specified function at x=0
	 * @param derivativeAt1
	 *            the derivative of the specified function at x=1
	 */
	public RouteFunctionData(double functionAt0, double functionAt1, double derivativeAt0, double derivativeAt1) {
		//
		this.functionAt0 = functionAt0;
		this.functionAt1 = functionAt1;

		this.derivativeAt0 = derivativeAt0;
		this.derivativeAt1 = derivativeAt1;
	}

	public void setFunctionAt0(double functionAt0) {
		this.functionAt0 = functionAt0;
	}

	public void setFunctionAt1(double functionAt1) {
		this.functionAt1 = functionAt1;
	}

	public void setDerivativeAt0(double derivativeAt0) {
		this.derivativeAt0 = derivativeAt0;
	}

	public void setDerivativeAt1(double derivativeAt1) {
		this.derivativeAt1 = derivativeAt1;
	}

	public double getFunctionAt0() {
		return functionAt0;
	}

	public double getFunctionAt1() {
		return functionAt1;
	}

	public double getDerivativeAt0() {
		return derivativeAt0;
	}

	public double getDerivativeAt1() {
		return derivativeAt1;
	}
}
