package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class NullPiece extends Piece {

	public NullPiece()
	{
		type = NULL;
	}
	
	@Override
	public boolean isMoveValid(Cell toCell, Cell fromCell, Cell[][] cells)
	{
		return false;
	}
}
