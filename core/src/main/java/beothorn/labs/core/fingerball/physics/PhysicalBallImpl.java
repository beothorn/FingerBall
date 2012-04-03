package beothorn.labs.core.fingerball.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import beothorn.labs.core.fingerball.units.PointMeters;

public class PhysicalBallImpl implements PhysiscalBall {

	private static final float NORMAL_KICK_MULTIPLIER = 0.006f;
	private static final float LONG_KICK_MULTIPLIER = 0.002f;
	private Body body;

	public PhysicalBallImpl(FingerBallWorld world, float radius, float x, float y) {
		body = createBody(world);
		FixtureDef fixtureDef = createBallFixture(radius);

		body.createFixture(fixtureDef);
		body.setTransform(new Vec2(x, y), 0);
		body.setLinearDamping(0.3f);
	}
	
	private Body createBody(FingerBallWorld world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		Body body = world.createBody(bodyDef);
		return body;
	}
	
	private FixtureDef createBallFixture(float radius) {
		CircleShape circleShape = new CircleShape();
		circleShape.m_radius = radius;

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.density = 0.4f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.5f;
		return fixtureDef;
	}
	
	@Override
	public Vec2 getPosition() {
		Vec2 position = body.getPosition();
		return position;
	}

	@Override
	public void kickAt(PointMeters kickPhysical) {
		kickAt(kickPhysical, NORMAL_KICK_MULTIPLIER);
	}
	
	@Override
	public void longKickAt(PointMeters kickPhysical) {
		kickAt(kickPhysical, LONG_KICK_MULTIPLIER);
	}

	@Override
	public float getAngle() {
		return body.getAngle();
	}
	
	private void kickAt(PointMeters kickPhysical, float kickForceMultiplier) {
		Vec2 position = body.getPosition();
		float deltaX = position.x - kickPhysical.x;
		float deltaY = -Math.abs(position.y - kickPhysical.y);
		Vec2 impulse = new Vec2(deltaX, deltaY);
		impulse.normalize();
		Vec2 impulseForce = impulse.mul(kickForceMultiplier);
		body.applyLinearImpulse(impulseForce,position);
		body.applyAngularImpulse(impulseForce.x * 0.01f);
	}
}
