package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Board;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class BoardData extends DataType {
  public BoardData(Board board) {
    cells = new PieceData[8][4];
    for (int row = 0; row < cells.length; row++) {
      for (int col = 0; col < cells[row].length; col++) {
        cells[row][col] = new PieceData(board.getCells()[row][col]);
      }
    }
  }

  public PieceData[][] cells; // contains piece type and color
}
