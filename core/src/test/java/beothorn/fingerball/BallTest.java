package beothorn.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.Ball;

public class BallTest {

	@Test
	public void onClickBall_WillUPdatePhysicsBall(){
		simulateClickAt(new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
	}
	
	@Test
	public void onClickOutsideBall_WillDoNothing(){
		simulateClickAt(new PointPixels(50,50));
		String none = "";
		Assert.assertEquals(none, getPhysicalBallOperations());
	}
	

//	
//	public void onClickAndHoldBall_WillUPdatePhysicsBall(){
//		Input input = new InputMock();
//		PhysiscalBall physicalBall = new PhysiscalBallMock(); 
//		Ball ball = new Ball(physicalBall, input);
//		
//		PointPixels kick = new PointPixels(1,1);
//		input.simulateLongKick(kick);
//		
//		Assert.assertEquals(
//				"Kicked at 0.2,0.15 meters\n" +
//				"Kick force increased", physicalBall.getOperations());
//	}
//	
//	public void onPhysicalBallUpdate_MustUpdateGraphicsBall(){
//		PhysiscalBallMock physicalBall = new PhysiscalBallMock();
//		physicalBall.simulateUpdatePositionTo(1,1);
//		Ball ball = new Ball(physicalBall, input);
//		
//	}
	PhysiscalBallMock physicalBall = new PhysiscalBallMock();

	private void simulateClickAt(PointPixels kick) {
		InputMock input = new InputMock();
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		PointPixels pointPixels = new PointPixels(0, 0);
		DimensionPixels dimensionPixels = new DimensionPixels(10, 10);
		GraphicsBallMock graphicsBallMock = new GraphicsBallMock(pointPixels,dimensionPixels);
		new Ball(physicalBall, graphicsBallMock, input, metersToPixelsConverter);
		input.simulateKick(kick);
	}
	
	private String getPhysicalBallOperations() {
		return physicalBall.getOperations();
	}
}


