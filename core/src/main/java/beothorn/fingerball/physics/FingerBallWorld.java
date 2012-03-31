package beothorn.fingerball.physics;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import playn.core.Graphics;
import playn.core.Image;
import playn.core.Pointer;
import playn.core.ResourceCallback;
import playn.core.Pointer.Event;
import beothorn.fingerball.Ball;
import beothorn.fingerball.Input;
import beothorn.fingerball.InputListener;
import beothorn.fingerball.graphics.GraphicsBallImpl;
import beothorn.fingerball.units.DimensionMeters;
import beothorn.fingerball.units.DimensionPixels;
import beothorn.fingerball.units.MetersToPixelsConverter;
import beothorn.fingerball.units.PointMeters;
import beothorn.fingerball.units.PointPixels;

public class FingerBallWorld {

	public static String IMAGE = "images/soccerBall.png";
	
	private static final DimensionMeters worldDimension = new DimensionMeters(2.31f,1.73f);
	private static final Vec2 gravity = new Vec2(0.0f, 0.05f);
	private World world;
	private final MetersToPixelsConverter metersToPixels;
	private final Graphics graphics;
	private Ball ball;

	public FingerBallWorld() {
		this.graphics = graphics();
		DimensionPixels screenDimensions = new DimensionPixels(graphics.width(), graphics.height());
		this.metersToPixels = new MetersToPixelsConverter(screenDimensions, worldDimension);
		
		createWorld();
		createBoundaries();
		createBall();
	}

	private void createBall() {
		Image image = assets().getImage(IMAGE);

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
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
		world.setWarmStarting(true);
		world.setAutoClearForces(true);
	}

	private void createBoundaries() {
		createCeiling();
		createFloor();
		createLeftWall();
		createRightWall();
	}

	private void createFloor() {
		createWall(new Vec2(0, worldDimension.height), new Vec2(worldDimension.width, worldDimension.height));
	}

	private void createRightWall() {
		createWall(new Vec2(worldDimension.width, 0), new Vec2(worldDimension.width, worldDimension.height));
	}

	private void createLeftWall() {
		createWall(new Vec2(0, 0), new Vec2(0, worldDimension.height));
	}

	private void createCeiling() {
		createWall(new Vec2(0, 0), new Vec2(worldDimension.width, 0));
	}

	private void createWall(Vec2 start, Vec2 end) {
		Body ground = world.createBody(new BodyDef());
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsEdge(start, end);
		ground.createFixture(groundShape, 0.0f);
	}

	public void update() {
		world.step(0.33f, 10, 10);
		ball.update();
	}
}
