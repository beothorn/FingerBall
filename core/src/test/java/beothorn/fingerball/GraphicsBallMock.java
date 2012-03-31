package beothorn.fingerball;

import beothorn.labs.core.RectanglePixels;

class GraphicsBallMock implements GraphicsBall {

	private RectanglePixels rectanglePixels;
	private float angle;

	public GraphicsBallMock(final PointPixels pointPixels, DimensionPixels dimensionPixels) {
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
}
