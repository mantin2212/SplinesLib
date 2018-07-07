package routes.synchronizing;

import routes.utils.RoutePointInfo;

public interface SpeedProviderFactory {

	abstract RouteSpeedProvider getSpeedProvider(RoutePointInfo[] routeInfo);
}
