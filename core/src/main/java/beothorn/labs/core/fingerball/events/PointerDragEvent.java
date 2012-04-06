package beothorn.labs.core.fingerball.events;

import playn.core.Pointer.Event;
import beothorn.labs.core.fingerball.units.PointPixels;


public class PointerDragEvent implements GameEvent {

	private PointPixels position;

	public PointerDragEvent(Event event) {
		position = new PointPixels((int)event.x(), (int)event.y());
	}

	@Override
	public void accept(GameEventVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return position.toString();
	}
}
