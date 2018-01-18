package routes.synchronizing;

import routes.RoutePointData;

public class DriveSingleVelocity extends RouteSynchronizer {

	private double velocity;

	public DriveSingleVelocity(RoutePointData[] route, double robotWidth, double velocity) {
		super(route, robotWidth);

		this.velocity = velocity;
	}

	@Override
	protected double getLinearSpeed(int k) {
		return velocity;
	}

	protected double getTime(int k) {
		return routeData[k].getDistance() / velocity;
	}

}
