package beothorn.labs.core.fingerball.update;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.GameEventVisitor;
import beothorn.labs.core.fingerball.update.UpdaterImpl;

public class UpdaterTest {
	
	private UpdaterImpl subject;
	private UpdateableMock updateableMock;

	@Before
	public void setup() {
		subject = new UpdaterImpl();
		updateableMock = new UpdateableMock();
		subject.add(updateableMock);
	}
	
	@Test
	public void update_ShouldUpateUpdateables(){
		subject.update(0);
		Assert.assertEquals("0.0 , []", updateableMock.getUpdates());
	}

	
	@Test
	public void updateWithEvent_ShouldPassEventToUpdateablesAnCleanQueue(){
		GameEvent gameEvent = new GameEvent() {	
			@Override
			public void accept(GameEventVisitor visitor) {
				throw new RuntimeException("NOT IMPLEMENTED");
			}
			
			@Override
			public String toString() {
				return "foo";
			}
		};
		subject.queueEvent(gameEvent);
		subject.update(0);
		Assert.assertEquals("0.0 , [foo]", updateableMock.getUpdates());
		subject.update(0);
		Assert.assertEquals("0.0 , [foo]\n0.0 , []", updateableMock.getUpdates());
	}

}
