package beothorn.fingerball;

import org.jbox2d.common.Vec2;

public interface PhysicBody {

	public Vec2 getPosition();
	public float getAngle();
	public void applyLinearImpulse(Vec2 impulseForce);
	public void applyAngularImpulse(float impulse);
}
