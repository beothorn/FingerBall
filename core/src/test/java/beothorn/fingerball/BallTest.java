package beothorn.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.Ball;
import beothorn.labs.core.RectanglePixels;

public class BallTest {

	@Test
	public void onClickBall_WillUpdatePhysicsBall(){
		simulateClickAt(new PointPixels(5,5));
		Assert.assertEquals("Kicked at (0.05m,0.05m)", getPhysicalBallOperations());
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
	InputMock input = new InputMock();
	private void simulateClickAt(PointPixels kick) {
		PointPixels pointPixels = new PointPixels(0, 0);
		DimensionPixels dimensionPixels = new DimensionPixels(10, 10);
		GraphicsBallMock graphicsBall = new GraphicsBallMock(pointPixels,dimensionPixels);
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		new Ball(physicalBall, graphicsBall, input, metersToPixelsConverter);
		input.simulateKick(kick);
	}
	
	private String getPhysicalBallOperations() {
		return physicalBall.getOperations();
	}
}


