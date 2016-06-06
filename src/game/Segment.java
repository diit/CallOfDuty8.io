package game;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class Segment extends Object {
	
	public Segment(int index) {
		Circle segment = new Circle(0.2);
		segment.translate(-0.5*index, 0.0);
		BodyFixture segmentFixture = new BodyFixture(segment);
		segmentFixture.setDensity(0.2);
		this.addFixture(segmentFixture);
		this.setMass(MassType.NORMAL);
		this.translate(2.0, 3.0);
	}

}
