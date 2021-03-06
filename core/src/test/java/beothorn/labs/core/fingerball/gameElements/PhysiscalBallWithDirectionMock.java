package beothorn.labs.core.fingerball.gameElements;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.physics.PhysiscalBallWithDirection;

class PhysiscalBallWithDirectionMock implements PhysiscalBallWithDirection{

	private StringBuffer operations;

	public PhysiscalBallWithDirectionMock() {
		operations = new StringBuffer();
	}
	
	public String getOperations() { 
		return operations.toString();
	}

	@Override
	public void applyForce(Vec2 force) {
		if(!operations.toString().isEmpty()){
			operations.append("\n");
		}
		operations.append("Applied force"+ force.toString());
	}

	@Override
	public Vec2 getPosition() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float getAngle() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
