package beothorn.fingerball;

import org.jbox2d.common.Vec2;

import playn.core.ImageLayer;

public class GameElement {

	private final ImageLayer imageLayer;
	private final PhysicalBody physicBody;
	private final MetersToPixelsConverter metersToPixelsConverter;

	public GameElement(ImageLayer imageLayer, PhysicalBody physicBody,MetersToPixelsConverter metersToPixelsConverter) {
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

	public void click(PointPixels clickPosition) {
		PointMeters pixelsToMeters = metersToPixelsConverter.pixelsToMeters(clickPosition);

		float deltaX = getPhisicalX() - pixelsToMeters.x;
		Vec2 impulse = new Vec2(deltaX, getPhisicalY() - pixelsToMeters.y);
		impulse.normalize();
		Vec2 impulseForce = impulse.mul(0.006f);
		physicBody.applyLinearImpulse(impulseForce);
		physicBody.applyAngularImpulse(impulseForce.x * 0.1f);
	}
}
