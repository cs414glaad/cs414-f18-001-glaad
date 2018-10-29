package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;
import java.util.UUID;

public class Invite {
  private String inviteId;
  private User fromUser;
  private ArrayList<String> toUsers;

  public Invite(User user, String inviteId) {
    fromUser = user;
    this.inviteId = inviteId;
    toUsers = new ArrayList<String>();
  }

  public boolean send(String username) {
    if (!toUsers.contains(username)) {
      return toUsers.add(username);
    }
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

  public ArrayList<String> getToUsers() {
    return toUsers;
  }

  public String getId() {
    return inviteId;
  }

  public static String generateId() {
    return UUID.randomUUID().toString();
  }
}
