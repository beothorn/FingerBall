package beothorn.labs.core.fingerball;

import beothorn.labs.core.fingerball.graphics.GraphicsBall;
import beothorn.labs.core.fingerball.physics.PhysiscalBall;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

final class BallInputListener implements InputListener {
	private final MetersToPixelsConverter metersToPixelsConverter;
	private final PhysiscalBall physicalBall;
	private final GraphicsBall graphicsBall;

	BallInputListener(MetersToPixelsConverter metersToPixelsConverter,PhysiscalBall physicalBall, GraphicsBall graphicsBall) {
		this.metersToPixelsConverter = metersToPixelsConverter;
		this.physicalBall = physicalBall;
		this.graphicsBall = graphicsBall;
	}

	@Override
	public void kickAt(PointPixels kick) {
		if(!isPointInsideBall(graphicsBall,kick)){
			return;
		}
		PointMeters kickPhysical = metersToPixelsConverter.pixelsToMeters(kick);
		physicalBall.kickAt(kickPhysical);
	}

	@Override
	public void longKickAt(PointPixels kick) {
		if(!isPointInsideBall(graphicsBall,kick)){
			return;
		}
		PointMeters kickPhysical = metersToPixelsConverter.pixelsToMeters(kick);
		physicalBall.longKickAt(kickPhysical);
	}

	private boolean isPointInsideBall(final GraphicsBall graphicsBall,PointPixels kick) {
		RectanglePixels rectangle =  graphicsBall.getRectangle();
		boolean pointIsNotInsideBall = rectangle.contains(kick);
		return pointIsNotInsideBall;
	}
}