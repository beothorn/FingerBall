package beothorn.labs.core.fingerball.events;

public interface GameEvent {

	void accept(GameEventVisitor visitor);

}
