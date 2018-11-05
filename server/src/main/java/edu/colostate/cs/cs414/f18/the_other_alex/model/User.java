package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User extends Observable implements Observer {
  private String username;
  private String email;
  private String password;
  private ArrayList<Invite> pendingInvites;
  private ArrayList<Invite> pendingReceivedInvites;
  private UserHistory userHistory;
  private ArrayList<String> games;

  public User(String username, String email, String password) {
    if (isValidUsername(username)) {
      this.username = username;
    }
    setPassword(password);
    setEmail(email);
    pendingInvites = new ArrayList<>();
    pendingReceivedInvites = new ArrayList<>();
    userHistory = new UserHistory();
    games = new ArrayList<>();
  }

  /**
   * The receiving user is already listed in the invite.
   * @param invite
   */
  public void receiveInvite(Invite invite) {
    pendingReceivedInvites.add(invite);
    invite.getToUsers().add(username);
  }

  public void sendInvite(Invite invite) {
    pendingInvites.add(invite);
  }

  public Invite getSendInvite(String inviteId) {
    if (inviteId == null) {
      return null;
    }
    for (Invite invite : pendingInvites) {
      if (invite.getInviteId().equals(inviteId)) {
        return invite;
      }
    }
    return null;
  }

  private boolean isNonEmpty(String string) {
    return !(string == null || string.length() == 0);
  }

  /**
   * This validates the format not uniqueness. Uniqueness should be checked wherever the user object is created. This
   * is because we want to be able to create user objects even though a user with that username already exists.
   * @param username Username of users.
   * @return True if username is valid
   */
  private boolean isValidUsername(String username) {
    // TODO: check for username already exists
    return isNonEmpty(username);
  }

  private boolean isValidPassword(String password) {
    return isNonEmpty(password);
  }

  private boolean isValidEmail(String email) {
    // TODO: check for email already exists
    return isNonEmpty(email);
  }

  public void setPassword(String newPassword) {
    if (isValidPassword(newPassword)) {
      password = newPassword;
    }
  }

  public void setEmail(String newEmail) {
    if (isValidEmail(newEmail)) {
      email = newEmail;
    }
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

  public boolean checkPassword(String password) {
    return password.equals(this.password);
  }

  @Override
  public void update(Observable o, Object arg) {

  }
}
