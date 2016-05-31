package game;

import org.dyn4j.geometry.Circle;

public class Snake extends Object{
	
	// Define length
	public int length = 10;
	
	public Circle[] getSegments() {
		
		Circle[] segments = new Circle[length];
		
		for (int i = 0; i < this.length; i++) {
			Circle segment = new Circle(0.2);
			segment.translate(-0.3*i, 0.0);
			segments[i] = segment;
		}
		
		return segments;
	}
}
