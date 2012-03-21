package beothorn.fingerball;

import org.jbox2d.common.Vec2;

import playn.core.ImageLayer;

public class GameElement {

	private final ImageLayer imageLayer;
	private final PhysicBody physicBody;
	private final MetersToPixelsConverter metersToPixelsConverter;

	public GameElement(ImageLayer imageLayer, PhysicBody physicBody,MetersToPixelsConverter metersToPixelsConverter) {
		this.imageLayer = imageLayer;
		this.physicBody = physicBody;
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

	public float getPhisicalX() {
		return physicBody.getPosition().x;
	}

	public float getPhisicalY() {
		return physicBody.getPosition().y;
	}
}
