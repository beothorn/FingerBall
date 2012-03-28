package beothorn.fingerball;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class PhysicBodyImpl implements PhysicalBody{

	private final Body body;

	public PhysicBodyImpl(Body body) {
		this.body = body;
	}

	@Override
	public Vec2 getPosition() {
		Vec2 position = body.getPosition();
		return position;
	}

	@Override
	public float getAngle() {
		return body.getAngle();
	}

	@Override
	public void applyLinearImpulse(Vec2 impulseForce) {
		body.applyLinearImpulse(impulseForce, getPosition());
	}

	@Override
	public void applyAngularImpulse(float impulse) {
		body.applyAngularImpulse(impulse);
	}

}
