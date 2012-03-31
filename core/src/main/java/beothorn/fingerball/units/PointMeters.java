package beothorn.fingerball.units;
import pythagoras.f.Point;


@SuppressWarnings("serial")
public class PointMeters extends Point {

	public PointMeters(float x, float y) {
		super(x,y);
	}

	@Override
	public String toString() {
		return "("+x+"m,"+y+"m)";
	}
	
}
