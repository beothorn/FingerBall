package beothorn.labs.core.fingerball.graphics;

import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class GraphicsBallImpl implements GraphicsElement {

	private ImageLayer ballImageLayer;
	private float angle;
	private int x;
	private int y;
	private int radius;

	public GraphicsBallImpl(Image image) {
			ballImageLayer = graphics().createImageLayer(image);
			radius = image.width() / 2;
			ballImageLayer.setOrigin(radius, radius);
			graphics().rootLayer().add(ballImageLayer);
	}
	
	@Override
	public RectanglePixels getRectangle() {
		PointPixels pointPixels = new PointPixels(x-radius, y-radius);
		DimensionPixels dimensionPixels = new DimensionPixels((int)ballImageLayer.width(), (int)ballImageLayer.height());
		RectanglePixels rectanglePixels = new RectanglePixels(pointPixels, dimensionPixels);
		return rectanglePixels;
	}

	@Override
	public void setRotation(float angle) {
		this.angle = angle;
		ballImageLayer.setRotation(angle);
	}

	@Override
	public void setLocation(int x, int y) {
		this.x=x;
		this.y=y;
		ballImageLayer.setTranslation(x, y);
	}

	@Override
	public float getAngle() {
		return angle;
	}

}
