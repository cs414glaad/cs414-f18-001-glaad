package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.UserHistory;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class UserHistoryData extends DataType {
  public UserHistoryData(UserHistory userHistory) {
    wins = userHistory.getWins();
    losses = userHistory.getLosses();
    draws = userHistory.getDraws();
    totalGamesPlayed = userHistory.getTotalGamesPlayed();
  }

  public int wins;
  public int losses;
  public int draws;
  public int totalGamesPlayed;
}
