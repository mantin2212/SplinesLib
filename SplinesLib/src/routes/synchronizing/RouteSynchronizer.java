package routes.synchronizing;

import routes.utils.RoutePointInfo;
import utils.Point;

/**
 * the class helps to synchronize a robot's movement with a route and a
 * {@link RouteSpeedProvider}. it supplies the point where the robot should
 * pass, as a function of the time passed from the beginning of the movement.
 *
 * @author noam mantin
 */
public class RouteSynchronizer {

	// the relevant route we track
	private RoutePointInfo[] routeInfo;

	/*
	 * the speed provider which supplies speed as a function of the progress on the
	 * route
	 */
	private RouteSpeedProvider speedProvider;
	/*
	 * an array containing the reaching times to each point on the route. the i'th
	 * element of the array is the time the robot should take to reach the i'th
	 * point of the route.
	 */
	private double[] reachingTimes;

	/**
	 * builds a new {@link RouteSynchronizer} object, with a received
	 * {@link SpeedProviderFactory} and a specific route.
	 *
	 * @param routeInfo
	 *            the route with which the robot needs to synchronize
	 * @param factory
	 *            the {@link SpeedProviderFactory} which describes the way to assign
	 *            a speed to a point a the route.
	 */
	public RouteSynchronizer(SpeedProviderFactory factory, RoutePointInfo[] routeInfo) {
		this.routeInfo = routeInfo;

		// getting the speed provider relevant to this route from the factory
		this.speedProvider = factory.getSpeedProvider(routeInfo);

		// initializing the reachingTimes array
		initTimes();
	}

	/**
	 * initializes the {@link #reachingTimes} array according to the speeds
	 * calculated in {@link #speedProvider} and the route's points.
	 */
	private void initTimes() {

		reachingTimes = new double[routeInfo.length];

		reachingTimes[0] = 0;

		/*
		 * setting each reaching time according to the previous one and the expected
		 * time which should pass between the two points
		 */
		for (int i = 1; i < reachingTimes.length; i++) {
			reachingTimes[i] = reachingTimes[i - 1] + getDT(i);
		}
	}

	/**
	 * returns the point where the robot should be, a certain time after it started
	 * driving on the route.
	 *
	 * @param time
	 *            the time passed since the robot started the route.
	 * @return the point on the route where the robot should be at this moment.
	 */
	public Point getPosition(double time) {

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

	/**
	 * calculates and returns the time that should pass between two adjacent points.
	 *
	 * @param index
	 *            the index of the second of the two points
	 * 
	 * @return the time it should take for the robot to move between the two points
	 */
	private double getDT(int index) {
		return routeInfo[index].getDistance() / speedProvider.getLinearSpeed(index);
	}
}