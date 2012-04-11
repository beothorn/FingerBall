package beothorn.labs.core.fingerball.gameElements;

import java.util.List;

import org.jbox2d.common.Vec2;

import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.GameEventVisitor;
import beothorn.labs.core.fingerball.events.PointerDragEvent;
import beothorn.labs.core.fingerball.events.PointerEndEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.physics.PhysiscalBallWithDirection;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.update.Updateable;

public class BallWithDirection implements Updateable,GameEventVisitor {

	private final PhysiscalBallWithDirection physicalBall;
	private final MetersToPixelsConverter metersToPixelsConverter;
	private PointMeters startingPoint;
	private final VectorDrawer vectorDrawer;

	public BallWithDirection(PhysiscalBallWithDirection physicalBall, VectorDrawer vectorDrawer, final MetersToPixelsConverter metersToPixelsConverter) {
		this.physicalBall = physicalBall;
		this.vectorDrawer = vectorDrawer;
		this.metersToPixelsConverter = metersToPixelsConverter;
	}

	@Override
	public void update(float delta, List<GameEvent> events) {
		if(events != null){
			processGameEvents(events);
		}
	}
	
	private void processGameEvents(List<GameEvent> events) {
		for (GameEvent gameEvent : events) {
			gameEvent.accept(this);
		}
		vectorDrawer.redraw();
	}

	@Override
	public void visit(PointerStartEvent pointerStartEvent) {
		PointPixels vectorStartScreenCoords = pointerStartEvent.getPosition();
		startingPoint = metersToPixelsConverter.pixelsToMeters(vectorStartScreenCoords);
		vectorDrawer.startVector(vectorStartScreenCoords); 
	}

	@Override
	public void visit(PointerEndEvent pointerEndEvent) {
		PointPixels vectorEndScreenCoords = pointerEndEvent.getPosition();
		PointMeters endingPoint = metersToPixelsConverter.pixelsToMeters(vectorEndScreenCoords);
		Vec2 force = new Vec2(endingPoint.x - startingPoint.x, endingPoint.y - startingPoint.y);
		physicalBall.applyForce(force);
		vectorDrawer.clearVector();
	}

	@Override
	public void visit(PointerDragEvent pointerDragEvent) {
		PointPixels vectorEndScreenCoords = pointerDragEvent.getPosition();
		vectorDrawer.updateVector(vectorEndScreenCoords);
	}

}
