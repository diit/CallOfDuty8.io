package game;

import java.util.ArrayList;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.dynamics.joint.DistanceJoint;

public class Snake extends Object{
	
	// Define length
	public int length = 10;
	
	// Define Segments
	public ArrayList<Segment> segments = new ArrayList<Segment>();
	
	public Snake() {
		// Build Head
		Circle head = new Circle(0.35);
		head.translate(0.4, 0.0);
		BodyFixture headFixture = new BodyFixture(head);
		headFixture.setDensity(0.5);
		this.addFixture(headFixture);
		this.setMass(MassType.INFINITE);
		this.applyForce(new Vector2(-1, -0.8));
		this.translate(2.0, 3.0);
		
		// Initiate Segments
		for (int i = 0; i < length; i++) {
			this.segments.add(new Segment(i));
		}
	}
}
