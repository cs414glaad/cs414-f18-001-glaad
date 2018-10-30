package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.Date;

public class GameRecord {
  private Date gameStartTime;
  private Date gameEndTime;
  private String winnerName;
  private String loserName;

  public GameRecord(Date StartTime) {
    gameStartTime = StartTime;
  }

  public Date getGameStartTime() {
    return gameStartTime;
  }

  public Date getGameEndTime() {
    return gameEndTime;
  }

  public void setGameEndTime(Date gameEndTime) {
    this.gameEndTime = gameEndTime;
  }

  public String getWinnerName() {
    return winnerName;
  }

  public void setWinnerName(String winnerName) {
    this.winnerName = winnerName;
  }

  public String getLoserName() {
    return loserName;
  }

  public void setLoserName(String loserName) {
    this.loserName = loserName;
  }
}
