package beothorn.labs.core.fingerball;

public class KicksCounter {

	int kicks;
	int bestKicks;
	private final KickRecordBreakListener kickRecordBreakListener;
	
	public KicksCounter(KickRecordBreakListener kickRecordBreakListener) {
		this.kickRecordBreakListener = kickRecordBreakListener;
	}

	public void kick() {
		kicks++;
	}

	public void hitTheGround() {
		if(kicks> bestKicks){
			bestKicks = kicks;
			kickRecordBreakListener.newRecord(kicks);
		}
		kicks = 0;
	}

}
