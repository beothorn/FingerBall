package beothorn.labs.core.fingerball.events;

import beothorn.labs.core.fingerball.units.PointPixels;
import playn.core.Pointer.Event;

public class PointerStartEvent implements GameEvent {

	private final Event event;
	private PointPixels position;

	public PointerStartEvent(Event event) {
		this.event = event;
		position = new PointPixels((int)event.x(), (int)event.y());
	}

	@Override
	public void accept(GameEventVisitor visitor) {
		visitor.visit(this);
	}

	public PointPixels getPosition() {
		return position;
	}

}
