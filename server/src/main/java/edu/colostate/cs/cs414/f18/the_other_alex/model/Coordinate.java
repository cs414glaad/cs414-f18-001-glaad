package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	private void writeObject(ObjectOutputStream oos)
			throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
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
