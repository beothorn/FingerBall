package beothorn.fingerball.graphics;

import beothorn.fingerball.units.RectanglePixels;

public interface GraphicsBall {

	RectanglePixels getRectangle();
	void setRotation(float angle);
	void setLocation(int x, int y);
	float getAngle();

}
