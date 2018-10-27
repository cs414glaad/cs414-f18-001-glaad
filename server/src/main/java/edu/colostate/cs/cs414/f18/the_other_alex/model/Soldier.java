package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;

public class Soldier extends Piece {
	
	public Soldier(PieceColor color){
		super(color);
		type = SOLDIER;
		capturableTypes = new ArrayList<String>(soldierCanCapture);
	}
}
