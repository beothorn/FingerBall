package beothorn.labs.core.fingerball.events;

public interface GameEventVisitor {
	void visit(PointerStartEvent pointerStartEvent);
}
