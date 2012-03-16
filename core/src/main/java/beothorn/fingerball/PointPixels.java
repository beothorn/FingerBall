package beothorn.fingerball;
import pythagoras.i.Point;


@SuppressWarnings("serial")
public class PointPixels extends Point {

	public PointPixels(int x, int y) {
		super(x,y);
	}

	@Override
	public String toString() {
		return "("+x+"px,"+y+"px)";
	}
}
