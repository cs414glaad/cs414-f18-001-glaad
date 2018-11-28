package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.GameRecord;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

public class GameRecordData extends RestCall {
  public GameRecordData(GameRecord gameRecord) {
    gameStartTime = gameRecord.getGameStartTime().toString(); // TODO reformat time format
    gameEndTime = gameRecord.getGameEndTime().toString();
    winnerName = gameRecord.getWinnerName();
    loserName = gameRecord.getLoserName();
  }

  public String gameStartTime;
  public String gameEndTime;
  public String winnerName;
  public String loserName;
}
