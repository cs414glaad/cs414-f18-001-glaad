package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

public class Invite extends Observable implements Serializable {
  private String inviteId;
  private User fromUser;
  private ArrayList<String> toUsers;
  private String toUser;

    private static final long serialVersionUID = 200L;

  public Invite(User user) {
    this(user, generateId());
  }

  private Invite(User user, String inviteId) {
    fromUser = user;
    this.inviteId = inviteId;
    toUsers = new ArrayList<>();
    toUser = null;
  }

  public boolean send(String username) {
    if (!toUsers.contains(username)) {
      return toUsers.add(username);
    }
    return false;
  }

  public void findPlayers(String username) {

  }

  /**
   *
   * @param username username of the user accepting
   * @return
   */
  public synchronized boolean acceptInvite(String username) {
    if(toUser == null && toUsers.contains(username)) {
      toUser = username;
      notifyObservers();
      return true;
    } else {
      return false;
    }
  }

  public synchronized void rejectInvite(String username) {
    int i = toUsers.indexOf(username);
    if (i != -1) {
      toUsers.remove(username);
    }
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

  public void clearAcceptance()
  {
    toUsers = null;
  }
    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }
}
