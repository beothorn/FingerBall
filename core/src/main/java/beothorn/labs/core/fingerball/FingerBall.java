package beothorn.labs.core.fingerball;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Graphics;
import beothorn.labs.core.fingerball.level.Level;
import beothorn.labs.core.fingerball.level.LevelChangeListener;
import beothorn.labs.core.fingerball.units.DimensionPixels;

public class FingerBall implements Game, LevelChangeListener { 
	
	private static final DimensionPixels screenDimensions = new DimensionPixels(800, 600);
	
	public static final String BACKGROUND_IMAGE = "images/background.png";
	public static final String BALL_IMAGE = "images/soccerBall.png";
	public static final String GOAL_IMAGE = "images/goal.png";

	private Level level;
	
	@Override
	public void init() {
		preloadResources();
		
		Graphics graphics = graphics();
		graphics.setSize(screenDimensions.width, screenDimensions.height);
		DimensionPixels screenDimensions = new DimensionPixels(graphics.width(), graphics.height());
		
		FirstLevel firstLevel = new FirstLevel(screenDimensions, this);
		setLevel(firstLevel);		
	}


	private void preloadResources() {
		//TODO: Loading screen
		assets().getImage(BALL_IMAGE);
		assets().getImage(BACKGROUND_IMAGE);
		assets().getImage(GOAL_IMAGE);
	}

	@Override
	public void paint(float delta) {
		level.paint(delta);
	}

	@Override
	public void update(float delta) {
		level.update(delta);
	}

	@Override
	public int updateRate() {
		return 25;
	}

	@Override
	public void setLevel(Level level) {
		if(this.level != null){
			this.level.destroy();
		}
		this.level = level;
		level.init();
	}
}