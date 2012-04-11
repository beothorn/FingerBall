package beothorn.labs.core.fingerball.level;

public interface Level {
	void init();
	void destroy();
	void paint(float delta);
	void update(float delta); 
}
