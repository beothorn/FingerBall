package beothorn.labs.core.fingerball;

import java.util.List;

import beothorn.labs.core.fingerball.events.GameEvent;

public interface Updateable {
	void update(float delta, List<GameEvent> events);
}
