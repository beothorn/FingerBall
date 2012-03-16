package beothorn.fingerball;


public class MetersToPixels {

	private final float proportionX;
	private final float proportionY;

	public MetersToPixels(DimensionPixels pixels,DimensionMeters meters) {
		proportionX = pixels.width / meters.width;
		proportionY = pixels.height / meters.height;
	}

	public PointMeters pixelsToMeters(PointPixels point) {
		float metersX = point.x / proportionX;
		float metersY = point.y / proportionY;
		return new PointMeters(metersX, metersY);
	}

	public PointPixels metersToPixels(PointMeters point) {
		int metersX = (int) (point.x * proportionX);
		int metersY = (int) (point.y * proportionY);
		return new PointPixels(metersX, metersY);
	}

}
