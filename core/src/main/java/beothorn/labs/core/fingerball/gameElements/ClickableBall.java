package beothorn.labs.core.fingerball.gameElements;

import java.util.List;

import beothorn.labs.core.fingerball.Updateable;
import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.GameEventVisitor;
import beothorn.labs.core.fingerball.events.PointerDragEvent;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.graphics.GraphicsElement;
import beothorn.labs.core.fingerball.physics.PhysiscalClickableBall;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class ClickableBall implements GameEventVisitor, Updateable{
	
	private static final float LONG_KICK_MIN_PRESS_INTERVAL = 200;
	private PhysiscalClickableBall physicalBall;
	private GraphicsElement graphicsBall;
	private MetersToPixelsConverter metersToPixelsConverter;
	private PointPixels kick;
	private float timeHoldingKick;
	
	public ClickableBall(final PhysiscalClickableBall physicalBall,final GraphicsElement graphicsBall, final MetersToPixelsConverter metersToPixelsConverter) {
		this.physicalBall = physicalBall;
		this.graphicsBall = graphicsBall;
		this.metersToPixelsConverter = metersToPixelsConverter;
	}

	@Override
	public void update(float delta, List<GameEvent> events) {
		if(events != null){
			processGameEvents(events);
		}
		
		if(kick != null){
			timeHoldingKick += delta;
			if(timeHoldingKick >= LONG_KICK_MIN_PRESS_INTERVAL){
				longKickAt(kick);
				endKick();
			}
		}
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
		timeHoldingKick = 0;
	}
	
	@Override
	public void visit(PointerEndEvent pointerEndEvent) {
		endKick();
	}
	
	@Override
	public void visit(PointerDragEvent pointerDragEvent) {
		//do nothing
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
	
}
