package edu.colostate.cs.cs414.f18.the_other_alex.model;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.UserService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.UUID;

public class Invite extends Observable {
  private String inviteId;
  private User fromUser;
  private ArrayList<String> toUsers;
  private String toUser;

  public Invite(User user) {
    this(user, generateId());
  }

  private Invite(User user, String inviteId) {
    fromUser = user;
    this.inviteId = inviteId;
    toUsers = new ArrayList<>();
    toUser = null;
  }

  public synchronized boolean send(String username) {
    if (!toUsers.contains(username)) {
      return toUsers.add(username);
    }
    return false;
  }

  public User[] findPlayers(String username, UserService userService) {
    return userService.searchUser(username, 5);
  }

  /**
   *
   * @param username username of the user accepting
   * @return
   */
  public synchronized boolean acceptInvite(String username, UserService userService) throws UserNotFoundException {
    if(toUser == null && toUsers.contains(username)) {
      toUser = username;
      ArrayList<String> toRemove = new ArrayList<>();
      for (String user : toUsers) {
        if (user != toUser) {
          toRemove.add(user);
        }
      }
      for (String user : toRemove) {
        rejectInvite(user, userService);
      }
      remove(userService);
      setChanged();
      notifyObservers();
      return true;
    } else {
      return false;
    }
  }

  public synchronized boolean rejectInvite(String username, UserService userService) {
    int i = toUsers.indexOf(username);
    if (i != -1) {
      toUsers.remove(username);
      try {
        userService.getUser(username).rejectInvite(this);
      } catch (UserNotFoundException e) {
        // can happen when the user unregisters. we didn't need them anyway..
      }
    }
    return i != -1;
  }

  public User getFromUser() {
    return fromUser;
  }

  public ArrayList<String> getToUsers() {
    return toUsers;
  }

  public String getInviteId() {
    return inviteId;
  }

  public static String generateId() {
    return UUID.randomUUID().toString();
  }

  public String getToUser() {
    return toUser;
  }

  public void clearAcceptance() {
    toUsers = null;
  }

  private void remove(UserService userService) {
    fromUser.removeInviteFromPendingInvites(this);
    try {
      userService.getUser(toUser).removeInviteFromPendingReceivedInvites(this);
    } catch (UserNotFoundException e) {
      // do nothing
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Invite invite = (Invite) o;
    return Objects.equals(inviteId, invite.inviteId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inviteId);
  }
}
