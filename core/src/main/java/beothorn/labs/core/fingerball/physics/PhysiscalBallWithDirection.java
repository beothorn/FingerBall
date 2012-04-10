package beothorn.labs.core.fingerball.physics;

import org.jbox2d.common.Vec2;

public interface PhysiscalBallWithDirection extends PhysicalBody{

	void applyForce(Vec2 force);
}

