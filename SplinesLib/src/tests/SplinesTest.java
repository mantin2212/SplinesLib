package tests;

import routes.routes.RouteFunctionsProvider;
import routes.routes.RouteProvider;
import routes.routes.SplineFunctionsProvider;
import routes.synchronizing.MaxSpeedsFactory;
import routes.synchronizing.SpeedProviderFactory;
import routes.utils.Position2D;
import routes.utils.RoutePointInfo;

public class SplinesTest {

	public static void main(String[] args) {
		RouteFunctionsProvider functionProvider = new SplineFunctionsProvider(new Position2D(0, 0, 0),
				new Position2D(0, 1, 0), 100);
		RouteProvider provider = new RouteProvider(functionProvider);

		RoutePointInfo[] routeInfo = provider.getRoute(100);

		SpeedProviderFactory factory = new MaxSpeedsFactory(10, 1, 0.1);
		factory.getSpeedProvider(routeInfo);
	}

}
