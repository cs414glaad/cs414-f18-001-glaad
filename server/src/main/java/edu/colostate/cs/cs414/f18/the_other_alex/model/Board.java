package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class Board {
  private Cell[][] cells;

  public void move(Cell fromCell, Cell toCell) {
    //check to make sure piece in fromCell are different from piece in toCell

  }

  public boolean anyPossibleMovesLeft() {
    return false;
  }

  public boolean isGameOver() {
    return false;
  }

  public Cell[][] getCells() {
    return cells;
  }
}
