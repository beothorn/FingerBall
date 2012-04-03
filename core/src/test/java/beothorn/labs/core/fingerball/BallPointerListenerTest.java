package beothorn.labs.core.fingerball;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import beothorn.labs.core.fingerball.units.PointPixels;

public class BallPointerListenerTest {

	private StringBuffer inputLog;
	
	private BallPointerListener subject = new BallPointerListener(new InputListener() {
		
		@Override
		public void longKickAt(PointPixels kick) {
			inputLog.append("longKickAt("+kick+")");
		}
		
		@Override
		public void kickAt(PointPixels kick) {
			inputLog.append("kickAt("+kick+")");
		}
	});
	
	
	@Before
	public void setup(){
		inputLog = new StringBuffer();		
	}
	
	@Test
	public void clickAndHold_ShouldDoLongKick(){
		
		subject.onPointerStart(new MockEvent(1, 2, 100));
		subject.onPointerEnd(new MockEvent(1, 2, 199));
		
		subject.onPointerStart(new MockEvent(2, 3, 200));
		int longKickHoldingPointerTimeMillis = 201;
		subject.onPointerEnd(new MockEvent(2, 3, 200+longKickHoldingPointerTimeMillis));
		
		Assert.assertEquals("kickAt((1px,2px))kickAt((2px,3px))longKickAt((2px,3px))", inputLog.toString());
	}
	
	@Test
	public void clickAndHoldTooMuch_ShouldDoOnlyNormalKick(){		
		subject.onPointerStart(new MockEvent(1, 2, 0));
		int tooMuchHoldingPointerTimeMillis = 401;
		subject.onPointerEnd(new MockEvent(1, 2, tooMuchHoldingPointerTimeMillis));
		
		Assert.assertEquals("kickAt((1px,2px))", inputLog.toString());
	}
}
