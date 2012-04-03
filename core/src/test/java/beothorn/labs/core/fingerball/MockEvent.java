package beothorn.labs.core.fingerball;

import playn.core.Pointer.Event;

public class MockEvent implements Event {

	private final float x;
	private final float y;
	private final double time;

	public MockEvent(float x,float y, double time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
	
	@Override
	public float x() {
		return x;
	}

	@Override
	public float y() {
		return y;
	}

	@Override
	public boolean getPreventDefault() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setPreventDefault(boolean preventDefault) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public double time() {
		return time;
	}

	@Override
	public boolean isTouch() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
