package beothorn.fingerball;

import org.jbox2d.common.Vec2;

import beothorn.fingerball.graphics.GraphicsBall;
import beothorn.fingerball.physics.PhysiscalBall;
import beothorn.fingerball.units.MetersToPixelsConverter;
import beothorn.fingerball.units.PointMeters;
import beothorn.fingerball.units.PointPixels;
import beothorn.fingerball.units.RectanglePixels;

public class Ball {
	
	private PhysiscalBall physicalBall;
	private GraphicsBall graphicsBall;
	private MetersToPixelsConverter metersToPixelsConverter;
	
	public Ball(final PhysiscalBall physicalBall,final GraphicsBall graphicsBall, Input input, final MetersToPixelsConverter metersToPixelsConverter) {
		this.physicalBall = physicalBall;
		this.graphicsBall = graphicsBall;
		this.metersToPixelsConverter = metersToPixelsConverter;
		input.setListener(new InputListener() {
			@Override
			public void kickAt(PointPixels kick) {
				RectanglePixels rectangle =  graphicsBall.getRectangle();
				if(!rectangle.contains(kick)){
					return;
				}
				PointMeters kickPhysical = metersToPixelsConverter.pixelsToMeters(kick);
				physicalBall.kickAt(kickPhysical);
			}
		});
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
