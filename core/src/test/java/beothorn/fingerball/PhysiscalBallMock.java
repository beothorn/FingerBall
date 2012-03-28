package beothorn.fingerball;

class PhysiscalBallMock implements PhysiscalBall {

	String operations = "";
	public String getOperations() {
		return operations;
	}

	@Override
	public void kickAt(PointMeters kickPhysical) {
		operations += "Kicked at " + kickPhysical;
	}

}
