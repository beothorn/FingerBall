package beothorn.labs.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import beothorn.fingerball.DimensionPixels;

public class FingerBall implements Game {
	private FingerBallWorld world;
	private static final DimensionPixels screenDimensions = new DimensionPixels(800, 600);

	@Override
	public void init() {
		graphics().setSize(screenDimensions.width, screenDimensions.height);
		createBackground();
		preloadResources();
		world = new FingerBallWorld();
	}

	private void preloadResources() {
		assets().getImage(Ball.IMAGE);
	}

	private void createBackground() {
		Image bgImage = assets().getImage("images/background.png");
		ImageLayer bgLayer = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(bgLayer);
	}

	@Override
	public void paint(float alpha) {
		// the background automatically paints itself, so no need to do anything
		// here!
	}

	@Override
	public void update(float delta) {
		world.update();
	}

	@Override
	public int updateRate() {
		return 25;
	}
}
