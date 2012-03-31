package beothorn.fingerball;

import org.jbox2d.common.Vec2;

import beothorn.fingerball.graphics.GraphicsBall;
import beothorn.fingerball.physics.PhysiscalBall;
import beothorn.fingerball.units.MetersToPixelsConverter;
import beothorn.fingerball.units.PointMeters;
import beothorn.fingerball.units.PointPixels;

public class PhysicalToGraphicsBallUpdater {

	private final PhysiscalBall physics;
	private final MetersToPixelsConverter metersToPixelsConverter;

	public PhysicalToGraphicsBallUpdater(GraphicsBall graphicalBall, PhysiscalBall physicalBall,MetersToPixelsConverter metersToPixelsConverter) {
		this.physics = physicalBall;
		this.metersToPixelsConverter = metersToPixelsConverter;
	}

	public float getPhisicalX() {
		return physics.getPosition().x;
	}

	public float getPhisicalY() {
		return physics.getPosition().y;
	}

	public void click(PointPixels clickPosition) {
		PointMeters pixelsToMeters = metersToPixelsConverter.pixelsToMeters(clickPosition);

		float deltaX = getPhisicalX() - pixelsToMeters.x;
		Vec2 impulse = new Vec2(deltaX, getPhisicalY() - pixelsToMeters.y);
		impulse.normalize();
		Vec2 impulseForce = impulse.mul(0.006f);
		physics.applyLinearImpulse(impulseForce);
		physics.applyAngularImpulse(impulseForce.x * 0.1f);
	}
}
