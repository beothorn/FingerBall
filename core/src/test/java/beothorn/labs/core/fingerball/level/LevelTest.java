package beothorn.labs.core.fingerball.level;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.fingerball.FingerBall;

public class LevelTest {

	@Test
	public void test(){
		LevelChangeListener subject = new FingerBall();
		LevelMock level = new LevelMock();
		subject.setLevel(level);
		Assert.assertTrue(level.initWasCalled());
		LevelMock secondLevel = new LevelMock();
		subject.setLevel(secondLevel);
		Assert.assertTrue(level.destroyWasCalled());
	}
	
}
