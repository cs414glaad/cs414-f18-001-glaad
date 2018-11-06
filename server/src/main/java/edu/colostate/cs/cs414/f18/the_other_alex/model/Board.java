package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidMoveException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
  private Cell[][] cells;
  private List<Piece> pieces;
  public static final int NUM_ROWS = 4;
  public static final int NUM_COLS = 8;



  public Board() {
    cells = new Cell[NUM_ROWS][NUM_COLS];
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
    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
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
    for (int i = 0; i < 5; i++) {
      pieces.add(new Soldier(PieceColor.BLACK));
      pieces.add(new Soldier(PieceColor.RED));
    }
  }

  public void move(Cell fromCell, Cell toCell) throws InvalidMoveException {
    //checking if user is attempting to flip a piece
    if (fromCell == toCell) {
      if (!fromCell.getPiece().getIsFlipped()) {
        fromCell.getPiece().flipPiece();
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
      }
      else {
        throw new InvalidMoveException("Invalid move. Select a different move");
      }
    }
  }

  //Checks if player has a move to make before giving them a turn. Otherwise game over.
  public boolean anyPossibleMovesLeft(PieceColor opponentColor) {
    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        Piece p = cells[i][j].getPiece();
        if (!p.getIsFlipped()) { //unflipped piece means move available
          return true;
        }
        if (p.getColor() == opponentColor) {
          for (int k = 0; k < NUM_ROWS; k++) {
            for (int l = 0; l < NUM_COLS; l++) {
              if (p.isMoveValid(cells[k][l], cells[i][j], cells)) {
                return true;
              }
            }
          }
        }
      }
      }
      return false;
    }

//remove?
  public boolean isGameOver(PieceColor opponentColor) {
    return(!anyPossibleMovesLeft(opponentColor));
  }
}

