package routes.synchronizing;

import routes.RoutePointData;

public abstract class RouteSynchronizer {

	protected double robotWidth;

	protected RoutePointData[] routeData;

	public enum Side {
		RIGHT, LEFT
	}

	public RouteSynchronizer(RoutePointData[] route, double robotWidth) {
		this.robotWidth = robotWidth;

		routeData = route;
	}

	protected abstract double getTime(int k);

	protected abstract double getLinearSpeed(int k);

	public double getSpeed(Side side, double time) {
		switch (side) {
		case RIGHT:
			return getRightSpeed(time);
		case LEFT:
			return getLeftSpeed(time);
		default:
			System.out.println("ERROR");
			return 0;
		}
	}

	private double getRightSpeed(double time) {
		int k = getK(time);

		double radius = routeData[k].getRadius();

		return ((radius - robotWidth / 2) / radius) * getLinearSpeed(k);
	}

	private double getLeftSpeed(double time) {
		int k = getK(time);

		double radius = routeData[k].getRadius();

		return ((radius + robotWidth / 2) / radius) * getLinearSpeed(k);
	}

	private double getTotalTime(int k) {

		double totalTime = 0;

		for (int i = 0; i <= k; i++)
			totalTime += getTime(k);

		return totalTime;
	}

	private int getK(double time) {
		if (time < 0) {
			System.out.println("ERROR");
			return 0;
		} else {

			double totalTime;

			for (int k = 0; k < routeData.length; k++) {
				totalTime = getTotalTime(k);
				if (totalTime >= time)
					return k;
			}
			return 0;
		}
	}
}
