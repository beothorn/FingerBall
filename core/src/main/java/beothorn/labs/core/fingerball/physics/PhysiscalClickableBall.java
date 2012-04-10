package beothorn.labs.core.fingerball.physics;

import beothorn.labs.core.fingerball.units.PointMeters;

public interface PhysiscalClickableBall extends PhysicalBody {

	void kickAt(PointMeters kickPhysical);
	public void longKickAt(PointMeters kickPhysical);

}
