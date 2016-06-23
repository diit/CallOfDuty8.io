package game;

import java.util.ArrayList;

public class Snake{
	
	// Define length
	public int length = 100;
	
	// Define Segments
	public ArrayList<Segment> segments = new ArrayList<Segment>();
	
	public Snake() {
		
		// Initiate Segments
		for (int i = 0; i < length; i++) {
			this.segments.add(new Segment(i));
		}
	}
}