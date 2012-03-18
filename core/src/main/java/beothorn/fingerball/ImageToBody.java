package beothorn.fingerball;

import org.jbox2d.common.Vec2;

import playn.core.ImageLayer;

public class ImageToBody {

	private final ImageLayer imageLayer;
	private final PhysicBody physicBody;
	private final MetersToPixels metersToPixelsConverter;

	public ImageToBody(ImageLayer imageLayerMock, PhysicBody physicBodyMock,MetersToPixels metersToPixelsConverter) {
		this.imageLayer = imageLayerMock;
		this.physicBody = physicBodyMock;
		this.metersToPixelsConverter = metersToPixelsConverter;
	}

	public void update() {
		Vec2 position = physicBody.getPosition();
		PointMeters bodyPosition = new PointMeters(position.x, position.y);
		imageLayer.setRotation(physicBody.getAngle());
		PointPixels pixelPosition = metersToPixelsConverter.metersToPixels(bodyPosition);
		float x = pixelPosition.x;
		float y = pixelPosition.y;
		imageLayer.setTranslation(x, y);
	}

}
