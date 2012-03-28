package beothorn.fingerball.mock;

import org.jbox2d.common.Vec2;

import beothorn.fingerball.PhysicalBody;

public class PhysicBodyMock implements PhysicalBody {

	private Vec2 position;

	public PhysicBodyMock(float x, float y) {
		position = new Vec2(x,y);
	}

	@Override
	public Vec2 getPosition() {
		return position;
	}

	@Override
	public float getAngle() {
		return 0;
	}

	@Override
	public void applyLinearImpulse(Vec2 impulseForce) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void applyAngularImpulse(float impulse) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
