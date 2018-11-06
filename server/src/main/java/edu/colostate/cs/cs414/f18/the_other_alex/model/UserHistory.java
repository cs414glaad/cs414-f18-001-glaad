package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.Serializable;

public class UserHistory implements Serializable{
  private int wins;
  private int losses;
  private int draws;
  private int totalGamesPlayed;
  private static final long serialVersionUID = 7526472295622776141L;
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
