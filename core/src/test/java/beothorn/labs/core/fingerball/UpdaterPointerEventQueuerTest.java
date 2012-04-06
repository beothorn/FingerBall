package beothorn.labs.core.fingerball;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import beothorn.labs.core.fingerball.events.GameEvent;

public class UpdaterPointerEventQueuerTest {

	private UpdaterPointerEventQueuer subject;
	private StringBuffer events;

	@Before
	public void setup() {
		events = new StringBuffer();
		Updater updater = new Updater() {
			
			@Override
			public void queueEvent(GameEvent gameEvent) {
				if(!events.toString().isEmpty()){
					events.append("\n");
				}
				events.append(gameEvent.getClass().getSimpleName()+" | "+gameEvent.toString());
			}
		};
		subject = new UpdaterPointerEventQueuer(updater);
	}
	
	@Test
	public void shouldAddPointerEndEventToTheQueue(){
		subject.onPointerEnd(new PointerEventMock());
		Assert.assertEquals("PointerEndEvent | pointer end", events.toString());
	}
	
	@Test
	public void shouldAddPointerStartEventToTheQueue(){
		subject.onPointerStart(new PointerEventMock());
		Assert.assertEquals("PointerStartEvent | (0px,0px)", events.toString());
	}
	
	@Test
	public void shouldAddPointerDragEventToTheQueue(){
		subject.onPointerDrag(new PointerEventMock());
		Assert.assertEquals("PointerDragEvent | (0px,0px)", events.toString());
	}
}
