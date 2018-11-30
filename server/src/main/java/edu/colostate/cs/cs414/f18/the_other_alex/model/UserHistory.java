package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.Serializable;

public class UserHistory {
  private int wins; // note: these default to zero as they should
  private int losses;
  private int draws;
  private int totalGamesPlayed;

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
