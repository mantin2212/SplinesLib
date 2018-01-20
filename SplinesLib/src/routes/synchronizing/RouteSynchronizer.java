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

	protected double getTime(int index) {

		if (index < 0 || index >= routeData.length) {

			System.out.println("ERROR");
			return 0;

		} else if (index == 0)
			return 0;

		else {
			double v0 = getLinearSpeed(index - 1);

			double v1 = getLinearSpeed(index);

			double distance = routeData[index].getDistance();

			double avgSpeed = (v0 + v1) / 2;

			return distance / avgSpeed;
		}
	}

	protected abstract double getLinearSpeed(int index);

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
		int index = getIndex(time);

		double radius = routeData[index].getRadius();

		return ((radius - robotWidth / 2) / radius) * getLinearSpeed(index);
	}

	private double getLeftSpeed(double time) {
		int index = getIndex(time);

		double radius = routeData[index].getRadius();

		return ((radius + robotWidth / 2) / radius) * getLinearSpeed(index);
	}

	private int getIndex(double time) {
		if (time < 0) {
			System.out.println("ERROR");
			return 0;
		} else {

			double totalTime = 0;

			for (int i = 0; i < routeData.length; i++) {
				totalTime += getTime(i);

				if (totalTime >= time)
					return i;
			}
			return 0;
		}
	}
}
