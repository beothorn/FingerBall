package beothorn.labs.core.fingerball.level;

public class LevelMock implements Level {

	private boolean init;
	private boolean destroy;

	public LevelMock() {
		init = false;
		destroy = false;
	}
	
	public boolean initWasCalled() {
		return init;
	}

	@Override
	public void init() {
		init = true;
	}

	public boolean destroyWasCalled() {
		return destroy;
	}

	@Override
	public void destroy() {
		destroy = true;
	}

	@Override
	public void paint(float delta) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void update(float delta) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
