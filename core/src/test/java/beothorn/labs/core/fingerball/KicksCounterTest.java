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
		subject.kick();
		subject.kick();
		Assert.assertEquals(3, subject.getKicksCount());
		subject.hitTheGround();
		Assert.assertEquals(0, subject.getKicksCount());
		Assert.assertEquals(3, record.get());
	}
	
}
