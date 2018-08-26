package routes.synchronizing;

import routes.utils.RoutePointInfo;

/**
 * this interface is a factory which supplies {@link RouteSpeedProvider}
 * objects.
 *
 * @author noam mantin
 * @see RouteSpeedProvider
 */
public interface SpeedProviderFactory {

	/**
	 * returns a speed provider according to a given route information.
	 *
	 * @param routeInfo
	 *            the route to which the returned {@link RouteSpeedProvider} object
	 *            should refer.
	 * @return a {@link RouteSpeedProvider} object which activates on the given
	 *         route.
	 * @see RouteSpeedProvider
	 */
	abstract RouteSpeedProvider getSpeedProvider(RoutePointInfo[] routeInfo);
}
