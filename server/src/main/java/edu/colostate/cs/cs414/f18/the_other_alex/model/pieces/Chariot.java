package edu.colostate.cs.cs414.f18.the_other_alex.model.pieces;

import edu.colostate.cs.cs414.f18.the_other_alex.model.PieceColor;

import java.util.ArrayList;

public class Chariot extends Piece {
	
	public Chariot(PieceColor color){
		super(color);
		capturableTypes = new ArrayList<String>(chariotCanCapture);
		type = CHARIOT;
	}
}

