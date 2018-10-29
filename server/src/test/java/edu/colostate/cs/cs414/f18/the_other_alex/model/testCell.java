package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.General;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCell {

	
	@Test
	public void testCoordinateConstructorXValue(){
		Cell c = new Cell(4, 3, new NullPiece());
		assertEquals(3, c.getCoordinate().getCol());
	}
	
	@Test
	public void testCoordinateConstructorYValue(){
		Cell c = new Cell(4, 3, new NullPiece());
		assertEquals(4, c.getCoordinate().getRow());
	}
	
	@Test
	public void testCellWithPiece(){
		Cell c = new Cell(4, 3, new General(PieceColor.BLACK));
		assertEquals("General", c.getPiece().getType());
	}
}
