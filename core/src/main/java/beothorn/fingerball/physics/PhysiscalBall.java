package beothorn.fingerball.physics;

import org.jbox2d.common.Vec2;

import beothorn.fingerball.units.PointMeters;

public interface PhysiscalBall {

	public Vec2 getPosition();
	void kickAt(PointMeters kickPhysical);
	public float getAngle();
	public void applyLinearImpulse(Vec2 impulseForce);
	public void applyAngularImpulse(float f);

}
