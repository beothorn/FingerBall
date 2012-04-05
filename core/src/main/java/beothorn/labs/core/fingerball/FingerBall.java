package beothorn.labs.core.fingerball;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;

import java.util.ArrayList;
import java.util.List;

import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Game;
import playn.core.Graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;
import playn.core.ResourceCallback;
import playn.core.TextFormat;
import beothorn.labs.core.fingerball.events.GameEvent;
import beothorn.labs.core.fingerball.events.PointerStartEvent;
import beothorn.labs.core.fingerball.graphics.GraphicsBallImpl;
import beothorn.labs.core.fingerball.physics.FingerBallWorld;
import beothorn.labs.core.fingerball.physics.PhysicalBallImpl;
import beothorn.labs.core.fingerball.units.DimensionMeters;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.MetersToPixelsConverter;
import beothorn.labs.core.fingerball.units.PointMeters;
import beothorn.labs.core.fingerball.units.PointPixels;

public class FingerBall implements Game, Listener {
	
	private FingerBallWorld world;
	private MetersToPixelsConverter metersToPixels;
	private static final DimensionPixels screenDimensions = new DimensionPixels(800, 600);
	private static final DimensionMeters worldDimension = new DimensionMeters(2.31f,1.73f);
	private static final String BACKGROUND_IMAGE = "images/background.png";
	private static final String BALL_IMAGE = "images/soccerBall.png";
	
	private Ball ball;
	private int kickCount;
	
	private CanvasImage counterImage;
	private KicksCounter kicksCounter = new KicksCounter(new KickRecordBreakListener() {
		@Override
		public void newRecord(int newRecord) {
			kickCount = newRecord;
		}
	});
	private List<GameEvent> eventQueue;

	@Override
	public void init() {
		Graphics graphics = graphics();
		graphics.setSize(screenDimensions.width, screenDimensions.height);
		DimensionPixels screenDimensions = new DimensionPixels(graphics.width(), graphics.height());
		this.metersToPixels = new MetersToPixelsConverter(screenDimensions, worldDimension);
		eventQueue = new ArrayList<GameEvent>();
		createBackground();
		preloadResources();
		createWorld();
		createBall();
		createHUD(screenDimensions);
		pointer().setListener(this);
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
				PhysicalBallImpl physicalBall = new PhysicalBallImpl(world,kicksCounter, radiusInMeters.x, 1.0f, 1.0f);
				
				GraphicsBallImpl graphicsBall = new GraphicsBallImpl(image);
				
				ball = new Ball(physicalBall,graphicsBall,metersToPixels);
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
		ball.update(delta,eventQueue);
		eventQueue.clear();
	}

	@Override
	public int updateRate() {
		return 25;
	}

	@Override
	public void onPointerStart(Event event) {
		queueEvent(new PointerStartEvent(event));
	}

	@Override
	public void onPointerEnd(Event event) {
	}

	@Override
	public void onPointerDrag(Event event) {
	}
	
	private void queueEvent(PointerStartEvent pointerStartEvent) {
		eventQueue.add(pointerStartEvent);
	}
}