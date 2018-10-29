package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;
import java.util.List;

public class Invite {
  private User fromUser;
  private ArrayList<User> toUsers;

  public boolean send(String username) {
    return false;
  }

  public void findPlayers(String username) {

  }

  public boolean acceptInvite() {
    return false;
  }

  public boolean rejectInvite() {
    return false;
  }

  public User getFromUser() {
    return fromUser;
  }

  public ArrayList<User> getToUsers() {
    return toUsers;
  }
}
