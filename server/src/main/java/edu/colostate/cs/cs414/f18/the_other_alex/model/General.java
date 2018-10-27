package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;

public class General extends Piece {

	
	public General(PieceColor color){
		super(color);
		type = GENERAL;
		capturableTypes = new ArrayList<String>(generalCanCapture);
	}

}
