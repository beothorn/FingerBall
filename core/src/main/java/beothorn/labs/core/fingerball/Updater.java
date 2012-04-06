package beothorn.labs.core.fingerball;

import java.util.ArrayList;
import java.util.List;

import beothorn.labs.core.fingerball.events.GameEvent;

public class Updater{

	private List<Updateable> updateables;
	private List<GameEvent> eventQueue;
	
	public Updater() { 
		updateables = new ArrayList<Updateable>();
		eventQueue  = new ArrayList<GameEvent>();
	}
	
	public void add(Updateable updateable) {
		updateables.add(updateable);
	}

	public void update(float delta){		
		for (Updateable updateable : updateables) {
			updateable.update(delta, eventQueue);
		}
		eventQueue.clear();
	}

	public void queueEvent(GameEvent gameEvent) {
		eventQueue.add(gameEvent);
	} 

}
