package beothorn.labs.core.fingerball.update;

import beothorn.labs.core.fingerball.events.GameEvent;

public interface Updater {
	void queueEvent(GameEvent gameEvent);
}
