package beothorn.labs.core.fingerball.gameElements;

import beothorn.labs.core.fingerball.units.PointPixels;

public interface VectorDrawer {

	void redraw();
	void startVector(PointPixels vectorStartScreenCoords);
	void clearVector();
	void updateVector(PointPixels vectorEndScreenCoords);

}
