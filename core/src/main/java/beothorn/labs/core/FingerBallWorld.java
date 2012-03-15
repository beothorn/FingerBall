package beothorn.labs.core;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class FingerBallWorld {

	private static final int BALL_RADIUS = 3;
	private World world;

	public FingerBallWorld(float width, float height) {
		createWorld();
		createGround(width, height);
	}

	public Body createBall(float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position = new Vec2(0, 0);
		Body body = world.createBody(bodyDef);

		CircleShape circleShape = new CircleShape();
		circleShape.m_radius = BALL_RADIUS;
		circleShape.m_p.set(0, 0);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.density = 0.4f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.35f;
		
		body.createFixture(fixtureDef);
//		body.setUserData(data)
		body.setLinearDamping(0.3f);
		body.setTransform(new Vec2(x, y), 0);
		return body;
	}

	private void createWorld() {
		Vec2 gravity = new Vec2(0.0f, 1.0f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
		world.setWarmStarting(true);
		world.setAutoClearForces(true);
	}

	private void createGround(float width, float height) {
		Body ground = world.createBody(new BodyDef());
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsEdge(new Vec2(0, height), new Vec2(width, height));
		ground.createFixture(groundShape, 0.0f);

	}

	public void update() {
		world.step(0.33f, 10, 10);
	}
}
