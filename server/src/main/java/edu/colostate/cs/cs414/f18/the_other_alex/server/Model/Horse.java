package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

import java.util.ArrayList;

public class Horse extends Piece {
	
	public Horse()
	{
		type = HORSE;
		capturableTypes = new ArrayList<String>(horseCanCapture);
	}
}

