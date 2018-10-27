package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;

public class Horse extends Piece {
	
	public Horse(PieceColor color){
		super(color);
		type = HORSE;
		capturableTypes = new ArrayList<String>(horseCanCapture);
	}
}

