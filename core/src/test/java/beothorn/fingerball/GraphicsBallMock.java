package beothorn.fingerball;

import beothorn.labs.core.RectanglePixels;

class GraphicsBallMock implements GraphicsBall {

	private RectanglePixels rectanglePixels;

	public GraphicsBallMock(final PointPixels pointPixels, DimensionPixels dimensionPixels) {
		rectanglePixels = new RectanglePixels(pointPixels, dimensionPixels);
	}

	@Override
	public RectanglePixels getRectangle() {
		return rectanglePixels;
	}
}
