package beothorn.labs.core.fingerball;

import junit.framework.Assert;

import org.junit.Test;

import beothorn.labs.core.fingerball.graphics.GraphicsElement;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointPixels;

public class PhysicsToGraphicsPositionUpdaterTest {
	
	@Test
	public void happyDay(){
		DimensionPixels pixels = new DimensionPixels(100, 100);
		DimensionMeters meters = new DimensionMeters(1, 1);
		MetersToPixelsConverter metersToPixelsConverter = new MetersToPixelsConverter(pixels, meters);
		PhysicsToGraphicsPositionUpdater subject = new PhysicsToGraphicsPositionUpdater(metersToPixelsConverter);
		GraphicsElement graphicsElement = new GraphicsElementMock(new PointPixels(0, 0), new DimensionPixels(10, 10));
		PhysicalBodyMock physicalBodyMock = new PhysicalBodyMock(0,0);
		
		subject.registerToUpdate(graphicsElement,physicalBodyMock);
		subject.update();
		Assert.assertEquals("10x10+0+0", graphicsElement.getRectangle().toString());
		
		physicalBodyMock.setPosition(0.5f,0.5f);
		subject.update();
		Assert.assertEquals("10x10+50+50", graphicsElement.getRectangle().toString());
	}

}
