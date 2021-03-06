package beothorn.labs.core.fingerball.gameElements;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.physics.PhysiscalClickableBall;
import beothorn.labs.core.fingerball.units.PointMeters;

class PhysiscalClickableBallMock implements PhysiscalClickableBall {

	String operations = "";
	private Vec2 position = new Vec2();
	private int rotation;
	
	public String getOperations() {
		return operations;
	}

	@Override
	public void kickAt(PointMeters kickPhysical) {
		String operation = "Kicked at " + kickPhysical;
		addOperation(operation);
	}

	private void addOperation(String operation) {
		if(!operations.isEmpty()){
			operations += "\n";
		}
		operations += operation;
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