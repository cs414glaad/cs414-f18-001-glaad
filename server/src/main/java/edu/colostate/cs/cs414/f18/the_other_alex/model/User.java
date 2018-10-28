package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;
import java.util.BitSet;

public class User {
  private String name;
  private String username;
  private String email;
  private String password;
  private ArrayList<Invite> pendingInvites;
  private ArrayList<Invite> pendingReceivedInvites;
  private UserHistory userHistory;
  private ArrayList<String> games;

  public Invite sendInvite(String username) {
    return null;
  }

  public void changePassword(String username, String newPassword) {

  }

  public void changeEmail(String username, String newEmail) {

  }

  public UserHistory getUserHistory() {
    return userHistory;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public ArrayList<Invite> getInvites() {
    return pendingInvites;
  }

  public ArrayList<String> getGames() {
    return games;
  }
}
