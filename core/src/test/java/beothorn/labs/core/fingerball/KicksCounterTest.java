package beothorn.labs.core.fingerball;

import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;

import org.junit.Test;

public class KicksCounterTest {

	@Test
	public void doSomeKicks_ShouldCountUntilBallHitsTheGround(){
		final AtomicInteger record = new AtomicInteger(0);
		KicksCounter subject = new KicksCounter(new KickRecordBreakListener(){
			@Override
			public void newRecord(int kickCount){
				record.set(kickCount);
			}
		});
		subject.kick();
		subject.hitTheGround();
		Assert.assertEquals(1, record.get());
		subject.kick();
		subject.kick();
		subject.hitTheGround();
		Assert.assertEquals(2, record.get());
	}
	
}
