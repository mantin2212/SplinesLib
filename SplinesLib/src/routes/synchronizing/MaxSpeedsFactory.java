package routes.synchronizing;

import routes.utils.RoutePointInfo;

public class MaxSpeedsFactory implements SpeedProviderFactory {

	private double robotWidth;

	private double maxVelocity;
	private double maxAcceleration;

	public MaxSpeedsFactory(double robotWidth, double maxVelocity, double maxAcceleration) {
		this.robotWidth = robotWidth;

		this.maxVelocity = maxVelocity;
		this.maxAcceleration = maxAcceleration;
	}

	@Override
	public RouteSpeedProvider getSpeedProvider(RoutePointInfo[] routeInfo) {
		return new DriveMaxSpeeds(routeInfo, robotWidth, maxVelocity, maxAcceleration);
	}

}
