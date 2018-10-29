package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class Game {
  private User user1;
  private User user2;
  private Turn turn;
  private Board board;
  private GameRecord gameRecord;
  private PieceColor user1Color;
  private PieceColor user2Color;



  public Game() {
    board = new Board();
    user1Color = PieceColor.NONE;
    user2Color = PieceColor.NONE;
  }

  public void gameOver() {

  }

  public Board getBoard() {
    return board;
  }

  public void endTurn() {

  }

  public GameState makeMove(Cell fromCell, Cell toCell, User user) throws InvalidMoveException {
    //checking for first move
    boolean firstMove = false;
    Cell[][] cells = board.getCells();
    for (int i= 0; i < 4; i++) {
      for (int j = 0; j < 8; j++) {
        if (!cells[i][j].getPiece().isFlipped) {
          firstMove = true;
        }
      }
    }

    if (firstMove) {
      board.move(fromCell, toCell);
      user1Color = fromCell.getPiece().getColor();
      if (user1Color.equals("BLACK")) {
        user2Color = PieceColor.RED;
      } else {
        user2Color = PieceColor.BLACK;
      }
      return GameState.IN_PROGRESS;
    }


    return null;
  }

  public boolean isUsersTurn(User user) {
    return false;
  }

  public void attach(GameObserver o) {

  }

  public void detach(GameObserver o) {

  }

  public void notifyObservers() {

  }

  public GameRecord getGameRecord() {
    return gameRecord;
  }
}
