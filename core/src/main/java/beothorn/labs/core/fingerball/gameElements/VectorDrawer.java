 package beothorn.labs.core.fingerball.gameElements;

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.ImageLayer;
import beothorn.labs.core.fingerball.graphics.GraphicsElement;
import beothorn.labs.core.fingerball.units.DimensionPixels;
import beothorn.labs.core.fingerball.units.PointPixels;
import beothorn.labs.core.fingerball.units.RectanglePixels;

public class VectorDrawer {

	private final GraphicsElement graphicsElement;
	private Canvas canvas;
	private PointPixels lastKnownPointerPosition;
	private PointPixels vectorStartScreenCoords;

	public VectorDrawer(DimensionPixels screenDimensions, GraphicsElement graphicsElement) {
		this.graphicsElement = graphicsElement;
		
		CanvasImage counterImage = graphics().createImage(screenDimensions.width, screenDimensions.height);
		ImageLayer counterLayer = graphics().createImageLayer(counterImage);
		graphics().rootLayer().add(counterLayer);
		canvas = counterImage.canvas();
		canvas.setStrokeColor(Color.rgb(0, 0, 0));
		canvas.setStrokeWidth(3);
		clear();
	}
	
	public void updateVector(PointPixels endVector) {
		this.lastKnownPointerPosition = endVector;
		clear();
		redraw();
	}
	
	public void redraw(){
		if(vectorStartScreenCoords == null){
			return;
		}
		clear();
		RectanglePixels rectangle = graphicsElement.getRectangle();
		float x = rectangle.x+graphicsElement.getOriginX();
		float y = rectangle.y+graphicsElement.getOriginY();
		
		canvas.drawLine(x, y, x+(lastKnownPointerPosition.x-vectorStartScreenCoords.x), y+(lastKnownPointerPosition.y-vectorStartScreenCoords.y));
	}
	
	private void clear(){
		canvas.clear();
	}

	public void clearVector() {
		clear();
		vectorStartScreenCoords = null;
	}

	public void startVector(PointPixels vectorStartScreenCoords) {
		this.vectorStartScreenCoords = vectorStartScreenCoords;
		this.lastKnownPointerPosition = vectorStartScreenCoords;
	}

}
