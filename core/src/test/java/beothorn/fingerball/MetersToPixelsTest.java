package beothorn.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.fingerball.DimensionMeters;
import beothorn.fingerball.DimensionPixels;
import beothorn.fingerball.MetersToPixels;
import beothorn.fingerball.PointMeters;
import beothorn.fingerball.PointPixels;


public class MetersToPixelsTest {
	DimensionPixels pixels = new DimensionPixels(800,320);
	DimensionMeters meters = new DimensionMeters(2,3);
	MetersToPixels subject = new MetersToPixels(pixels, meters);
	
	@Test
	public void pixelsToMeters_ShouldTransform(){
		PointPixels screenPoint = new PointPixels(400,160);
		PointMeters worldPoint = subject.pixelsToMeters(screenPoint);
		Assert.assertEquals( "(1.0m,1.5m)", worldPoint.toString());
	}

	@Test
	public void metersToPixels_ShouldTransform(){
		PointMeters worldPoint = new PointMeters(1.0f, 1.5f);
		PointPixels screenPoint = subject.metersToPixels(worldPoint);
		Assert.assertEquals( "(400px,160px)", screenPoint.toString());
	}
	
}
