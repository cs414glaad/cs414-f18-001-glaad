package edu.colostate.cs.cs414.f18.the_other_alex.testmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Advisor;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Cannon;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Cell;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Chariot;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Elephant;
import edu.colostate.cs.cs414.f18.the_other_alex.model.General;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Horse;
import edu.colostate.cs.cs414.f18.the_other_alex.model.NullPiece;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Soldier;

public class testPiece {
	
	@Test
	public void testCanSoldierCaptureGeneral()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new NullPiece());
		cells[2][1] = new Cell(2, 1, new General());
		cells[2][1].getPiece().flipPiece();
		cells[2][2] = new Cell(2, 2, new Soldier());
		cells[2][2].getPiece().flipPiece();
		assertTrue(cells[2][2].getPiece().isMoveValid(cells[2][1], cells[2][2], cells));
	}
	
	@Test
	public void testCanCannonCaptureHorseValid()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new Horse());
		cells[2][0].getPiece().flipPiece();
		cells[2][1] = new Cell(2, 1, new General());
		cells[2][2] = new Cell(2, 2, new Cannon());
		cells[2][2].getPiece().flipPiece();
		assertTrue(cells[2][2].getPiece().isMoveValid(cells[2][0], cells[2][2], cells));
	}
	
	@Test
	public void testCanCannonCaptureHorseInvalid()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new Horse());
		cells[2][0].getPiece().flipPiece();
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new Cannon());
		cells[2][2].getPiece().flipPiece();
		assertFalse(cells[2][2].getPiece().isMoveValid(cells[2][0], cells[2][2], cells));
	}
	
	@Test
	public void testCannotCaptureDiagonalElephantTakesChariot()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new Elephant());
		cells[1][1].getPiece().flipPiece();
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new NullPiece());
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new Chariot());
		cells[2][2].getPiece().flipPiece();
		assertFalse(cells[1][1].getPiece().isMoveValid(cells[2][2], cells[1][1], cells));
	}
	
	@Test
	public void testCannotTakePieceTwoMovesAwayChariotTakesHorse()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new Horse());
		cells[2][0].getPiece().flipPiece();
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new Chariot());
		cells[2][2].getPiece().flipPiece();
		assertFalse(cells[2][2].getPiece().isMoveValid(cells[2][0], cells[2][2], cells));
	}
	
	@Test
	public void testStandardMoveValidXAxis()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new NullPiece());
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new Advisor());
		cells[2][2].getPiece().flipPiece();
		assertTrue(cells[2][2].getPiece().isMoveValid(cells[2][1], cells[2][2], cells));
	}
	
	@Test
	public void testStandardMoveValidYAxis()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new NullPiece());
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new Advisor());
		cells[2][2].getPiece().flipPiece();
		assertTrue(cells[2][2].getPiece().isMoveValid(cells[1][2], cells[2][2], cells));
	}
	
	@Test
	public void canSoldierCaptureAdvisorInvalid()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new Soldier());
		cells[0][0].getPiece().flipPiece();
		cells[0][1] = new Cell(0, 1, new Advisor());
		cells[0][1].getPiece().flipPiece();
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new NullPiece());
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new NullPiece());
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new NullPiece());
		assertFalse(cells[0][0].getPiece().isMoveValid(cells[0][1], cells[0][0], cells));
	}
	
	@Test
	public void canHorseCaptureElephantInvalid()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new Horse());
		cells[1][0].getPiece().flipPiece();
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new Elephant());
		cells[2][0].getPiece().flipPiece();
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new NullPiece());
		assertFalse(cells[1][0].getPiece().isMoveValid(cells[2][0], cells[1][0], cells));
	}
	
	@Test
	public void canElephantCaptureHorseValid()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new Horse());
		cells[1][0].getPiece().flipPiece();
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new Elephant());
		cells[2][0].getPiece().flipPiece();
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new NullPiece());
		assertTrue(cells[2][0].getPiece().isMoveValid(cells[1][0], cells[2][0], cells));
	}
	
	@Test
	public void canGeneralCaptureGeneralValid()
	{
		Cell[][] cells = new Cell[3][3];
		cells[0][0] = new Cell(0, 0, new NullPiece());
		cells[0][1] = new Cell(0, 1, new NullPiece());
		cells[0][2] = new Cell(0, 2, new NullPiece());
		cells[1][0] = new Cell(1, 0, new General());
		cells[1][0].getPiece().flipPiece();
		cells[1][1] = new Cell(1, 1, new NullPiece());
		cells[1][2] = new Cell(1, 2, new NullPiece());
		cells[2][0] = new Cell(2, 0, new General());
		cells[2][0].getPiece().flipPiece();
		cells[2][1] = new Cell(2, 1, new NullPiece());
		cells[2][2] = new Cell(2, 2, new NullPiece());
		assertTrue(cells[2][0].getPiece().isMoveValid(cells[1][0], cells[2][0], cells));
	}
}
