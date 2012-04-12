package beothorn.labs.core.fingerball.camera;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class CameraTest {

	@Ignore
	@Test
	public void testCamera(){
		RectanglePixels focusRectangle = new RectanglePixels(new PointPixels(10, 10), new DimensionPixels(10, 10));
		RectanglePixels mockSubjectSize = new RectanglePixels(new PointPixels(10, 10), new DimensionPixels(5, 5));
		CameraSubjectMock cameraSubjectMock = new CameraSubjectMock(mockSubjectSize);
		Camera subject = new Camera(cameraSubjectMock, focusRectangle);
		Assert.assertEquals("(0,0)", subject.getPosition().toString());
		subject.update();
		cameraSubjectMock.setNewPosition(20,20);
		Assert.assertEquals("(5,5)", subject.getPosition().toString());
	}
	
}
