package beothorn.labs.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

public class FingerBall implements Game {
	private GroupLayer ballLayer;
	private Ball ball;
	private FingerBallWorld world;
	private Body physicsBall;

	@Override
  public void init() {
	graphics().setSize(800, 600);
    createBackground();
    createBallLayer();
    createBall();
    preloadResources();

    pointer().setListener(new Pointer.Adapter() {
      @Override
      public void onPointerStart(Pointer.Event event) {    	  
    	  float x = physicsBall.getPosition().x - (event.x()/10);
    	  float y = physicsBall.getPosition().y - (event.y()/10);
    	  Vec2 impulse = new Vec2(x, y);
    	  impulse.normalize();
    	  Vec2 impulseForce = impulse.mul(500);
    	  physicsBall.applyLinearImpulse(impulseForce, physicsBall.getPosition());
    	  physicsBall.applyAngularImpulse(x);
      }
    });
    
    world = new FingerBallWorld(80,60);
    physicsBall = world.createBall(30, 30);
  }

	private void createBall() {
		ball = new Ball(ballLayer, 150, 150);
	}

	private void createBallLayer() {
		ballLayer = graphics().createGroupLayer();
		graphics().rootLayer().add(ballLayer);
	}

	private void preloadResources() {
		assets().getImage(Ball.IMAGE);
	}

	private void createBackground() {
		Image bgImage = assets().getImage("images/background.png");
		ImageLayer bgLayer = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(bgLayer);
	}

	@Override
	public void paint(float alpha) {
		// the background automatically paints itself, so no need to do anything
		// here!
	}

	@Override
	public void update(float delta) {
		ball.update(delta);
		world.update();
		Vec2 position = physicsBall.getPosition();
		ball.setPositionAndRotation(position.x*10, position.y*10,physicsBall.getAngle());
		
	}

	@Override
	public int updateRate() {
		return 25;
	}
}
