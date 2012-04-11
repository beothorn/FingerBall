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
import beothorn.labs.core.fingerball.gameElements.VectorDrawerImpl;
import beothorn.labs.core.fingerball.graphics.GraphicsBallImpl;
import beothorn.labs.core.fingerball.graphics.GraphicsElementImpl;
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
	private static final String GOAL_IMAGE = "images/goal.png";
	
	
	private GraphicsElementImpl goal;
	private GraphicsBallImpl vectorBallGraphics;
	
	private CanvasImage counterImage;
	private KicksCounter kicksCounter = new KicksCounter(new KickRecordBreakListener() {
		@Override
		public void newRecord(int newRecord) {
			drawText("Best "+newRecord);
		}
	});
	private UpdaterImpl updater;
	private PhysicsToGraphicsPositionUpdater physicsToGraphicsPositionUpdater;
	private String text;

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
		Image goalImage = assets().getImage(GOAL_IMAGE);

		goalImage.addCallback(new ResourceCallback<Image>() {


			@Override
			public void done(Image resource) {
				goal = new GraphicsElementImpl(resource, 100, 100);
			}

			@Override
			public void error(Throwable err) {
				log().error("Error loading image!", err);
			}
			
		});
		
		createHUD(screenDimensions);
	}

	private void createHUD(DimensionPixels screenDimensions) {
		counterImage = graphics().createImage(screenDimensions.width, screenDimensions.height);
		ImageLayer counterLayer = graphics().createImageLayer(counterImage);
		graphics().rootLayer().add(counterLayer);
		drawText("Do some kickups");
	}

	private void drawText(String text){
		this.text = text;
	}
	
	private void drawText() {
		Canvas canvas = counterImage.canvas();
		canvas.clear();
		canvas.drawText(graphics().layoutText(text,new TextFormat()), 10, 20);
	}

	private void createBall() {
		Image ballmage = assets().getImage(BALL_IMAGE);

		ballmage.addCallback(new ResourceCallback<Image>() {
			
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
				vectorBallGraphics = new GraphicsBallImpl(image);
				physicsToGraphicsPositionUpdater.registerToUpdate(vectorBallGraphics, physiscalBallWithDirectionImpl);
				VectorDrawerImpl vectorDrawer = new VectorDrawerImpl(screenDimensions,vectorBallGraphics);
				BallWithDirection ballWithDirection = new BallWithDirection(physiscalBallWithDirectionImpl,vectorDrawer, metersToPixels);
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
		assets().getImage(GOAL_IMAGE);
	}

	private void createBackground() {
		Image bgImage = assets().getImage(BACKGROUND_IMAGE);
		ImageLayer bgLayer = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(bgLayer);
	}

	@Override
	public void paint(float alpha) {
		drawText();
	}

	@Override
	public void update(float delta) {
		world.update();
		updater.update(delta);
		physicsToGraphicsPositionUpdater.update();
		if(goal.getRectangle().intersects(vectorBallGraphics.getRectangle())){
			drawText("Win");
		}
	}

	@Override
	public int updateRate() {
		return 25;
	}
}