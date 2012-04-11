package beothorn.labs.core.fingerball.update;

import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;
import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.PointerDragEvent;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;

public class UpdaterPointerEventQueuer implements Listener {

	private final Updater updater;

	public UpdaterPointerEventQueuer(Updater updater) {
		this.updater = updater;
	}
	
	@Override
	public void onPointerStart(Event event) {
		queueEvent(new PointerStartEvent(event));
	}

	@Override
	public void onPointerEnd(Event event) {
		queueEvent(new PointerEndEvent(event));
	}

	@Override
	public void onPointerDrag(Event event) {
		queueEvent(new PointerDragEvent(event));
	}
	
	private void queueEvent(GameEvent gameEvent) {
		updater.queueEvent(gameEvent);
	}

}
