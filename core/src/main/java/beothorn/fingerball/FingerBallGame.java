package beothorn.fingerball;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;
import playn.core.Game;
import playn.core.Graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.ResourceCallback;
import playn.core.Pointer.Event;
import beothorn.fingerball.graphics.GraphicsBallImpl;
import beothorn.fingerball.physics.FingerBallWorld;
import beothorn.fingerball.physics.PhysicalBallImpl;
import beothorn.fingerball.units.DimensionMeters;
import beothorn.fingerball.units.DimensionPixels;
import beothorn.fingerball.units.MetersToPixelsConverter;
import beothorn.fingerball.units.PointMeters;
import beothorn.fingerball.units.PointPixels;

public class FingerBallGame implements Game {
	
	private FingerBallWorld world;
	private MetersToPixelsConverter metersToPixels;
	private static final DimensionPixels screenDimensions = new DimensionPixels(800, 600);
	private static final DimensionMeters worldDimension = new DimensionMeters(2.31f,1.73f);
	private static final String BACKGROUND_IMAGE = "images/background.png";
	private static final String BALL_IMAGE = "images/soccerBall.png";
	private Ball ball;

	@Override
	public void init() {
		Graphics graphics = graphics();
		graphics.setSize(screenDimensions.width, screenDimensions.height);
		DimensionPixels screenDimensions = new DimensionPixels(graphics.width(), graphics.height());
		this.metersToPixels = new MetersToPixelsConverter(screenDimensions, worldDimension);
		createBackground();
		preloadResources();
		createWorld();
		createBall();
	}


	private void createBall() {
		Image image = assets().getImage(BALL_IMAGE);

		image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				float imageRadius = image.width() / 2f;
				PointMeters radiusInMeters = metersToPixels.pixelsToMeters(new PointPixels((int) imageRadius,(int) imageRadius));
				PhysicalBallImpl physicalBall = new PhysicalBallImpl(world, radiusInMeters.x, 1.0f, 1.0f);
				
				GraphicsBallImpl graphicsBall = new GraphicsBallImpl(image);
				
				Input input = new Input(){@Override	public void setListener(final InputListener inputListener) { pointer().setListener(new Pointer.Listener() {
							
					@Override
					public void onPointerStart(Event event) {
						PointPixels kick = new PointPixels((int)event.x(), (int)event.y());
						inputListener.kickAt(kick);
					}
							
					@Override
					public void onPointerEnd(Event event) {
					}
							
					@Override
					public void onPointerDrag(Event event) {
					}
					
				});}};
				
				ball = new Ball(physicalBall,graphicsBall,input,metersToPixels);
			}

			@Override
			public void error(Throwable err) {
				log().error("Error loading image!", err);
			}
			
		});
	}
	
	private void createWorld() {
		world = new FingerBallWorld(worldDimension);
	}

	private void preloadResources() {
		assets().getImage(BALL_IMAGE);
		assets().getImage(BACKGROUND_IMAGE);
	}

	private void createBackground() {
		Image bgImage = assets().getImage(BACKGROUND_IMAGE);
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
		ball.update();
	}

	@Override
	public int updateRate() {
		return 25;
	}
}
