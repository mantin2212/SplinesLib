package routes.synchronizing;

import routes.RouteDataPoint;
import utils.Utils;

public class DriveMaxSpeeds extends RouteSynchronizer {

	private double maxVelocity;
	private double maxAcceleration;

	private double[] maxSpeeds;

	public DriveMaxSpeeds(RouteDataPoint[] route, double robotWidth, double maxVelocity, double maxAcceleration) {
		super(route, robotWidth);

		this.maxVelocity = maxVelocity;
		this.maxAcceleration = maxAcceleration;

		initializeSpeeds();
	}

	@Override
	protected double getLinearSpeed(int index) {
		return maxSpeeds[index];
	}

	public void initializeSpeeds() {

		maxSpeeds = new double[routeData.length];

		for (int i = 0; i < maxSpeeds.length; i++)
			maxSpeeds[i] = Math.min(maxVelocity, getMaxSpeed(routeData[i].getRadius()));

		fixAcceleration();
	}

	private double getMaxSpeed(double radius) {
		if (radius == 0)
			return maxVelocity;
		else {
			double absoluteRadius = Math.abs(radius);

			return absoluteRadius / (absoluteRadius + robotWidth / 2) * maxVelocity;
		}
	}

	private void fixAcceleration() {
		fixPositiveAcceleration();
		fixNegativeAcceleration();
	}

	private void fixPositiveAcceleration() {
		maxSpeeds[0] = 0;

		double distance;

		for (int k = 1; k <= maxSpeeds.length; k++) {
			distance = routeData[k].getDistance();

			maxSpeeds[k] = Math.min(maxSpeeds[k],
					Utils.getSpeedWithConstantAcceleration(maxAcceleration, maxSpeeds[k - 1], distance));
		}
	}

	private void fixNegativeAcceleration() {
		double distance;

		maxSpeeds[maxSpeeds.length - 1] = 0;

		for (int k = maxSpeeds.length - 1; k >= 0; k--) {
			distance = routeData[k].getDistance();

			maxSpeeds[k] = Math.min(maxSpeeds[k],
					Utils.getSpeedWithConstantAcceleration(-maxAcceleration, maxSpeeds[k + 1], distance));
		}
	}
}