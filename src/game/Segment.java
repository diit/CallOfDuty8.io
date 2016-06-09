package game;

import java.awt.Color;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class Segment extends Object {
	
	public Segment(int index) {
		Circle segment = new Circle(0.2);
		BodyFixture segmentFixture = new BodyFixture(segment);
		segmentFixture.setDensity(0.1);
		this.addFixture(segmentFixture);
		this.setMass(MassType.NORMAL);
		this.translate(new Vector2(-0.1-(0.2*index),0));
		this.color = Color.green;
	}

}