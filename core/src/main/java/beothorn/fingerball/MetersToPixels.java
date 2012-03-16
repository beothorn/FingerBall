package beothorn.fingerball;


public class MetersToPixels {

	private final float proportionX;
	private final float proportionY;

	public MetersToPixels(DimensionPixels pixels,DimensionMeters meters) {
		proportionX = pixels.width / meters.width;
		proportionY = pixels.height / meters.height;
	}

	public PointMeters pixelsToMeters(PointPixels pixels) {
		float metersX = pixels.x / proportionX;
		float metersY = pixels.y / proportionY;
		return new PointMeters(metersX, metersY);
	}

	public PointPixels metersToPixels(PointMeters meters) {
		int metersX = metersWidthToPixels(meters.x);
		int metersY = metersHeightToPixels(meters.y);
		return new PointPixels(metersX, metersY);
	}

	public int metersWidthToPixels(float widthInMeters) {
		return (int) (widthInMeters * proportionX);
	}

	public int metersHeightToPixels(float heightInMeters) {
		return (int) (heightInMeters * proportionY);
	}

}
