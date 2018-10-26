package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

import java.util.ArrayList;

public class Soldier extends Piece {
	
	public Soldier()
	{
		type = SOLDIER;
		isFlipped = false;
		capturableTypes = new ArrayList<String>(soldierCanCapture);
	}
}
