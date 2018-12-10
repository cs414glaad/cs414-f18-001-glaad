package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.Piece;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Cell implements Serializable {

	private Coordinate coord;
	private Piece piece;
	private static final long serialVersionUID = 3L;
	
	//done like this to make more sense with arrays, since cells[1][2] is 1st row 2nd column, which is 
	// 2 x one y
	//maybe just call this row and column then? Do the same in coordinate?
	public Cell(int row, int col, Piece p)
	{
		coord = new Coordinate(col, row);
		piece = p;
	}
	private void writeObject(ObjectOutputStream oos)
			throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
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
