package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.GameRecord;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

import java.util.ArrayList;

public class GameRecordList extends RestCall {
  public ArrayList<GameRecordData> gameRecords;

  public GameRecordList() {
    super();
    gameRecords = new ArrayList<>();
  }

  public GameRecordList(GameRecord gameRecord) {
    this();
    gameRecords.add(new GameRecordData(gameRecord));
  }
}
