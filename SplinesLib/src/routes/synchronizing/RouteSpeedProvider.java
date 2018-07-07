package routes.synchronizing;

import routes.utils.RoutePointInfo;

public interface RouteSpeedProvider {

	abstract double[] getLinearSpeeds(RoutePointInfo[] routeInfo);
}
