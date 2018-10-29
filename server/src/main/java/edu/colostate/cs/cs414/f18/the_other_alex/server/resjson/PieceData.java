package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Cell;
import edu.colostate.cs.cs414.f18.the_other_alex.model.pieces.Piece;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class PieceData extends DataType {
  // row and column are stored in id
  public String color;
  public boolean isFlipped;

  public PieceData(Piece piece) {
    switch(piece.getColor()) {
      case NONE:
        color = null;
      case BLACK:
        color = "black";
      case RED:
        color = "red";
    }
    isFlipped = piece.getIsFlipped();
  }

  public PieceData(Cell cell) {
    this(cell.getPiece());
  }
}
