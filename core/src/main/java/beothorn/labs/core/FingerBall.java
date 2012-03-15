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
import pythagoras.f.Point;

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
      public void onPointerEnd(Pointer.Event event) {
    	  Point clickPosition = new Point(event.x(),event.y());
    	  ball.click(clickPosition);
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
		ball.setPosition(position.x*10, position.y*10);
		
	}

	@Override
	public int updateRate() {
		return 25;
	}
}
