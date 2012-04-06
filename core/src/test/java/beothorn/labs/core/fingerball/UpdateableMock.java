package beothorn.labs.core.fingerball;

import java.util.List;

import beothorn.labs.core.fingerball.events.GameEvent;

class UpdateableMock implements Updateable {

	private String updateEvents = "";
	
	@Override
	public void update(float delta, List<GameEvent> events) {
		if(!updateEvents.isEmpty()){
			updateEvents+="\n";
		}
		updateEvents+=delta +" , "+events;
	}

	public String getUpdates() {
		return updateEvents;
	}

}
