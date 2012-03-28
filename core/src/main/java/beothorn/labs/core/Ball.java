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
import beothorn.fingerball.Input;
import beothorn.fingerball.InputListener;
import beothorn.fingerball.MetersToPixelsConverter;
import beothorn.fingerball.PhysicalBody;
import beothorn.fingerball.PhysicBodyImpl;
import beothorn.fingerball.PhysiscalBall;
import beothorn.fingerball.PointMeters;
import beothorn.fingerball.PointPixels;

public class Ball {

	public static String IMAGE = "images/soccerBall.png";

	private GameElement gameElement;
	
	public Ball(final PhysiscalBall physicalBall, Input input, final MetersToPixelsConverter metersToPixelsConverter) {
		input.setListener(new InputListener() {
			
			@Override
			public void kickAt(PointPixels kick) {
				PointMeters kickPhysical = metersToPixelsConverter.pixelsToMeters(kick);
				physicalBall.kickAt(kickPhysical);
			}
		});
	}

	public Ball(final World world,
			final MetersToPixelsConverter metersToPixelsConverter,
			final float x, final float y) {
		Image image = assets().getImage(IMAGE);

		image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				
				float imageRadius = image.width() / 2f;
				
				final ImageLayer ballImageLayer = createImageLayer(image);
				
				PointMeters radiusInMeters = metersToPixelsConverter.pixelsToMeters(new PointPixels((int) imageRadius,(int) imageRadius));
				final PhysicalBody ballBody = createPhysicalBody(world, x, y,radiusInMeters);
				
				gameElement = new GameElement(ballImageLayer, ballBody, metersToPixelsConverter);
				pointer().setListener(new Pointer.Adapter() {@Override public void onPointerStart(Pointer.Event event) {
						PointPixels clickPosition = new PointPixels((int) event.x(), (int) event.y());
						gameElement.click(clickPosition);
				}});				
			}

			private ImageLayer createImageLayer(Image image) {
				final ImageLayer ballImageLayer = graphics().createImageLayer(image);
				ballImageLayer.setOrigin(image.width() / 2f, image.width() / 2f);
				graphics().rootLayer().add(ballImageLayer);
				return ballImageLayer;
			}

			@Override
			public void error(Throwable err) {
				log().error("Error loading image!", err);
			}

			private PhysicalBody createPhysicalBody(World world, float x, float y,
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
		gameElement.update();
	}
}
