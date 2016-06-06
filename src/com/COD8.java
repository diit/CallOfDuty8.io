package com;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

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
	
	/**
	 * A custom mouse adapter for listening for mouse clicks.
	 * @author William Bittle
	 * @version 3.2.1
	 * @since 3.2.0
	 */
	private final class CustomMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			// store the mouse click postion for use later
			point = new Point(e.getX(), e.getY());
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			point = null;
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
	}
	
	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld() {
		game.Object floor = new game.Object();
	    floor.addFixture(Geometry.createRectangle(20, 1));
	    floor.setMass(MassType.INFINITE);
	    this.world.addBody(floor);
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
			
			// create a new body
			game.Object no = new game.Object();
			no.addFixture(Geometry.createSquare(0.5));
			no.translate(x, y);
			no.setMass(MassType.NORMAL);
			this.world.addBody(no);
			
			// clear the point
			this.point = null;
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