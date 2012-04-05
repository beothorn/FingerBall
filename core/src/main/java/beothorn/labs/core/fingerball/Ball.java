package beothorn.labs.core.fingerball;

import java.util.List;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.GameEventVisitor;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.graphics.GraphicsBall;
import beothorn.labs.core.fingerball.physics.PhysiscalBall;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class Ball implements GameEventVisitor{
	
	private static final float LONG_KICK_MIN_PRESS_INTERVAL = 500;
	private PhysiscalBall physicalBall;
	private GraphicsBall graphicsBall;
	private MetersToPixelsConverter metersToPixelsConverter;
	private PointPixels kick;
	private float timeHoldingKick;
	private float currentDelta;
	
	public Ball(final PhysiscalBall physicalBall,final GraphicsBall graphicsBall, final MetersToPixelsConverter metersToPixelsConverter) {
		this.physicalBall = physicalBall;
		this.graphicsBall = graphicsBall;
		this.metersToPixelsConverter = metersToPixelsConverter;
	}

	public void update(float delta, List<GameEvent> events) {
		this.currentDelta = delta;
		if(events != null){
			processGameEvents(events);
		}
		
		if(kick != null){
			timeHoldingKick += currentDelta;
			if(timeHoldingKick >= LONG_KICK_MIN_PRESS_INTERVAL){
				longKickAt(kick);
				endKick();
			}
		}
		propagatePhycicalChangesToGraphics();
	}

	private void endKick() {
		kick = null;
	}

	private void processGameEvents(List<GameEvent> events) {
		for (GameEvent gameEvent : events) {
			gameEvent.accept(this);
		}
	}
	
	@Override
	public void visit(PointerStartEvent pointerStartEvent){
		PointPixels kickPosition = pointerStartEvent.getPosition();
		if(!isPointInsideBall(kickPosition)){
			return;
		}
		kick = kickPosition;
		kickAt(kick);
		timeHoldingKick = currentDelta;
	}
	
	@Override
	public void visit(PointerEndEvent pointerEndEvent) {
		endKick();
	}
	
	private void kickAt(PointPixels kick) {
		PointMeters kickPhysical = metersToPixelsConverter.pixelsToMeters(kick);
		physicalBall.kickAt(kickPhysical);
	}

	private void longKickAt(PointPixels kick) {
		PointMeters kickPhysical = metersToPixelsConverter.pixelsToMeters(kick);
		physicalBall.longKickAt(kickPhysical);
	}

	private boolean isPointInsideBall(PointPixels kick) {
		RectanglePixels rectangle =  graphicsBall.getRectangle();
		boolean pointIsNotInsideBall = rectangle.contains(kick);
		return pointIsNotInsideBall;
	}
	
	private void propagatePhycicalChangesToGraphics() {
		Vec2 position = physicalBall.getPosition();
		PointMeters bodyPosition = new PointMeters(position.x, position.y);
		graphicsBall.setRotation(physicalBall.getAngle());
		PointPixels pixelPosition = metersToPixelsConverter.metersToPixels(bodyPosition);
		int x = pixelPosition.x;
		int y = pixelPosition.y;
		graphicsBall.setLocation(x, y);
	}
}
