package beothorn.labs.core.fingerball;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.physics.PhysiscalBall;
import beothorn.labs.core.fingerball.units.PointMeters;

class PhysiscalBallMock implements PhysiscalBall {

	String operations = "";
	private Vec2 position = new Vec2();
	private int rotation;
	public String getOperations() {
		return operations;
	}

	@Override
	public void kickAt(PointMeters kickPhysical) {
		if(!operations.isEmpty()){
			operations += "\n";
		}
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

	@Override
	public float getAngle() {
		return rotation;
	}

	@Override
	public void longKickAt(PointMeters kickPhysical) {
		operations += "\nKick force increased";
	}

}
