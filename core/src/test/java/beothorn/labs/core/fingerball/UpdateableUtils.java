package beothorn.labs.core.fingerball;

import java.util.Arrays;
import java.util.List;

import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.units.PointPixels;

public class UpdateableUtils {

	private static final int DELTA = 25;
	
	static void simulatePointerRelease(Updateable subject) {
		GameEvent pointerStartEvent = new PointerEndEvent();
		List<GameEvent> events = Arrays.asList(new GameEvent[]{pointerStartEvent});
		advance(subject, events);
	}

	static void simulateClickAndHoldAt(Updateable subject, PointPixels kick) {
		simulateClickAt(subject, kick);
		for (int i = 0; i < 10; i++) {			
			advance(subject);
		}
	}

	static void simulateClickAt(Updateable subject, PointPixels kick) {
		EventMock mockEvent = new EventMock(kick.x, kick.y, 0);
		GameEvent pointerStartEvent = new PointerStartEvent(mockEvent);
		List<GameEvent> events = Arrays.asList(new GameEvent[]{pointerStartEvent});
		advance(subject, events);
	}

	static void advance(Updateable subject) {
		subject.update(DELTA, null);
	}

	private static void advance(Updateable subject, List<GameEvent> events) {
		subject.update(DELTA, events);
	}

	public static void simulateDragAt(BallWithDirection subject,PointPixels kick) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public static void simulateMillisPassed(float timePassed) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}