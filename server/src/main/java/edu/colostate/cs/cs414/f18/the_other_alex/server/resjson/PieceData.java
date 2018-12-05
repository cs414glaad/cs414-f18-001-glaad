package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Cell;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.Piece;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class PieceData extends DataType {
  // row and column are stored in id
  public String color;
  public String pieceType; // "blank" or defined by Piece class

  public PieceData(Piece piece) {
    if (piece.getIsFlipped()) {
      // values defined in Piece (e.g., NullPiece, Soldier, etc.)
      pieceType = piece.getType();
      switch(piece.getColor()) {
        case NONE:
          color = null;
        case BLACK:
          color = "black";
        case RED:
          color = "red";
      }
    } else {
      pieceType = "blank";
      color = "blank";
    }
  }

  public PieceData(Cell cell) {
    this(cell.getPiece());
  }
}
