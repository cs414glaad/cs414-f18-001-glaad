package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Board;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class BoardData extends DataType {
  public BoardData(Board board) {
    cells = new PieceData[Board.NUM_ROWS][Board.NUM_COLS];
    for (int row = 0; row < Board.NUM_ROWS; row++) {
      for (int col = 0; col < Board.NUM_COLS; col++) {
        cells[row][col] = new PieceData(board.getCells()[row][col]);
        cells[row][col].id = String.format("%s %s", row, col);
      }
    }
  }

  public PieceData[][] cells; // contains piece type and color
}
