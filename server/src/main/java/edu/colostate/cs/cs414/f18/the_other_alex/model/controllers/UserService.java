package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Database;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;

import java.util.ArrayList;
import java.util.Observable;

public class UserService extends Observable {
  private Database database;
  private ArrayList<User> cachedUsers;

  public UserService(Database database) {
    this.database = database;
    cachedUsers = new ArrayList<>();
  }

  /**
   * Throws exception for when user not found
   *
   * @param username
   * @return
   */
  public User getUser(String username) throws UserNotFoundException {
    for (User user : cachedUsers) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    if (database != null) {
      try {
        return database.getUser(username);
      } catch (Exception e) {
        throw new UserNotFoundException();
      }
    } else {
      throw new UserNotFoundException();
    }
  }

  /**
   * Throws exception if user not found
   *
   * @param email
   * @return
   */
  public User getUserByEmail(String email) throws UserNotFoundException {
    for (User user : cachedUsers) {
      if (user.getEmail().equals(email)) {
        return user;
      }
    }
    if (database != null) {
      try {
        return database.getUserByEmail(email);
      } catch (Exception e) {
        throw new UserNotFoundException();
      }
    } else {
      throw new UserNotFoundException();
    }
  }

  public User registerUser(String username, String email, String password) throws FailedApiCallException, InvalidInputException {
    try {
      getUser(username); // throws exception
      getUserByEmail(email);
      throw new FailedApiCallException("user already exists");
    } catch (UserNotFoundException e) {
      // user doesn't exist
    }
    // Create user
    User user = new User(username, email, password);
    cachedUsers.add(user);
    notifyObservers();
    return user;
  }

  public User unregisterUser(User user) {
    return null; // TODO
  }

  public User[] searchUser(String query, int limit) {
    return null; // TODO
  }

  private User loadUser(User user) {
    if (cachedUsers.indexOf(user) == -1) {
      cachedUsers.add(user);
    }
    return user;
  }

  private User saveUser(User user) {
    return null; // TODO
  }

  /**
   * This method will get the invite with the specified inviteId from the fromUser. If the fromUser doesn't have
   * the invite, it will create it and assign the involved users.
   *
   * @param fromUser The user creating the invite
   * @param toUser The user receiving the invite
   * @param inviteId The invite id to send, null if new invite
   * @return Returns the created invite
   */
  public synchronized Invite sendInvite(String fromUser, String inviteId, String toUser) throws UserNotFoundException {
    User fromUserObj = getUser(fromUser);
    User toUserObj = getUser(toUser);
    Invite invite = fromUserObj.getSendInvite(inviteId);
    if (invite == null) {
      invite = new Invite(fromUserObj);
    }
    fromUserObj.sendInvite(invite);
    toUserObj.receiveInvite(invite);
    notifyObservers();
    return invite;
  }

  public void shutdown() {
    notifyObservers();
  }

  public User authenticate(String username, String email, String password) throws InvalidApiCallException, FailedApiCallException {
    try {
      User user;
      if (username != null) {
        user = getUser(username);
      } else if (email != null) {
        user = getUserByEmail(email);
      } else {
        // shouldn't happen
        throw new InvalidApiCallException("username or email must be provided to authenticate");
      }
      if (!user.checkPassword(password)) {
        throw new FailedApiCallException();
      }
      return user;
    } catch (InvalidApiCallException e) {
      throw e;
    } catch (Exception e) {
      throw new FailedApiCallException("credentials invalid");
    }
  }

  /**
   * Returns
   * @param currentUser
   * @param inviteId
   * @return returns gameId (which is really just inviteId)
   * @throws FailedApiCallException
   */
  public String acceptInvite(String currentUser, String inviteId) throws FailedApiCallException {
    try {
      Invite invite = getUser(currentUser).getReceivedInvite(inviteId);
      if (!invite.acceptInvite(currentUser)) {
        throw new FailedApiCallException("unable to accept invitation");
      }
    } catch (UserNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
    return inviteId;
  }
}
