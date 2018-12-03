package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.Serializable;

public class Coordinate implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private int col;
	private int row;
	
	
	public Coordinate(int col, int row)
	{
		this.col = col;
		this.row = row;
	}
	
	public void setCol(int newCol){ col = newCol;}
	
	public void setY(int newRow)
	{
		row = newRow;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getRow()
	{
		return row;
	}
	
}
