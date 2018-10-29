package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class UserService {
  public User getUser(String username) {
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

  private Invite createInvite(String fromUser, String toUser) {
    return getUser(fromUser).sendInvite(toUser);
  }
}
