package beothorn.labs.core.fingerball.physics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import beothorn.labs.core.fingerball.KicksCounter;
import beothorn.labs.core.fingerball.units.DimensionMeters;

public class FingerBallWorld {
	
	private final DimensionMeters worldDimension;
	private static final Vec2 gravity = new Vec2(0.0f, 0.05f);
	private World world;
	private Fixture floor;
	private final KicksCounter kicksCounter;

	public FingerBallWorld(DimensionMeters worldDimension, KicksCounter kicksCounter) {
		this.worldDimension = worldDimension;
		this.kicksCounter = kicksCounter;
		createWorld();
		createBoundaries();
	}

	private void createWorld() {
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
		world.setWarmStarting(true);
		world.setAutoClearForces(true);
		world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}
			
			@Override
			public void endContact(Contact contact) {
			}
			
			@Override
			public void beginContact(Contact contact) {
				if(contact.getFixtureA() == floor || contact.getFixtureA() == floor)
					kicksCounter.hitTheGround();
			}
		});
	}

	private void createBoundaries() {
		createCeiling();
		createFloor();
		createLeftWall();
		createRightWall();
	}

	private void createFloor() {
		floor = createWall(new Vec2(0, worldDimension.height), new Vec2(worldDimension.width, worldDimension.height));
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

	private Fixture createWall(Vec2 start, Vec2 end) {
		Body ground = world.createBody(new BodyDef());
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsEdge(start, end);
		return ground.createFixture(groundShape, 0.0f);
	}

	public void update() {
		world.step(0.33f, 10, 10);
	}

	public Body createBody(BodyDef bodyDef) {
		return world.createBody(bodyDef);
	}
}
