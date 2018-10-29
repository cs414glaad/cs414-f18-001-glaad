package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;

import java.util.UUID;

public class UserService {
  public User getUser(String username) {
    return null;
  }

  public User getUserByEmail(String email) {
    return null;
  }

  public User registerUser(String username, String email, String password) {
    return null; // TODO
  }

  public User unregisterUser(User user) {
    return null; // TODO
  }

  public User searchUser(String query) {
    return null; // TODO
  }

  private User loadUser(User user) {
    return null; // TODO
  }

  private User saveUser(User user) {
    return null; // TODO
  }

  /**
   * This will create an invite that hasn't already been created.
   *
   * @param fromUser The user creating the invite
   * @param toUser The user receiving the invite
   * @return Returns the created invite
   */
  private Invite createInvite(String fromUser, String toUser) {
    String inviteId = UUID.randomUUID().toString();
    Invite invite = getUser(fromUser).sendInvite(inviteId, toUser);
    getUser(toUser).receiveInvite(invite);
    return invite;
  }
}
