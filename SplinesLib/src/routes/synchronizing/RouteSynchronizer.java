package routes.synchronizing;

import java.awt.geom.Point2D;

import routes.utils.RoutePointInfo;

/**
 * the class helps to synchronize a robot's movement with a route. it has an
 * abstract method- getlinearspeed, which should present the wanted speed at any
 * point of the route.
 * 
 * @author noam mantin
 */
public class RouteSynchronizer {

	private RoutePointInfo[] routeInfo;

	private RouteSpeedProvider speedProvider;
	private double[] reachingTimes;

	/**
	 * builds a new {@link RouteSynchronizer} object, with received data.
	 * 
	 * @param route
	 *            the route with which the robot needs to synchronize
	 * @param robotWidth
	 *            the width of the specific robot
	 */
	public RouteSynchronizer(SpeedProviderFactory factory, RoutePointInfo[] routeInfo) {
		this.routeInfo = routeInfo;

		this.speedProvider = factory.getSpeedProvider(routeInfo);

		initTimes();
	}

	public Point2D getPosition(double time) {

		int index = -1;

		for (int i = 0; i < reachingTimes.length; i++) {
			if (time < reachingTimes[i]) {
				index = i;
				break;
			}
		}
		if (index == -1)
			index = reachingTimes.length - 1;

		return routeInfo[index].getPosition();
	}

	public double getVelocity(double time) {
		int index = -1;

		for (int i = 0; i < reachingTimes.length; i++) {
			if (time < reachingTimes[i]) {
				index = i;
				break;
			}
		}
		if (index == -1)
			index = reachingTimes.length - 1;

		return speedProvider.getLinearSpeed(index);
	}

	private void initTimes() {

		reachingTimes = new double[routeInfo.length];

		reachingTimes[0] = 0;

		for (int i = 1; i < reachingTimes.length; i++) {
			reachingTimes[i] = reachingTimes[i - 1] + getDT(i);
		}

	}

	/**
	 * calculates and returns the time that should pass between two adjacent
	 * points
	 * 
	 * @param index
	 *            the index of the second of the two points
	 * 
	 * @return the time it should take for the robot to move between the two
	 *         points
	 */
	private double getDT(int index) {
		return routeInfo[index].getDistance() / speedProvider.getLinearSpeed(index);
	}
}