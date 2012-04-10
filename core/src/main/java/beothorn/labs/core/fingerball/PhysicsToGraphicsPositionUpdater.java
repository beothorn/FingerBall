package beothorn.labs.core.fingerball;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.graphics.GraphicsElement;
import beothorn.labs.core.fingerball.physics.PhysicalBody;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;

public class PhysicsToGraphicsPositionUpdater {

	private HashMap<GraphicsElement, PhysicalBody> graphicToPhysics;
	private final MetersToPixelsConverter metersToPixelsConverter;
	
	public PhysicsToGraphicsPositionUpdater(MetersToPixelsConverter metersToPixelsConverter) {
		graphicToPhysics = new HashMap<GraphicsElement, PhysicalBody>();
		this.metersToPixelsConverter = metersToPixelsConverter;
	}

	public void registerToUpdate(GraphicsElement graphicsElement, PhysicalBody physicalBodyMock) {
		graphicToPhysics.put(graphicsElement, physicalBodyMock);
	}

	public void update() {
		Set<Entry<GraphicsElement, PhysicalBody>> entrySet = graphicToPhysics.entrySet();
		for (Entry<GraphicsElement, PhysicalBody> entry : entrySet) {
			GraphicsElement graphicElement = entry.getKey();
			PhysicalBody physicalBody = entry.getValue();
			
			Vec2 position = physicalBody.getPosition();
			PointMeters bodyPosition = new PointMeters(position.x, position.y);
			graphicElement.setRotation(physicalBody.getAngle());
			PointPixels pixelPosition = metersToPixelsConverter.metersToPixels(bodyPosition);
			int x = pixelPosition.x;
			int y = pixelPosition.y;
			graphicElement.setLocation(x, y);
		}
	}

}
