package com;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.dyn4j.collision.narrowphase.Penetration;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.CollisionAdapter;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.joint.AngleJoint;
import org.dyn4j.dynamics.joint.DistanceJoint;
import org.dyn4j.dynamics.joint.PinJoint;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.dynamics.joint.RopeJoint;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import game.Segment;

/**
 * A simple scene showing how to capture mouse input and create
 * bodies dynamically.
 * @author William Bittle
 * @version 3.2.1
 * @since 3.2.0
 */
public class COD8 extends Frame {
	/** The serial version id */
	private static final long serialVersionUID = -1366264828445805140L;

	/** A point for tracking the mouse click */
	private Point point;
	
	/** Tracks if clicked */
	private boolean clicked;
	
	/** Local snake */
	private game.Snake slither;
	
	/** Head of snake */
	Segment last;
	
	/** Mouse Interaction Joint */
	private PinJoint pj;
	
	/** Camera */
	private static Camera camera;

	/**
	 * A custom mouse adapter for listening for mouse clicks.
	 * @author William Bittle
	 * @version 3.2.1
	 * @since 3.2.0
	 */
	private final class CustomMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			clicked = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			clicked = false;
		}
		
		public void mouseMoved(MouseEvent e) {
			point = new Point(e.getX(), e.getY());
		}
	}
	
	/**
	 * Custom contact listener to stop the bodies.
	 * @author William Bittle
	 * @version 3.2.0
	 * @since 3.2.0
	 */
	private static class StopContactListener extends CollisionAdapter {
		private Body b1, b2;
		
		public StopContactListener(Body b1, Body b2) {
			this.b1 = b1;
			this.b2 = b2;
		}
		@Override
		public boolean collision(Body body1, BodyFixture fixture1, Body body2, BodyFixture fixture2, Penetration penetration) {
			return false;
		}
	}

	/**
	 * Default constructor.
	 */
	public COD8() {
		super("Call of Duty 8", 32.0);

		MouseAdapter ml = new CustomMouseAdapter();
		this.canvas.addMouseMotionListener(ml);
		this.canvas.addMouseWheelListener(ml);
		this.canvas.addMouseListener(ml);
		
		// Add Camera
		setCamera(new Camera());
	}
	
	public static Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld() {
		this.world.setGravity(World.ZERO_GRAVITY);

		// Test Snake
		slither = new game.Snake();

		// Build Body
		last = null;
		int index = 0;
		for (Segment segment : slither.segments) {
			this.world.addBody(segment);
			if (last != null) {
				RevoluteJoint rj = new RevoluteJoint(last, segment, new Vector2(-0.1-(0.2*index),0));
				this.world.addJoint(rj);
			}
			last = segment;
			index++;
		}
		
		// Add Mouse Joint
		last.color = Color.red;
//		pj = new PinJoint(last, new Vector2(0, 0), 0.5, 0.0, 0.2);
//		pj.setDampingRatio(0.1);
//		this.world.addJoint(pj);
		
		// Add collision listeners
		for (Segment segment : slither.segments) {
//			this.world.addListener(new StopContactListener(last, segment));
		}
		
		last.getFixture(0).setDensity(100);
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.samples.SimulationFrame#update(java.awt.Graphics2D, double)
	 */
	@Override
	protected void update(Graphics2D g, double elapsedTime) {

		// see if the user clicked
		if (this.point != null) {
			// convert from screen space to world space coordinates
			double x =  (this.point.getX() - this.canvas.getWidth() / 2.0) / this.scale;
			double y = -(this.point.getY() - this.canvas.getHeight() / 2.0) / this.scale;
			
			double mod = 0;
			
			if (this.clicked) {
				// Supercharge
				mod = 0.1;
			}else {
				mod = 0.01;
			}
			
			Vector2 force = new Vector2((mod)*x, (mod)*y);
			
			System.out.println("Force on Snake :"+ force);
			
			last.setLinearVelocity(new Vector2(0,0));
			last.applyForce(force);
//			this.camera.translate(new Vector2((mod)*x*-1, (mod)*y*-1));
			
		}

		super.update(g, elapsedTime);
	}

	/**
	 * Entry point for the example application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		COD8 simulation = new COD8();
		simulation.run();
	}
}