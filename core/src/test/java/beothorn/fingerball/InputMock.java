package beothorn.fingerball;

class InputMock implements Input {

	private InputListener inputListener;

	public void simulateKick(PointPixels kick) {
		inputListener.kickAt(kick);
	}

	@Override
	public void setListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

}
