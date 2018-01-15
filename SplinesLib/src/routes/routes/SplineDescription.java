package routes.routes;

import functions.DifferentiableFunction;
import routes.ArgPoint;
import routes.RouteFunctionData;
import utils.Utils;

public class SplineDescription extends RouteDescription {

	private double k;

	public SplineDescription(ArgPoint start, ArgPoint finish, double k) {
		super(start, finish);
		this.k = k;
	}

	private void updateDerivatives(RouteFunctionData data) {

		data.setDerivativeAt0(data.getDerivativeAt0() * k);
		data.setDerivativeAt1(data.getDerivativeAt1() * k);
	}

	@Override
	public DifferentiableFunction getFunction(Axis axis) {

		RouteFunctionData result = getAxisData(axis);

		updateDerivatives(result);

		return Utils.calculate3degreePolinom(result);
	}
}
