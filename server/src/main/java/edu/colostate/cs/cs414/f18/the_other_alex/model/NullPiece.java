package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.Piece;

public class NullPiece extends Piece {

	public NullPiece(){
		super();
		type = NULL;
	}
	
	@Override
	public boolean isMoveValid(Cell toCell, Cell fromCell, Cell[][] cells)
	{
		return false;
	}
}
