package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidMoveException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.Cannon;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestGame {

  Game game;
  Board board;
  Cell[][] cells;
  User user1;
  User user2;

  @BeforeEach
  void setUP() throws InvalidInputException {
    user1 = new User("Tim","yahoo@yahoo.com", "1234" );
    user2 = new User("Eric", "aol@aol.com)", "admin");
    game = new Game(user1, user2,"007");
    board = game.getBoard();
    cells = board.getCells();
  }

  @Test
  void testInitializedVariables() {
    assertEquals(user1, game.getTurn()); //Turn is initialized to first User argument
    assertEquals(PieceColor.NONE, game.getUser1Color());
    assertEquals(PieceColor.NONE, game.getUser2Color());
    assertEquals(GameState.IN_PROGRESS, game.getGameState());
  }

  @Test
  void testIsUsersTurn() {
    assertTrue(game.isUsersTurn(game.getTurn()));
  }

  @Test
  void testEndTurn() {
    game.endTurn();
    assertEquals(user2, game.getTurn());
  }

  @Test
  void testColorInitOnFirstMove() throws InvalidMoveException {
    cells[0][0].setPiece(new General(PieceColor.BLACK));
    game.makeMove(cells[0][0], cells[0][0], game.getTurn());
    assertEquals(cells[0][0].getPiece().getColor(), game.getUser1Color());
    assertEquals(PieceColor.RED, game.getUser2Color());
  }

  @Test
  void testMakeSubsequentMove() throws InvalidMoveException {
    cells[0][0].setPiece(new General(PieceColor.BLACK));
    cells[0][1].setPiece(new Cannon(PieceColor.RED));
    game.makeMove(cells[0][0], cells[0][0], game.getTurn());
    game.makeMove(cells[0][1], cells[0][1], game.getTurn());
    game.makeMove(cells[0][0],cells[0][1], game.getTurn());
    assertEquals("General", cells[0][1].getPiece().getType());
    assertEquals("Null piece",cells[0][0].getPiece().getType());
  }

  @Test
  void testMakeFinalMove() throws InvalidMoveException {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 8; j++) {
        cells[i][j].setPiece(new NullPiece());
      }
    }
    cells[0][0].setPiece(new General(PieceColor.BLACK));
    cells[0][1].setPiece(new Cannon(PieceColor.RED));
    game.makeMove(cells[0][0], cells[0][0], game.getTurn());
    game.makeMove(cells[0][1], cells[0][1], game.getTurn());
    game.makeMove(cells[0][0],cells[0][1],game.getTurn());
    assertEquals(GameState.OVER, game.getGameState());
  }

  @Test // Winning move is performed by the user whose turn it is.
  void testGameOver() {
    game.gameOver();
    GameRecord record = game.getGameRecord();
    assertEquals(user1.getUsername(), record.getWinnerName());
    assertEquals(user2.getUsername(), record.getLoserName());
    assertEquals(GameState.OVER, game.getGameState());
  }

  @Test
  void testGetTurnColor() {
    game.setUser1Color(PieceColor.BLACK);
    assertEquals(PieceColor.BLACK, game.getTurnColor(user1));
  }

  @Test
  void testGetOpponentColor() {
    game.setUser2Color(PieceColor.RED);
    assertEquals(PieceColor.RED, game.getOpponentColor(user1));
  }


}
