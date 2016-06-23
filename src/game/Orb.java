package game;

import java.awt.Color;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class Orb extends Object {
	
	public Orb(Vector2 coords) {
		Circle segment = new Circle(0.15);
		BodyFixture segmentFixture = new BodyFixture(segment);
		this.addFixture(segmentFixture);
		this.setMass(MassType.INFINITE);
		this.translate(coords);
		this.color = new Color((int) ((Math.random() * 255) % 255), (int) ((Math.random() * 255) % 255), (int) ((Math.random() * 255) % 255));
	}

}