package beothorn.fingerball;

import beothorn.labs.core.RectanglePixels;

public interface GraphicsBall {

	RectanglePixels getRectangle();
	void setRotation(float angle);
	void setLocation(int x, int y);
	float getAngle();

}
