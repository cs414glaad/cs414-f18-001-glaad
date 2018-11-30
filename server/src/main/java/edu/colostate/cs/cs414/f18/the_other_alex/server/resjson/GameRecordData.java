package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.GameRecord;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

import java.util.Date;

public class GameRecordData extends DataType {
  public GameRecordData(GameRecord gameRecord) {
    gameStartTime = parse(gameRecord.getGameStartTime());
    gameEndTime = parse(gameRecord.getGameEndTime());
    winnerName = parse(gameRecord.getWinnerName());
    loserName = parse(gameRecord.getLoserName());
  }

  private String parse(String str) {
    if (str == null) {
      return "";
    } else {
      return str;
    }
  }

  private String parse(Date date) {
    if (date == null) {
      return "";
    } else {
      return date.toString(); // TODO: better formatting?
    }
  }

  public String gameStartTime;
  public String gameEndTime;
  public String winnerName;
  public String loserName;
}
