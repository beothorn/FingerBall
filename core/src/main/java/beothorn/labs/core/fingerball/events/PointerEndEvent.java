package beothorn.labs.core.fingerball.events;


public class PointerEndEvent implements GameEvent {

	@Override
	public void accept(GameEventVisitor visitor) {
		visitor.visit(this);
	}

}
