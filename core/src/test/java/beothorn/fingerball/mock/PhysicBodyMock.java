package beothorn.fingerball.mock;

import org.jbox2d.common.Vec2;

import beothorn.fingerball.PhysicBody;

public class PhysicBodyMock implements PhysicBody {

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

}
