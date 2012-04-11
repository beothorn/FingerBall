package beothorn.labs.core.fingerball.graphics;

import static playn.core.PlayN.graphics;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;
import playn.core.Image;
import playn.core.ImageLayer;

public class GraphicsElementImpl implements GraphicsElement{

	private ImageLayer imageLayer;
	private float angle;
	private int x;
	private int y;
	
	public GraphicsElementImpl(Image resource,int x,int y) {
		imageLayer = graphics().createImageLayer(resource);
		graphics().rootLayer().add(imageLayer);
		setLocation(x, y);
	}

	@Override
	public RectanglePixels getRectangle() {
		PointPixels pointPixels = new PointPixels(x, y);
		DimensionPixels dimensionPixels = new DimensionPixels((int)imageLayer.width(), (int)imageLayer.height());
		RectanglePixels rectanglePixels = new RectanglePixels(pointPixels, dimensionPixels);
		return rectanglePixels;
	}

	@Override
	public void setRotation(float angle) {
		this.angle = angle;
		imageLayer.setRotation(angle);
	}

	@Override
	public void setLocation(int x, int y) {
		this.x=x;
		this.y=y;
		imageLayer.setTranslation(x, y);
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public float getOriginX() {
		return imageLayer.originX();
	}

	@Override
	public float getOriginY() {
		return imageLayer.originY();
	}

}
