package routes.synchronizing;

import routes.RouteDataPoint;

/**
 * the class helps to synchronize a robot's movement with a route. it has an
 * abstract method- getlinearspeed, which should present the wanted speed at any
 * point of the route.
 * 
 * @author noam mantin
 */
public abstract class RouteSynchronizer {

	// the width of the robot
	protected double robotWidth;

	// the needed information about the route
	protected RouteDataPoint[] routeData;

	/**
	 * the enum describes a side of the robot- LEFT or RIGHT
	 * 
	 * @author noam mantin
	 */
	public enum Side {
		RIGHT, LEFT
	}

	/**
	 * builds a new {@link RouteSynchronizer} object, with received data.
	 * 
	 * @param route
	 *            the route with which the robot needs to synchronize
	 * @param robotWidth
	 *            the width of the specific robot
	 */
	public RouteSynchronizer(RouteDataPoint[] route, double robotWidth) {

		this.robotWidth = robotWidth;

		routeData = route;
	}

	/**
	 * calculates and returns the time that might pass between two specific points
	 * 
	 * @param index
	 *            the index of the second of the two points
	 * 
	 * @return the time it might take for the robot to move between the two points
	 */
	protected double getTime(int index) {

		if (index < 0 || index >= routeData.length) {// illegal index

			System.out.println("ERROR");
			return 0;

		} else if (index == 0)// the route hasn't started yet
			return 0;

		else {

			// calculating the average speed between the points
			double v0 = getLinearSpeed(index - 1);
			double v1 = getLinearSpeed(index);

			double avgSpeed = (v0 + v1) / 2;

			// calculating the distance between the points
			double distance = routeData[index].getDistance();

			// returning the time according to: d=tv
			return distance / avgSpeed;
		}
	}

	/**
	 * should return the wanted linear velocity at a certain point
	 * 
	 * @param index
	 *            the index of the point
	 * @return the linear velocity
	 */
	protected abstract double getLinearSpeed(int index);

	/**
	 * returns the speed of a received side of the robot at a certain time
	 * 
	 * @param side
	 *            the relevant side of the robot
	 * @param time
	 *            the time since the robot started following the route
	 * @return either the right or the left speed at the received time
	 */
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

	/**
	 * returns the speed of the right side of the robot, at a certain time
	 * 
	 * @param time
	 *            the time since the robot started following the route
	 * @return the right speed of the robot at the moment
	 */
	private double getRightSpeed(double time) {
		// casting from time to an index on the route
		int index = getIndex(time);

		/*
		 * calculating the radius of the robot movement, and the linear speed, at the
		 * certain point
		 */
		double radius = routeData[index].getRotationRadius();
		double linearSpeed = getLinearSpeed(index);

		if (radius == 0)
			/*
			 * the robot drives straight, and thus the right speed equals to the linear
			 * speed
			 */
			return linearSpeed;
		else
			/*
			 * returning the left speed, that equals to ((r-w/2)/r)*v (r- the radius, v- the
			 * linear speed, w- the width of the robot
			 */
			return ((radius - robotWidth / 2) / radius) * linearSpeed;
	}

	/**
	 * returns the speed of the left side of the robot, at a certain time
	 * 
	 * @param time
	 *            the time since the robot started following the route
	 * @return the left speed of the robot at the moment
	 */
	private double getLeftSpeed(double time) {
		// casting from time to an index on the route
		int index = getIndex(time);

		/*
		 * calculating the radius of the robot movement, and the linear speed, at the
		 * certain point
		 */
		double radius = routeData[index].getRotationRadius();
		double linearSpeed = getLinearSpeed(index);

		if (radius == 0)
			/*
			 * the robot drives straight, and thus the left speed equals to the linear speed
			 */
			return linearSpeed;
		else
			/*
			 * returning the left speed, that equals to ((r+w/2)/r)*v (r- the radius, v- the
			 * linear speed, w- the width of the robot
			 */
			return ((radius + robotWidth / 2) / radius) * linearSpeed;
	}

	/**
	 * returns the index of the point on the route where the robot should be, at a
	 * certain time after it started
	 * 
	 * @param time
	 *            the time since the robot started following the route
	 * @return the index, where the route should be after the received time
	 */
	private int getIndex(double time) {
		if (time < 0) {
			// illegal time
			System.out.println("ERROR");
			return 0;
		} else {

			// the total time until a certain point
			double totalTime = 0;

			for (int i = 0; i < routeData.length; i++) {
				// updating the total time
				totalTime += getTime(i);
				// checking whether the total time passed the received time or not
				if (totalTime >= time)
					return i;
			}
			System.out.println("INDEX DOESN'T EXIST");
			return 0;
		}
	}

	public boolean isFinished(double time) {

		double totalTime = 0;

		for (int i = 0; i < routeData.length; i++)
			totalTime += getTime(i);

		return (time >= totalTime);
	}
}