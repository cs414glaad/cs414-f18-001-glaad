package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class ModelService implements Observer {
  // TODO: make this a singleton
  private UserService userService;
  private GameService gameService;
  private Database database;

  public ModelService(boolean useDatabase) {
    database = null;
    if (useDatabase) {
      database = new Database();
    }
    userService = new UserService(database);
    gameService = new GameService(database);

  }

  public ModelService() {
    this(true);
  }

  public UserService getUserService() {
    return userService;
  }

  public GameService getGameService() {
    return gameService;
  }

  public void shutdown() {
    userService.shutdown();
    gameService.shutdown();
  }

  /**
   * When invites are accepted, ModelService will handle them.
   * @param invite
   */
  private void createGame(Invite invite) throws ClassNotFoundException, IOException, SQLException,
   IllegalAccessException, InstantiationException {
    try {
      User toUser = userService.getUser(invite.getToUser());
      gameService.createGame(invite.getFromUser(), toUser, invite.getInviteId());
    } catch (UserNotFoundException e) {
      invite.rejectInvite(invite.getToUser(), userService);
      invite.clearAcceptance();
      return;
    }
  }

  @Override
  public synchronized void update(Observable o, Object arg) {
    if (o.getClass() == Invite.class) {
      Invite invite = (Invite)o;
      if (invite.getFromUser() == null) {
        return;
      }
      try {
        createGame((Invite) o);
      } catch (Exception e) {

      }
    }
  }
}
