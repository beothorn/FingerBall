package beothorn.labs.core.fingerball.gameElements;

import beothorn.labs.core.fingerball.units.PointPixels;

class VectorDrawerMock implements VectorDrawer {

	@Override
	public void redraw() {
	}

	@Override
	public void startVector(PointPixels vectorStartScreenCoords) {
	}

	@Override
	public void clearVector() {
	}

	@Override
	public void updateVector(PointPixels vectorEndScreenCoords) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
