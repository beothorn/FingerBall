package beothorn.labs.core.fingerball.update;

import java.util.Arrays;
import java.util.List;

import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.update.Updateable;

public class UpdateableUtils {
	
	private static final int DELTA = 25;
	
	public static void simulatePointerRelease(Updateable subject) {
		PointPixels releasePosition = new PointPixels(0, 0);
		simulatePointerRelease(subject, releasePosition);
	}

	public static void simulateClickAndHoldAt(Updateable subject, PointPixels kick) {
		simulateClickAt(subject, kick);
		for (int i = 0; i < 10; i++) {			
			advance(subject);
		}
	}

	public static void simulateClickAt(Updateable subject, PointPixels clickPosition) {
		EventMock mockEvent = new EventMock(clickPosition.x, clickPosition.y, 0);
		GameEvent pointerStartEvent = new PointerStartEvent(mockEvent);
		List<GameEvent> events = Arrays.asList(new GameEvent[]{pointerStartEvent});
		advance(subject, events);
	}

	public static void advance(Updateable subject) {
		subject.update(DELTA, null);
	}

	private static void advance(Updateable subject, List<GameEvent> events) {
		subject.update(DELTA, events);
	}

	public static void simulateDragAt(Updateable subject,PointPixels kick) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public static void simulateMillisPassed(float timePassed) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public static void simulatePointerRelease(Updateable subject,PointPixels releasePosition) {
		EventMock mockEvent = new EventMock(releasePosition.x, releasePosition.y, 0);
		GameEvent pointerEndEvent = new PointerEndEvent(mockEvent);
		List<GameEvent> events = Arrays.asList(new GameEvent[]{pointerEndEvent});
		advance(subject, events);
	}

}