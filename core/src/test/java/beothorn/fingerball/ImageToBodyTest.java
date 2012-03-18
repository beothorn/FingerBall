package beothorn.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.fingerball.mock.ImageLayerMock;
import beothorn.fingerball.mock.PhysicBodyMock;

public class ImageToBodyTest {

	@Test
	public void testImageToBody(){
		ImageLayerMock imageLayerMock = new ImageLayerMock();
		PhysicBody physicBodyMock = new PhysicBodyMock(1.5f, 1.5f);
		DimensionMeters worldDimensions = new DimensionMeters(3, 3);
		DimensionPixels screenDimensions = new DimensionPixels(800, 600);
		MetersToPixels metersToPixelsConverter = new MetersToPixels(screenDimensions, worldDimensions);
		
		ImageToBody subject = new ImageToBody(imageLayerMock,physicBodyMock,metersToPixelsConverter);
		subject.update();
		Assert.assertEquals("(400.0,300.0)", imageLayerMock.getNewPositionAsString());
		Assert.assertEquals("0.0", imageLayerMock.getAngleAsString());
	}
	
}
