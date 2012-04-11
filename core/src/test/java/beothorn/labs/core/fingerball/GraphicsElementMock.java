package beothorn.labs.core.fingerball;

import beothorn.labs.core.fingerball.graphics.GraphicsElement;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;


public class GraphicsElementMock implements GraphicsElement {

	private RectanglePixels rectanglePixels;
	private float angle;

	public GraphicsElementMock(final PointPixels pointPixels, DimensionPixels dimensionPixels) {
		rectanglePixels = new RectanglePixels(pointPixels, dimensionPixels); 
	}

	@Override
	public RectanglePixels getRectangle() {
		return rectanglePixels;
	}

	@Override
	public void setRotation(float angle) {
		this.angle = angle;
	}

	@Override
	public void setLocation(int x, int y) {
		rectanglePixels.setLocation(x, y);
	}

	public float getAngle() {
		return angle;
	}

	@Override
	public float getOriginX() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float getOriginY() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}
}
