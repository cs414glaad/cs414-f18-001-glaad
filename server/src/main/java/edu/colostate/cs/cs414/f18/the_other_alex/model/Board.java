package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
  private Cell[][] cells;
  private List<Piece> pieces;

  public Board() {
    cells = new Cell[4][8];
    pieces = new ArrayList<Piece>();
    loadPieces();
    loadCells();
  }

  public Cell[][] getCells() {
    return cells;
  }

  //randomizes placement of pieces onto 4x8 cells array
  private void loadCells() {
    Random randomGenerator = new Random();
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 8; j++) {
            int randomIndex = randomGenerator.nextInt(pieces.size());
            Piece piece = pieces.remove(randomIndex);
            cells[i][j] = new Cell(i, j, piece);
        }
    }
  }

  //creates list of 16 black and 16 red players (still needs the color argument for piece constructor)
  private void loadPieces() {
    pieces.add(new General(PieceColor.BLACK));
    pieces.add(new General(PieceColor.RED));
    for (int i = 0; i < 2; i++) {
      pieces.add(new Advisor(PieceColor.BLACK));
      pieces.add(new Advisor(PieceColor.RED));
      pieces.add(new Elephant(PieceColor.BLACK));
      pieces.add(new Elephant(PieceColor.RED));
      pieces.add(new Chariot(PieceColor.BLACK));
      pieces.add(new Chariot(PieceColor.RED));
      pieces.add(new Horse(PieceColor.BLACK));
      pieces.add(new Horse(PieceColor.RED));
      pieces.add(new Cannon(PieceColor.BLACK));
      pieces.add(new Cannon(PieceColor.RED));
    }
    for (int i = 0; i < 5; i ++) {
      pieces.add(new Soldier(PieceColor.BLACK));
      pieces.add(new Soldier(PieceColor.RED));
    }
  }

  public void move(Cell fromCell, Cell toCell) throws InvalidMoveException {
      //checking if user is attempting to flip a piece
      if (fromCell == toCell) {
          if (!fromCell.getPiece().isFlipped) {
              fromCell.getPiece().flipPiece();
              return;
          }
          else {
              throw new InvalidMoveException("Invalid move. Select a different move");
          }
      }
      else {
          boolean moveValid = fromCell.getPiece().isMoveValid(fromCell, toCell, cells);
          if (moveValid) {
              Piece fromPiece = fromCell.getPiece();
              toCell.setPiece(fromPiece);
              fromCell.setPiece(new NullPiece());
          } else {
              throw new InvalidMoveException("Invalid move. Select a different move");
          }
      }
  }

  public boolean anyPossibleMovesLeft(Turn turn) {
    return false;
  }

  public boolean isGameOver() {
    return false;
  }

}
