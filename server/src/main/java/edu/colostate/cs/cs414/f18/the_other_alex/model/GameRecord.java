package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.io.Serializable;

public class GameRecord implements Serializable {
  private Date gameStartTime;
  private Date gameEndTime;
  private String winnerName;
  private String loserName;
  private static final long serialVersionUID = 6L;

  private void writeObject(ObjectOutputStream oos)
          throws IOException {
    oos.defaultWriteObject();
  }

  private void readObject(ObjectInputStream ois)
          throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
  }

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
