package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Board;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

import java.util.ArrayList;

public class BoardList extends RestCall {
  public BoardList() {
    boards = new ArrayList<>();
  }

  public BoardList(Board board) {
    this();
    boards.add(new BoardData(board));
  }
  public ArrayList<BoardData> boards;
}
