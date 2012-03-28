package beothorn.fingerball;

import org.jbox2d.common.Vec2;

public interface PhysiscalBall {

	public Vec2 getPosition();
	void kickAt(PointMeters kickPhysical);

}
