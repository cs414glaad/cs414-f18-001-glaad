package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

import java.util.ArrayList;

public class Advisor extends Piece {
	
	public Advisor()
	{
		type = ADVISOR;
		isFlipped = false;
		capturableTypes = new ArrayList<String>(advisorCanCapture);
	}
}
