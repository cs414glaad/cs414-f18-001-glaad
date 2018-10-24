package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

import java.util.ArrayList;

public class General extends Piece {

	
	public General()
	{
		type = GENERAL;
		capturableTypes = new ArrayList<String>(generalCanCapture);
	}

}
