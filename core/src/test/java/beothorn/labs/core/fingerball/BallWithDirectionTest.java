package beothorn.labs.core.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.fingerball.units.PointPixels;

public class BallWithDirectionTest {

	@Test
	public void onClickAndDrag_ShouldDecideDirectionBasedOnLastDragPointBeforeMaxDraggingTime(){
		PhysiscalBallMock physicalBall = new PhysiscalBallMock();
		BallWithDirection subject = new BallWithDirection(physicalBall);
		PointPixels kick = new PointPixels(0, 0);
		UpdateableUtils.simulateClickAt(subject, kick);
		PointPixels drag = new PointPixels(1, 1);
		UpdateableUtils.simulateDragAt(subject, drag);
		float timePassed = 1000;
		UpdateableUtils.simulateMillisPassed(timePassed);
		Assert.assertEquals("", physicalBall.getOperations());
	}
	
}
