package beothorn.labs.core.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointPixels;

public class BallWithDirectionTest {

	@Test
	public void onClickAndDrag_ShouldDecideDirectionBasedOnLastDragPointBeforeMaxDraggingTime(){
		PhysiscalBallWithDirectionMock physicalBall = new PhysiscalBallWithDirectionMock();
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		BallWithDirection subject = new BallWithDirection(physicalBall,metersToPixelsConverter);
		PointPixels vectorStart = new PointPixels(50, 50);
		UpdateableUtils.simulateClickAt(subject, vectorStart);
		PointPixels vectorEnd = new PointPixels(100, 100);
		UpdateableUtils.simulatePointerRelease(subject, vectorEnd);
		Assert.assertEquals("Applied force(0.5,0.5)", physicalBall.getOperations());
	}
	
}
