package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class Cell {

	private Coordinate coord;
	private Piece piece;
	
	//done like this to make more sense with arrays, since cells[1][2] is 1st row 2nd column, which is 
	// 2 x one y
	public Cell(int y, int x, Piece p)
	{
		coord = new Coordinate(x, y);
		piece = p;
	}
	
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	public Coordinate getCoordinate()
	{
		return coord;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
}
