package beothorn.labs.core.fingerball.graphics;

import beothorn.labs.core.fingerball.units.RectanglePixels;

public interface GraphicsBall {

	RectanglePixels getRectangle();
	void setRotation(float angle);
	void setLocation(int x, int y);
	float getAngle();

}
