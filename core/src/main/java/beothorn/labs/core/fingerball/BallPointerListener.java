package beothorn.labs.core.fingerball;

import beothorn.labs.core.fingerball.units.PointPixels;
import playn.core.Pointer;
import playn.core.Pointer.Event;

public class BallPointerListener implements Pointer.Listener{

	private static final double LONG_KICK_PRESS_INTERVAL = 200;
	
	private double pointerStart;
	private final InputListener inputListener;

	public BallPointerListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	@Override
	public void onPointerStart(Event event) {
		PointPixels kick = new PointPixels((int)event.x(), (int)event.y());
		inputListener.kickAt(kick);
		pointerStart = event.time();
	}
			
	@Override
	public void onPointerEnd(Event event) {
		double pointerEnd = event.time();
		if(pointerEnd - pointerStart > LONG_KICK_PRESS_INTERVAL){
			PointPixels kick = new PointPixels((int)event.x(), (int)event.y());
			inputListener.longKickAt(kick);
		}
	}
			
	@Override
	public void onPointerDrag(Event event) {
	}

}
