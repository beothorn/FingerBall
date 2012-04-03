package beothorn.labs.core.fingerball.physics;

import org.jbox2d.common.Vec2;

public interface PhysicalBody {

	public Vec2 getPosition();
	public float getAngle();
	public void applyLinearImpulse(Vec2 impulseForce);
	public void applyAngularImpulse(float impulse);
}
