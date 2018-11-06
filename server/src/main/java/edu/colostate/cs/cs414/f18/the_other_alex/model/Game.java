package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidMoveException;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.io.Serializable;

public class Game extends Observable implements Observer, Serializable {
  private String gameId;
  private User user1;
  private User user2;
  private User turn;
  private Board board;
  private GameRecord gameRecord;
  private PieceColor user1Color;
  private PieceColor user2Color;
  private Boolean firstMove;
  private static final long serialVersionUID = 7526472295622776142L;
  private GameState gameState;

  //game record start time is set when Game instantiated. Player1 is first to move.
  public Game(User player1, User player2, String id) {
    gameId = id;
    user1 = player1;
    user2 = player2;
    board = new Board();
    gameRecord = new GameRecord(new Date());
    user1Color = PieceColor.NONE;
    user2Color = PieceColor.NONE;
    turn = user1;
    firstMove = true;
    gameState = GameState.IN_PROGRESS;
  }

  public void gameOver() {
    gameRecord.setGameEndTime(new Date());
    if(isUsersTurn(user1)) {
      gameRecord.setWinnerName(user1.getUsername());
      gameRecord.setLoserName(user2.getUsername());
    }
    else {
      gameRecord.setWinnerName(user2.getUsername());
      gameRecord.setLoserName(user1.getUsername());
    }
  }

  public Board getBoard() {
    return board;
  }

  public void endTurn() {
    if (isUsersTurn(user1)) {
      turn = user2;
    }
    else {
      turn = user1;
    }
  }

  public GameState makeMove(Cell fromCell, Cell toCell, User user) throws InvalidMoveException {
//check for firstMove. If true, set userColors according to flipped piece color. (assumes user1 plays first)
    if (firstMove) {
      board.move(fromCell, toCell);
      user1Color = fromCell.getPiece().getColor();
      if (user1Color == PieceColor.BLACK) {
        user2Color = PieceColor.RED;
      } else {
        user2Color = PieceColor.BLACK;
      }
      firstMove = false;
      endTurn();
      return GameState.IN_PROGRESS;
    }
//subsequent moves.
    if(fromCell.getPiece().getColor() == getUserColor(user)) { //make sure piece is right color
      board.move(fromCell, toCell);
      if (board.isGameOver(getOpponentColor(user))) {
        gameOver();
        return GameState.OVER;
      }
      else {
        endTurn();
        return GameState.IN_PROGRESS;
      }
    }
    else {
      throw new InvalidMoveException("Invalid move: Select a piece of your own color");
    }
  }

  public boolean isUsersTurn(User user) {
    return (turn == user);
  }

  public PieceColor getUserColor(User user) {
    if(user == user1) {
      return user1Color;
    }
    else {
      return user2Color;
    }
  }

  public PieceColor getOpponentColor(User user) {
    if(user == user1) {
      return user2Color;
    }
    else {
      return user1Color;
    }
  }

  public GameRecord getGameRecord() {
    return gameRecord;
  }

  @Override
  public void update(Observable o, Object arg) {

  }

  public String getGameId() {
    return gameId;
  }

  public PieceColor getUser1Color() {
    return user1Color;
  }

  public PieceColor getUser2Color() {
    return user2Color;
  }

  public User getTurn() {
    return turn;
  }

  public GameState getGameState() {
    return gameState;
  }
}
