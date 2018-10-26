package edu.colostate.cs.cs414.f18.the_other_alex.testmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Cell;
import edu.colostate.cs.cs414.f18.the_other_alex.model.General;
import edu.colostate.cs.cs414.f18.the_other_alex.model.NullPiece;

public class testCell {

	
	@Test
	public void testCoordinateConstructorXValue()
	{
		Cell c = new Cell(4, 3, new NullPiece());
		assertEquals(3, c.getCoordinate().getX());
	}
	
	@Test
	public void testCoordinateConstructorYValue()
	{
		Cell c = new Cell(4, 3, new NullPiece());
		assertEquals(4, c.getCoordinate().getY());
	}
	
	@Test
	public void testCellWithPiece()
	{
		Cell c = new Cell(4, 3, new General());
		assertEquals("General", c.getPiece().getType());
	}
}
