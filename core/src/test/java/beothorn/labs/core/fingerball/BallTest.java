package beothorn.labs.core.fingerball;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import beothorn.labs.core.fingerball.Ball;
import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class BallTest {

	PhysiscalBallMock physicalBall = new PhysiscalBallMock();
	private Ball subject;
	
	@Before
	public void setupBall() {
		PointPixels pointPixels = new PointPixels(0, 0);
		DimensionPixels dimensionPixels = new DimensionPixels(10, 10);
		GraphicsBallMock graphicsBall = new GraphicsBallMock(pointPixels,dimensionPixels);
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		subject = new Ball(physicalBall, graphicsBall, metersToPixelsConverter);
	}
	
	@Test
	public void onClickBall_WillUpdatePhysicsBall(){
		simulateClickAt(new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}

	@Test
	public void onLongClickBall_WillUpdatePhysicsBall(){
		simulateClickAndHoldAt(new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)\nKick force increased", getPhysicalBallOperations());
	}
	
	@Test
	public void onClickBallAndRelease_WillOnlyDoOneNormalKick(){
		simulateClickAt(new PointPixels(5,5));
		simulatePointerRelease();
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}

	private void simulatePointerRelease() {
		GameEvent pointerStartEvent = new PointerEndEvent();
		List<GameEvent> events = Arrays.asList(new GameEvent[]{pointerStartEvent});
		subject.update(1000, events);
	}
	
//	@Test
//	public void clickAndHold_ShouldDoLongKick(){
//		
//		subject.onPointerStart(new MockEvent(1, 2, 0));
//		subject.onPointerEnd(new MockEvent(1, 2, 79));
//		
//		subject.onPointerStart(new MockEvent(2, 3, 0));
//		int longKickHoldingPointerTimeMillis = 81;
//		subject.onPointerEnd(new MockEvent(2, 3, longKickHoldingPointerTimeMillis));
//		
//		Assert.assertEquals("kickAt((1px,2px))kickAt((2px,3px))longKickAt((2px,3px))", inputLog.toString());
//	}
//	
//	@Test
//	public void clickAndHoldTooMuch_ShouldDoOnlyNormalKick(){		
//		subject.onPointerStart(new MockEvent(1, 2, 0));
//		int tooMuchHoldingPointerTimeMillis = 111;
//		subject.onPointerEnd(new MockEvent(1, 2, tooMuchHoldingPointerTimeMillis));
//		
//		Assert.assertEquals("kickAt((1px,2px))", inputLog.toString());
//	}
	

	@Test
	public void onClickOutsideBall_WillDoNothingEvenWhenTimePasses(){
		simulateClickAt(new PointPixels(50,50));
		subject.update(200, null);
		String none = "";
		Assert.assertEquals(none, getPhysicalBallOperations());
	}
	
	@Test
	public void onUpdate_WillUpdateGraphicsBall(){
		PointPixels pointPixels = new PointPixels(0, 0);
		DimensionPixels dimensionPixels = new DimensionPixels(10, 10);
		GraphicsBallMock graphicsBall = new GraphicsBallMock(pointPixels,dimensionPixels);
		
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		
		Ball subject = new Ball(physicalBall, graphicsBall, metersToPixelsConverter);
		
		PointMeters newPosition = new PointMeters(0.05f, 0.05f);
		physicalBall.setPositionAnRotation(newPosition, 90);
		
		subject.update(0, null);
		
		RectanglePixels graphicsBallRectangle = graphicsBall.getRectangle();
		Assert.assertEquals("5,5", graphicsBallRectangle.x+","+graphicsBallRectangle.y);
	}

	
	private void simulateClickAndHoldAt(PointPixels kick) {
		simulateClickAt(kick);
		int delta = 1000;
		subject.update(delta, null);
	}

	private void simulateClickAt(PointPixels kick) {
		MockEvent mockEvent = new MockEvent(kick.x, kick.y, 0);
		float delta = 0;
		GameEvent pointerStartEvent = new PointerStartEvent(mockEvent);
		List<GameEvent> events = Arrays.asList(new GameEvent[]{pointerStartEvent});
		subject.update(delta, events);
	}
	
	private String getPhysicalBallOperations() {
		return physicalBall.getOperations();
	}
}


