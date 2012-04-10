package beothorn.labs.core.fingerball.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class PhysiscalBallWithDirectionImpl implements PhysiscalBallWithDirection {

	static final float MAX_FORCE = 0.01f;
	private Body body;
	
	public PhysiscalBallWithDirectionImpl(FingerBallWorld world,float radius, float x, float y) {
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
	public float getAngle() {
		return body.getAngle();
	}

	@Override
	public void applyForce(Vec2 force) {
		force.mulLocal(0.05f);
		float length = force.length();
		if(length > MAX_FORCE){
			force.normalize();
			force.mulLocal(MAX_FORCE);
		}
		Vec2 position = body.getPosition();
		body.applyLinearImpulse(force,position);
		body.applyAngularImpulse(force.x * MAX_FORCE);
	}

}
