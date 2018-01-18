package routes.synchronizing;

import routes.RoutePointData;
import utils.Utils;

public class DriveMaxSpeeds extends RouteSynchronizer {

	private double maxVelocity;
	private double maxAcceleration;

	private double[] maxSpeeds;

	public DriveMaxSpeeds(RoutePointData[] route, double robotWidth, double maxVelocity, double maxAcceleration) {
		super(route, robotWidth);

		this.maxVelocity = maxVelocity;
		this.maxAcceleration = maxAcceleration;

		initializeSpeeds();
	}

	public void initializeSpeeds() {

		maxSpeeds = new double[routeData.length];

		for (int i = 0; i < maxSpeeds.length; i++)
			maxSpeeds[i] = Math.min(maxVelocity, getMaxSpeed(routeData[i].getRadius()));

		fixAcceleration();
	}

	private double getMaxSpeed(double radius) {

		double absoluteRadius = Math.abs(radius);

		return absoluteRadius / (absoluteRadius + robotWidth / 2) * maxVelocity;

	}

	private void fixAcceleration() {
		maxSpeeds[0] = 0;

		double distance;

		for (int k = 1; k <= maxSpeeds.length; k++) {
			distance = routeData[k].getDistance();

			maxSpeeds[k] = Math.min(maxSpeeds[k],
					Utils.getSpeedWithConstantAcceleration(maxAcceleration, maxSpeeds[k - 1], distance));
		}

		maxSpeeds[maxSpeeds.length - 1] = 0;

		for (int k = maxSpeeds.length - 1; k >= 0; k--) {
			distance = routeData[k].getDistance();

			maxSpeeds[k] = Math.min(maxSpeeds[k],
					Utils.getSpeedWithConstantAcceleration(-maxAcceleration, maxSpeeds[k + 1], distance));
		}
	}

	@Override
	protected double getTime(int k) {
		/*
		 * if (k == 0) return 0; else {
		 * 
		 * if (getAcceleration(k) == 0) return routeData[k].getDistance() /
		 * maxSpeeds[k]; else return (maxSpeeds[k] - maxSpeeds[k - 1]) /
		 * getAcceleration(k); }
		 */
		// TODO implement this method
		return 0;
	}

	@Override
	protected double getLinearSpeed(int k) {
		return maxSpeeds[k];
	}

}
