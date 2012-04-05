package beothorn.labs.core.fingerball;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.GameEventVisitor;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.graphics.GraphicsBall;
import beothorn.labs.core.fingerball.physics.PhysiscalBall;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;

public class Ball implements GameEventVisitor{
	
	private PhysiscalBall physicalBall;
	private GraphicsBall graphicsBall;
	private MetersToPixelsConverter metersToPixelsConverter;
	private InputListener ballInputListener;
	private PointPixels kick;
	private float timeHoldingKick;
	private float currentDelta;
	
	public Ball(final PhysiscalBall physicalBall,final GraphicsBall graphicsBall, Input input, final MetersToPixelsConverter metersToPixelsConverter) {
		this.physicalBall = physicalBall;
		this.graphicsBall = graphicsBall;
		this.metersToPixelsConverter = metersToPixelsConverter;
		ballInputListener = new BallInputListener(metersToPixelsConverter, physicalBall,graphicsBall);
		input.setListener(ballInputListener);
	}

	public void update(float delta, GameEvent[] events) {
		this.currentDelta = delta;
		if(events != null){
			processGameEvents(events);
		}
		
		if(kick != null){
			timeHoldingKick += currentDelta;
			if(timeHoldingKick >= BallPointerListener.LONG_KICK_MIN_PRESS_INTERVAL){
				ballInputListener.longKickAt(kick);
			}
		}
		propagatePhycicalChangesToGraphics();
	}

	private void processGameEvents(GameEvent[] events) {
		for (GameEvent gameEvent : events) {
			gameEvent.accept(this);
		}
	}
	
	@Override
	public void visit(PointerStartEvent pointerStartEvent){
		kick = pointerStartEvent.getPosition();
		ballInputListener.kickAt(kick);
		timeHoldingKick = currentDelta;
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
