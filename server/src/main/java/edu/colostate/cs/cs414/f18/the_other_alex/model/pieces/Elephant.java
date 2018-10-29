package edu.colostate.cs.cs414.f18.the_other_alex.model.pieces;

import edu.colostate.cs.cs414.f18.the_other_alex.model.PieceColor;

import java.util.ArrayList;

public class Elephant extends Piece {
	
	public Elephant(PieceColor color){
		super(color);
		type = ELEPHANT;
		capturableTypes = new ArrayList<String>(elephantCanCapture);
	}
}
