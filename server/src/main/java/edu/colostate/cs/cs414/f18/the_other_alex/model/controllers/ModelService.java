package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Database;

import java.util.Observable;
import java.util.Observer;

public class ModelService implements Observer {
  private UserService userService;
  private GameService gameService;
  private Database database;

  public ModelService() {
    database = new Database();
    userService = new UserService(database);
    gameService = new GameService(database);
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
  private void createGame(Invite invite) {
    try {
      User toUser = userService.getUser(invite.getToUser());
      gameService.createGame(invite.getFromUser(), toUser, invite.getInviteId());
    } catch (UserNotFoundException e) {
      invite.rejectInvite(invite.getToUser());
      invite.clearAcceptance();
      return;
    }
  }

  @Override
  public synchronized void update(Observable o, Object arg) {
    if (o.getClass() == Invite.class) {
      createGame((Invite)o);
    }
  }
}
