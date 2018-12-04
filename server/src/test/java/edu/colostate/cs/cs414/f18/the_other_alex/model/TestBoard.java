package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidMoveException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBoard {

    Board board;
    Cell[][] cells;

    @BeforeEach
    void setUp() {
        board = new Board();
        cells = board.getCells();
    }

    @Test
    void testFirstMoveFlip() throws InvalidMoveException {
        assertFalse(cells[0][0].getPiece().getIsFlipped());
        board.move(cells[0][0], cells[0][0]);
        assertTrue(cells[0][0].getPiece().getIsFlipped());
    }

    @Test
    void testInvalidMove() throws InvalidMoveException {
        assertFalse(cells[0][0].getPiece().getIsFlipped());
        Throwable exception = assertThrows(InvalidMoveException.class, ()-> board.move(cells[0][0],cells[0][1]));
        assertEquals("Invalid move. Select a different move", exception.getMessage());
    }

    @Test
    void testMoveOpenSpace() throws InvalidMoveException {
        board.move(cells[0][0], cells[0][0]);
        String type = cells[0][0].getPiece().getType();
        cells[0][1].setPiece(new NullPiece());
        board.move(cells[0][0],cells[0][1]);
        assertEquals(type,cells[0][1].getPiece().getType());
        assertEquals("Null piece",cells[0][0].getPiece().getType());
    }

    @Test
    void testCaptureMove() throws InvalidMoveException {
      cells[0][0].setPiece(new General(PieceColor.BLACK));
      cells[0][0].getPiece().flipPiece();
      cells[0][1].setPiece(new Cannon(PieceColor.RED));
      cells[0][1].getPiece().flipPiece();
      board.move(cells[0][0],cells[0][1]);
      assertEquals("General", cells[0][1].getPiece().getType());
    }

    @Test
    void testCaptureInvalidMove() throws InvalidMoveException {
      cells[0][0].setPiece(new General(PieceColor.BLACK));
      cells[0][1].setPiece(new Horse(PieceColor.RED));
      Throwable exception = assertThrows(InvalidMoveException.class, ()-> board.move(cells[0][0],cells[0][1]));
      assertEquals("Invalid move. Select a different move", exception.getMessage());    }

    @Test
    void anyPossibleMovesLeft () {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 8; j++) {
          cells[i][j].getPiece().flipPiece();
          if(cells[i][j].getPiece().getColor() == PieceColor.RED) {
            cells[i][j].setPiece(new NullPiece());
          }
        }
      }
      assertFalse(board.anyPossibleMovesLeft(PieceColor.RED));
    }


}

