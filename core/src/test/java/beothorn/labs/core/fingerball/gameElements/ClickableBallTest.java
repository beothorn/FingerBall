package beothorn.labs.core.fingerball.gameElements;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import beothorn.labs.core.fingerball.GraphicsElementMock;
import beothorn.labs.core.fingerball.UpdateableUtils;
import beothorn.labs.core.fingerball.gameElements.ClickableBall;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointPixels;

public class ClickableBallTest {

	private PhysiscalClickableBallMock physicalBall = new PhysiscalClickableBallMock();
	private ClickableBall subject;
	
	@Before
	public void setupBall() {
		PointPixels pointPixels = new PointPixels(0, 0);
		DimensionPixels dimensionPixels = new DimensionPixels(10, 10);
		GraphicsElementMock graphicsBall = new GraphicsElementMock(pointPixels,dimensionPixels);
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		subject = new ClickableBall(physicalBall, graphicsBall, metersToPixelsConverter);
		UpdateableUtils.advance(subject);
	}
	
	@Test
	public void onClickBall_WillUpdatePhysicsBall(){
		UpdateableUtils.simulateClickAt(subject, new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}

	@Test
	public void onLongClickBall_WillUpdatePhysicsBall(){
		UpdateableUtils.simulateClickAndHoldAt(subject,new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)\nKick force increased", getPhysicalBallOperations());
	}
	
	@Test
	public void onClickBallAndRelease_WillOnlyDoOneNormalKick(){
		UpdateableUtils.simulateClickAt(subject,new PointPixels(5,5));
		UpdateableUtils.advance(subject);
		UpdateableUtils.simulatePointerRelease(subject);
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}

	@Test
	public void afterLongKick_WillDoNormalKick(){
		UpdateableUtils.simulateClickAndHoldAt(subject,new PointPixels(5,5));
		UpdateableUtils.simulatePointerRelease(subject);
		UpdateableUtils.simulateClickAt(subject,new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)\nKick force increased\nKicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}
	
	@Test
	public void onClickOutsideBall_WillDoNothingEvenWhenTimePasses(){
		UpdateableUtils.simulateClickAt(subject,new PointPixels(50,50));
		UpdateableUtils.advance(subject);
		String none = "";
		Assert.assertEquals(none, getPhysicalBallOperations());
	}

	private String getPhysicalBallOperations() {
		return physicalBall.getOperations();
	}
}


