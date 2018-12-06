package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Cell;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.Piece;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class PieceData extends DataType {
  // row and column are stored in id
  public String color;
  public String type; // "blank" or defined by Piece class
  public boolean isFlipped;

  public PieceData(Piece piece) {
    isFlipped = piece.getIsFlipped();
    if (isFlipped) {
      // values defined in Piece (e.g., NullPiece, Soldier, etc.)
      type = piece.getType();
      switch(piece.getColor()) {
        case NONE:
          color = "";
          break;
        case BLACK:
          color = "BLACK";
          break;
        case RED:
          color = "RED";
          break;
      }
    } else {
      type = "";
      color = "";
      isFlipped = true;
    }
  }

  public PieceData(Cell cell) {
    this(cell.getPiece());
  }
}
