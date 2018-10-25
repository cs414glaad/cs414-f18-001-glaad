package edu.colostate.cs.cs414.f18.the_other_alex.server.Model;

import java.util.ArrayList;

public class Cannon extends Piece {

	public Cannon() {
		type = CANNON;
		capturableTypes = new ArrayList<String>(cannonCanCapture);
	}

}
