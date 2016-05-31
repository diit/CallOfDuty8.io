package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;

import com.Graphics2DRenderer;

/**
 * Custom Body class to add drawing functionality.
 * @author William Bittle
 * @version 3.0.2
 * @since 3.0.0
 */
public class Object extends Body {
	/** The color of the object */
	protected Color color;
	
	/**
	 * Default constructor.
	 */
	public Object() {
		// randomly generate the color
		this.color = new Color(
				(float)Math.random() * 0.5f + 0.5f,
				(float)Math.random() * 0.5f + 0.5f,
				(float)Math.random() * 0.5f + 0.5f);
	}
	
	/**
	 * Draws the body.
	 * <p>
	 * Only coded for polygons and circles.
	 * @param g the graphics object to render to
	 */
	public void render(Graphics2D g) {
		// save the original transform
		AffineTransform ot = g.getTransform();
		
		// transform the coordinate system from world coordinates to local coordinates
		AffineTransform lt = new AffineTransform();
		lt.translate(this.transform.getTranslationX() * com.ExampleGraphics2D.SCALE, this.transform.getTranslationY() * com.ExampleGraphics2D.SCALE);
		lt.rotate(this.transform.getRotation());
		
		// apply the transform
		g.transform(lt);
		
		// loop over all the body fixtures for this body
		for (BodyFixture fixture : this.fixtures) {
			// get the shape on the fixture
			Convex convex = fixture.getShape();
			Graphics2DRenderer.render(g, convex, com.ExampleGraphics2D.SCALE, color);
		}
		
		// set the original transform
		g.setTransform(ot);
	}
}
