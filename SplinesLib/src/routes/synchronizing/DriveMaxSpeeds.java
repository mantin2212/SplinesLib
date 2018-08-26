package routes.synchronizing;

import routes.utils.RoutePointInfo;
import utils.Utils;

public class DriveMaxSpeeds implements RouteSpeedProvider {

	private RoutePointInfo[] routeInfo;
	private double robotWidth;

	private double maxVelocity;
	private double maxAcceleration;

	private double[] maxSpeeds;

	public DriveMaxSpeeds(RoutePointInfo[] routeInfo, double robotWidth, double maxVelocity, double maxAcceleration) {

		this.routeInfo = routeInfo;
		this.robotWidth = robotWidth;

		this.maxVelocity = maxVelocity;
		this.maxAcceleration = maxAcceleration;

		initializeSpeeds();
	}

	@Override
	public double getLinearSpeed(int index) {
		return maxSpeeds[index];
	}

	public void initializeSpeeds() {

		maxSpeeds = new double[routeInfo.length];

		for (int i = 0; i < maxSpeeds.length; i++)
			maxSpeeds[i] = Math.min(maxVelocity, getMaxSpeed(routeInfo[i].getRotationRadius()));

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

		for (int k = 1; k < maxSpeeds.length; k++) {
			distance = routeInfo[k].getDistance();

			maxSpeeds[k] = Math.min(maxSpeeds[k],
					Utils.getSpeedWithConstantAcceleration(maxAcceleration, maxSpeeds[k - 1], distance));
		}
	}

	private void fixNegativeAcceleration() {
		double distance;

		maxSpeeds[maxSpeeds.length - 1] = 0;

		for (int k = maxSpeeds.length - 2; k >= 0; k--) {
			distance = routeInfo[k].getDistance();

			maxSpeeds[k] = Math.min(maxSpeeds[k],
					Utils.getSpeedWithConstantAcceleration(maxAcceleration, maxSpeeds[k + 1], distance));
		}
	}
}