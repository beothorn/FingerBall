package beothorn.fingerball.mock;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import pythagoras.f.Transform;

public class ImageLayerMock implements ImageLayer{

	private float angle;
	private float x;
	private float y;

	@Override
	public float width() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float height() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float scaledWidth() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float scaledHeight() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void destroy() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public boolean destroyed() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public GroupLayer parent() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Transform transform() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public boolean visible() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setVisible(boolean visible) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float alpha() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setAlpha(float alpha) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float originX() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float originY() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setOrigin(float x, float y) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public float depth() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setDepth(float depth) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setTranslation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setScale(float x) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setScale(float x, float y) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setRotation(float angle) {
		this.angle = angle;
	}

	@Override
	public void clearHeight() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void clearSourceRect() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void clearWidth() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Image image() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setHeight(float height) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setImage(Image image) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setRepeatX(boolean repeat) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setRepeatY(boolean repeat) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setSourceRect(float sx, float sy, float sw, float sh) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setWidth(float width) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void setSize(float width, float height) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public Object getNewPositionAsString() {
		return "("+x+","+y+")";
	}

	public String getAngleAsString() {
		return ""+angle;
	}

}
