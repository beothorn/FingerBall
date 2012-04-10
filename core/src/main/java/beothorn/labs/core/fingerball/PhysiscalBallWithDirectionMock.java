package beothorn.labs.core.fingerball;

import org.jbox2d.common.Vec2;

public class PhysiscalBallWithDirectionMock implements PhysiscalBallWithDirection{

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

}
