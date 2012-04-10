package beothorn.labs.core.fingerball;

import playn.core.Pointer.Event;

class PointerEventMock implements Event {

	@Override
	public float x() {
		return 0;
	}

	@Override
	public float y() {
		return 0;
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
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public boolean isTouch() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
