package beothorn.labs.core.fingerball;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.graphics.GraphicsBall;
import beothorn.labs.core.fingerball.physics.PhysiscalBall;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;

public class Ball {
	
	private PhysiscalBall physicalBall;
	private GraphicsBall graphicsBall;
	private MetersToPixelsConverter metersToPixelsConverter;
	
	public Ball(final PhysiscalBall physicalBall,final GraphicsBall graphicsBall, Input input, final MetersToPixelsConverter metersToPixelsConverter) {
		this.physicalBall = physicalBall;
		this.graphicsBall = graphicsBall;
		this.metersToPixelsConverter = metersToPixelsConverter;
		InputListener ballInputListener = new BallInputListener(metersToPixelsConverter, physicalBall,graphicsBall);
		input.setListener(ballInputListener);
	}

	public void update() {
		propagatePhycicalChangesToGraphics();
	}

	private void propagatePhycicalChangesToGraphics() {
		Vec2 position = physicalBall.getPosition();
		PointMeters bodyPosition = new PointMeters(position.x, position.y);
		graphicsBall.setRotation(physicalBall.getAngle());
		PointPixels pixelPosition = metersToPixelsConverter.metersToPixels(bodyPosition);
		int x = pixelPosition.x;
		int y = pixelPosition.y;
		graphicsBall.setLocation(x, y);
	}
}
