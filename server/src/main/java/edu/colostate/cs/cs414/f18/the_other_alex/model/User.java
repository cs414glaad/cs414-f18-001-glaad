package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class User {
  private String name;
  private String email;
  private String password;
  private Invite[] pendingInvites;
  private Invite[] pendingReceivedInvites;
  private UserHistory userHistory;

  public boolean sendInvite(String username) {
    return false;
  }

  public void changePassword(String username, String newPassword) {

  }

  public void changeEmail(String username, String newEmail) {

  }

  public UserHistory getUserHistory() {
    return userHistory;
  }
}
