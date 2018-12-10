package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserHistory implements Serializable {
  private int wins; // note: these default to zero as they should
  private int losses;
  private int draws;
  private int totalGamesPlayed;
  private static final long serialVersionUID = 9L;

  private void writeObject(ObjectOutputStream oos)
          throws IOException {
      oos.defaultWriteObject();
  }

  private void readObject(ObjectInputStream ois)
          throws ClassNotFoundException, IOException {
          ois.defaultReadObject();
  }

  public int getWins() {
    return wins;
  }

  public int getLosses() {
    return losses;
  }

  public int getDraws() {
    return draws;
  }

  public int getTotalGamesPlayed() {
    return totalGamesPlayed;
  }
}
