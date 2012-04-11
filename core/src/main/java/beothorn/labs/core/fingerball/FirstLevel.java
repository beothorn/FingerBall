package beothorn.labs.core.fingerball;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.TextFormat;
import beothorn.labs.core.fingerball.gameElements.BallWithDirection;
import beothorn.labs.core.fingerball.gameElements.ClickableBall;
import beothorn.labs.core.fingerball.gameElements.VectorDrawerImpl;
import beothorn.labs.core.fingerball.graphics.GraphicsBallImpl;
import beothorn.labs.core.fingerball.graphics.GraphicsElementImpl;
import beothorn.labs.core.fingerball.level.Level;
import beothorn.labs.core.fingerball.level.LevelChangeListener;
import beothorn.labs.core.fingerball.physics.FingerBallWorld;
import beothorn.labs.core.fingerball.physics.PhysicalClickableBallImpl;
import beothorn.labs.core.fingerball.physics.PhysiscalBallWithDirectionImpl;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.update.UpdaterImpl;
import beothorn.labs.core.fingerball.update.UpdaterPointerEventQueuer;

public class FirstLevel implements Level {

	private FingerBallWorld world;
	private MetersToPixelsConverter metersToPixels;
	private static final DimensionMeters worldDimension = new DimensionMeters(2.31f,1.73f);
	
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
	private final DimensionPixels screenDimensions;
	private final LevelChangeListener fingerBall;
	
	private ImageLayer background;
	private GraphicsBallImpl graphicsBall;
	
	public FirstLevel(DimensionPixels screenDimensions, LevelChangeListener fingerBall) {
		this.screenDimensions = screenDimensions;
		this.fingerBall = fingerBall;
	}

	@Override
	public void init() {
		this.metersToPixels = new MetersToPixelsConverter(screenDimensions, worldDimension);
		updater = new UpdaterImpl();
		UpdaterPointerEventQueuer updaterPointerEventQueuer = new UpdaterPointerEventQueuer(updater);
		physicsToGraphicsPositionUpdater = new PhysicsToGraphicsPositionUpdater(metersToPixels);
		pointer().setListener(updaterPointerEventQueuer);
		
		
		createBackground();
		createWorld();
		createBall();
		Image goalImage = assets().getImage(FingerBall.GOAL_IMAGE);
		goal = new GraphicsElementImpl(goalImage, 100, 100);
		
		createHUD(screenDimensions);
	}

	@Override
	public void destroy() {
		background.destroy();
		vectorBallGraphics.destroy();
		graphicsBall.destroy();
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
		Image image = assets().getImage(FingerBall.BALL_IMAGE);
		
		float imageRadius = image.width() / 2f;
		PointMeters radiusInMeters = metersToPixels.pixelsToMeters(new PointPixels((int) imageRadius,(int) imageRadius));
		
		PhysicalClickableBallImpl physicalBall = new PhysicalClickableBallImpl(world,kicksCounter, radiusInMeters.x, 1.0f, 1.0f);
		graphicsBall = new GraphicsBallImpl(image);
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
	
	private void createWorld() {
		world = new FingerBallWorld(worldDimension, kicksCounter);
	}

	private void createBackground() {
		Image bgImage = assets().getImage(FingerBall.BACKGROUND_IMAGE);
		background = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(background);
	}

	@Override
	public void paint(float delta) {
		drawText();
	}

	@Override
	public void update(float delta) {
		world.update();
		updater.update(delta);
		physicsToGraphicsPositionUpdater.update();
		if(goal.getRectangle().intersects(vectorBallGraphics.getRectangle())){
			fingerBall.setLevel(new FirstLevel(screenDimensions, fingerBall));
		}
	}

}
