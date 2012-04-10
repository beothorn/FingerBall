package beothorn.labs.core.fingerball;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Game;
import playn.core.Graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.TextFormat;
import beothorn.labs.core.fingerball.gameElements.BallWithDirection;
import beothorn.labs.core.fingerball.gameElements.ClickableBall;
import beothorn.labs.core.fingerball.graphics.GraphicsBallImpl;
import beothorn.labs.core.fingerball.physics.FingerBallWorld;
import beothorn.labs.core.fingerball.physics.PhysicalClickableBallImpl;
import beothorn.labs.core.fingerball.physics.PhysiscalBallWithDirectionImpl;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;

public class FingerBall implements Game {
	
	private FingerBallWorld world;
	private MetersToPixelsConverter metersToPixels;
	private static final DimensionPixels screenDimensions = new DimensionPixels(800, 600);
	private static final DimensionMeters worldDimension = new DimensionMeters(2.31f,1.73f);
	private static final String BACKGROUND_IMAGE = "images/background.png";
	private static final String BALL_IMAGE = "images/soccerBall.png";
	
	private int kickCount;
	
	private CanvasImage counterImage;
	private KicksCounter kicksCounter = new KicksCounter(new KickRecordBreakListener() {
		@Override
		public void newRecord(int newRecord) {
			kickCount = newRecord;
		}
	});
	private UpdaterImpl updater;
	private PhysicsToGraphicsPositionUpdater physicsToGraphicsPositionUpdater;

	@Override
	public void init() {
		Graphics graphics = graphics();
		graphics.setSize(screenDimensions.width, screenDimensions.height);
		DimensionPixels screenDimensions = new DimensionPixels(graphics.width(), graphics.height());
		this.metersToPixels = new MetersToPixelsConverter(screenDimensions, worldDimension);
		updater = new UpdaterImpl();
		UpdaterPointerEventQueuer updaterPointerEventQueuer = new UpdaterPointerEventQueuer(updater);
		physicsToGraphicsPositionUpdater = new PhysicsToGraphicsPositionUpdater(metersToPixels);
		pointer().setListener(updaterPointerEventQueuer);
		preloadResources();
		
		createBackground();
		createWorld();
		createBall();
		createHUD(screenDimensions);
	}

	private void createHUD(DimensionPixels screenDimensions) {
		counterImage = graphics().createImage(screenDimensions.width, screenDimensions.height);
		ImageLayer counterLayer = graphics().createImageLayer(counterImage);
		graphics().rootLayer().add(counterLayer);
		drawText("Do some kickups");
	}

	private void drawText(String text) {
		Canvas canvas = counterImage.canvas();
		canvas.clear();
		canvas.drawText(graphics().layoutText(text,new TextFormat()), 10, 20);
	}

	private void createBall() {
		Image image = assets().getImage(BALL_IMAGE);

		image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				float imageRadius = image.width() / 2f;
				PointMeters radiusInMeters = metersToPixels.pixelsToMeters(new PointPixels((int) imageRadius,(int) imageRadius));
				
				PhysicalClickableBallImpl physicalBall = new PhysicalClickableBallImpl(world,kicksCounter, radiusInMeters.x, 1.0f, 1.0f);
				GraphicsBallImpl graphicsBall = new GraphicsBallImpl(image);
				physicsToGraphicsPositionUpdater.registerToUpdate(graphicsBall, physicalBall);
				ClickableBall ball = new ClickableBall(physicalBall,graphicsBall,metersToPixels);
				updater.add(ball);
				
				
				PhysiscalBallWithDirectionImpl physiscalBallWithDirectionImpl = new PhysiscalBallWithDirectionImpl(world, radiusInMeters.x, 0.5f, 1.0f);
				GraphicsBallImpl graphicsBall2 = new GraphicsBallImpl(image);
				physicsToGraphicsPositionUpdater.registerToUpdate(graphicsBall2, physiscalBallWithDirectionImpl);
				BallWithDirection ballWithDirection = new BallWithDirection(physiscalBallWithDirectionImpl, metersToPixels);
				updater.add(ballWithDirection);
				
			}
			@Override
			public void error(Throwable err) {
				log().error("Error loading image!", err);
			}
			
		});
	}
	
	private void createWorld() {
		world = new FingerBallWorld(worldDimension, kicksCounter);
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
		String text = "Best "+kickCount;
		drawText(text);
	}

	@Override
	public void update(float delta) {
		world.update();
		updater.update(delta);
		physicsToGraphicsPositionUpdater.update();
	}

	@Override
	public int updateRate() {
		return 25;
	}
}