package beothorn.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.Ball;

public class BallTest {

	@Test
	public void onClickBall_WillUPdatePhysicsBall(){
		InputMock input = new InputMock();
		PhysiscalBallMock physicalBall = new PhysiscalBallMock();
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		new Ball(physicalBall, input, metersToPixelsConverter);
		PointPixels kick = new PointPixels(1,1);
		input.simulateKick(kick);
		Assert.assertEquals("Kicked at (0.01m,0.01m)", physicalBall.getOperations());
	}
//		
//	}
//	
//	public void onClickOUtsideBall_WillDoNothing(){
//		Input input = new InputMock();
//		PhysiscalBall physicalBall = new PhysiscalBallMock(); 
//		Ball ball = new Ball(physicalBall, input);
//		
//		Point outsideBall = new Point(5,5);
//		input.simulateKick(outsideBall);
//		
//		String nop = "";
//		Assert.assertEquals(nop, physicalBall.getOperations());
//	}
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
	
	
}


