package edu.colostate.cs.cs414.f18.the_other_alex.model.pieces;

import edu.colostate.cs.cs414.f18.the_other_alex.model.PieceColor;

import java.util.ArrayList;

public class Advisor extends Piece {
	
	public Advisor(PieceColor color){
		super(color);
		type = ADVISOR;
		capturableTypes = new ArrayList<String>(advisorCanCapture);
	}
}
