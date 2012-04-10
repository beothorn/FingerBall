package beothorn.labs.core.fingerball;

import org.junit.Test;

import beothorn.labs.core.fingerball.graphics.GraphicsElement;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.PointPixels;

public class PhysicsToGraphicsPositionUpdaterTest {
	
	@Test
	public void happyDay(){
		
		PhysicsToGraphicsPositionUpdater subject = new PhysicsToGraphicsPositionUpdater();
		GraphicsElement graphicsElement = new GraphicsElementMock(new PointPixels(0, 0), new DimensionPixels(10, 10));
		
//		subject.registerToUpdate()
		
	}

}
