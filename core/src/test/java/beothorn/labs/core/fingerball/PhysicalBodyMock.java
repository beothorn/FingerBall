package beothorn.labs.core.fingerball;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.physics.PhysicalBody;

public class PhysicalBodyMock implements PhysicalBody {

	private Vec2 position;

	public PhysicalBodyMock(int x, int y) {
		setPosition(x, y);
	}
	
	@Override
	public Vec2 getPosition() {
		return position;
	}

	@Override
	public float getAngle() {
		return 0;
	}

	public void setPosition(float x, float y) {
		position = new Vec2(x,y);
	}

}
