package beothorn.fingerball;

import beothorn.labs.core.fingerball.Input;
import beothorn.labs.core.fingerball.InputListener;
import beothorn.labs.core.fingerball.units.PointPixels;

class InputMock implements Input {

	private InputListener inputListener;

	public void simulateKick(PointPixels kick) {
		inputListener.kickAt(kick);
	}

	@Override
	public void setListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	public void simulateLongKick(PointPixels kick) {
		inputListener.kickAt(kick);
		inputListener.longKickAt(kick);
	}

}
