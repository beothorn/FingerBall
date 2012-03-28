package beothorn.fingerball;

import org.jbox2d.common.Vec2;

class PhysiscalBallMock implements PhysiscalBall {

	String operations = "";
	private Vec2 position;
	private int rotation;
	public String getOperations() {
		return operations;
	}

	@Override
	public void kickAt(PointMeters kickPhysical) {
		operations += "Kicked at " + kickPhysical;
	}

	public void setPositionAnRotation(PointMeters newPosition, int rotation) {
		this.rotation = rotation;
		position = new Vec2(newPosition.x, newPosition.y);
	}

	@Override
	public Vec2 getPosition() {
		return position;
	}

}
