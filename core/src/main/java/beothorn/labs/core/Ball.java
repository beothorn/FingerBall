package beothorn.labs.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.ResourceCallback;
import beothorn.fingerball.GameElement;
import beothorn.fingerball.MetersToPixelsConverter;
import beothorn.fingerball.PhysicBody;
import beothorn.fingerball.PhysicBodyImpl;
import beothorn.fingerball.PointMeters;
import beothorn.fingerball.PointPixels;

public class Ball {

	public static String IMAGE = "images/soccerBall.png";

	private GameElement imageToBody;

	public Ball(final World world,
			final MetersToPixelsConverter metersToPixelsConverter,
			final float x, final float y) {
		Image image = assets().getImage(IMAGE);

		image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				final ImageLayer ballImageLayer = graphics().createImageLayer(image);
				float radius = image.width() / 2f;
				ballImageLayer.setOrigin(radius, radius);
				graphics().rootLayer().add(ballImageLayer);
				PointMeters radiusInMeters = metersToPixelsConverter.pixelsToMeters(new PointPixels((int) radius,(int) radius));
				final PhysicBody ballBody = createBallBody(world, x, y,radiusInMeters);
				imageToBody = new GameElement(ballImageLayer, ballBody,metersToPixelsConverter);
				pointer().setListener(new Pointer.Adapter() {
					@Override
					public void onPointerStart(Pointer.Event event) {
						PointPixels clickPosition = new PointPixels((int) event
								.x(), (int) event.y());
						PointMeters pixelsToMeters = metersToPixelsConverter
								.pixelsToMeters(clickPosition);

						float deltaX = imageToBody.getPhisicalX()
								- pixelsToMeters.x;
						Vec2 impulse = new Vec2(deltaX, imageToBody
								.getPhisicalY() - pixelsToMeters.y);
						impulse.normalize();
						Vec2 impulseForce = impulse.mul(0.006f);
						ballBody.applyLinearImpulse(impulseForce);
						ballBody.applyAngularImpulse(impulseForce.x * 0.1f);
					}
				});
			}

			@Override
			public void error(Throwable err) {
				log().error("Error loading image!", err);
			}

			private PhysicBody createBallBody(World world, float x, float y,
					PointMeters radiusInMeters) {
				Body body = createBody(world);
				FixtureDef fixtureDef = createBallFixture(radiusInMeters.x);

				body.createFixture(fixtureDef);

				body.setLinearDamping(0.3f);
				body.setTransform(new Vec2(x, y), 0);

				return new PhysicBodyImpl(body);
			}

			private Body createBody(World world) {
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
		});
	}

	public void update() {
		imageToBody.update();
	}
}
