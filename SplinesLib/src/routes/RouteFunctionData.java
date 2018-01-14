package routes;

public class RouteFunctionData {

	private double functionAt0;
	private double functionAt1;

	private double derivativeAt0;
	private double derivativeAt1;

	public RouteFunctionData(double functionAt0, double functionAt1, double derivativeAt0, double derivativeAt1) {

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
