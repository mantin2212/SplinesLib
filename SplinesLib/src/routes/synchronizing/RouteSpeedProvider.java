package routes.synchronizing;

/**
 * this interface describes an object which supplies speeds to each point of a
 * specific route.
 *
 * @author noam mantin
 * 
 * @see DriveMaxSpeeds
 */
public interface RouteSpeedProvider {

	/**
	 * returns the wanted speed in a specific point.
	 * 
	 * @param index
	 *            the index of the referred point.
	 * @return the linear speed an object (the robot, for example) should have when
	 *         passing in the point with the given index.
	 * 
	 * @see RouteSynchronizer
	 */
	abstract double getLinearSpeed(int index);

}
