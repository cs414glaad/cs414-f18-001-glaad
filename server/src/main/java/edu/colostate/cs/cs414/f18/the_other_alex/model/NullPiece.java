package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class NullPiece extends Piece {

	public NullPiece(){
		//setting color to white, when checking for a null piece we'll just check type anyway
		super(PieceColor.WHITE);
		type = NULL;
	}
	
	@Override
	public boolean isMoveValid(Cell toCell, Cell fromCell, Cell[][] cells)
	{
		return false;
	}
}
