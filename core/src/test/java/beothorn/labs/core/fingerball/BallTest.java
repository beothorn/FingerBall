package beothorn.labs.core.fingerball;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import beothorn.labs.core.fingerball.Ball;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class BallTest {

	@Test
	public void onClickBall_WillUpdatePhysicsBall(){
		simulateClickAt(new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}

	@Test
	public void onLongClickBall_WillUpdatePhysicsBall(){
		simulateLongClickAt(new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)\nKick force increased", getPhysicalBallOperations());
	}
	

	@Test
	public void onClickOutsideBall_WillDoNothing(){
		simulateClickAt(new PointPixels(50,50));
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
		
		Ball subject = new Ball(physicalBall, graphicsBall, input, metersToPixelsConverter);
		
		PointMeters newPosition = new PointMeters(0.05f, 0.05f);
		physicalBall.setPositionAnRotation(newPosition, 90);
		
		subject.update();
		
		RectanglePixels graphicsBallRectangle = graphicsBall.getRectangle();
		Assert.assertEquals("5,5", graphicsBallRectangle.x+","+graphicsBallRectangle.y);
	}

	PhysiscalBallMock physicalBall = new PhysiscalBallMock();
	InputMock input = new InputMock();

	@Before
	public void setupBall() {
		PointPixels pointPixels = new PointPixels(0, 0);
		DimensionPixels dimensionPixels = new DimensionPixels(10, 10);
		GraphicsBallMock graphicsBall = new GraphicsBallMock(pointPixels,dimensionPixels);
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		new Ball(physicalBall, graphicsBall, input, metersToPixelsConverter);
	}
	
	private void simulateLongClickAt(PointPixels kick) {
		input.simulateLongKick(kick);
	}
	
	private void simulateClickAt(PointPixels kick) {
		input.simulateKick(kick);
	}
	
	private String getPhysicalBallOperations() {
		return physicalBall.getOperations();
	}
}


