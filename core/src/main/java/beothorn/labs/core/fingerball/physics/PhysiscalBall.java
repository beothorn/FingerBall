package beothorn.labs.core.fingerball.physics;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.units.PointMeters;

public interface PhysiscalBall {

	public Vec2 getPosition();
	void kickAt(PointMeters kickPhysical);
	public float getAngle();
	public void longKickAt(PointMeters kickPhysical);

}
