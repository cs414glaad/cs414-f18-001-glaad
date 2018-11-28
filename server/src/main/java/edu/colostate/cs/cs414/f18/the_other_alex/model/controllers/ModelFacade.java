package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.*;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.GameNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;

public class ModelFacade {
  private ModelService modelService;

  public ModelFacade() {
    this.modelService = new ModelService();
  }

  public UserHistory getUserHistory(String username) throws UserNotFoundException {
    return modelService.getUserService().getUser(username).getUserHistory();
  }

  public Invite sendInvite(String fromUser, String toUser, String inviteId) throws FailedApiCallException {
    try {
      Invite invite = modelService.getUserService().sendInvite(fromUser, inviteId, toUser);
      invite.addObserver(modelService);
      return invite;
    } catch (UserNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  public String acceptInvite(String currentUser, String inviteId) throws FailedApiCallException {
    return modelService.getUserService().acceptInvite(currentUser, inviteId);
  }

  public String rejectInvite(String currentUser, String inviteId) throws FailedApiCallException {
    return modelService.getUserService().rejectInvite(currentUser, inviteId);
  }

  public User createUser(String username, String email, String password) throws FailedApiCallException, InvalidInputException {
    return modelService.getUserService().registerUser(username, email, password);
  }

  public Game getGame(String gameId) throws FailedApiCallException {
    try {
      return modelService.getGameService().getGame(gameId);
    } catch (GameNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  public GameRecord getGameRecord(String gameId) throws FailedApiCallException {
    try {
      return modelService.getGameService().getGame(gameId).getGameRecord();
    } catch (GameNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  public Board getBoard(String gameId) throws FailedApiCallException {
    try {
      return modelService.getGameService().getGame(gameId).getBoard();
    } catch (GameNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  public User authenticate(String username, String email, String password) throws FailedApiCallException, InvalidApiCallException {
    return modelService.getUserService().authenticate(username, email, password);
  }

  public void shutdown() {
    modelService.shutdown();
  }

  public void makeMove(String gameId, String fromCell, String toCell, String currentUser) throws FailedApiCallException {
    try {
      modelService.getGameService().makeMove(gameId, fromCell, toCell, modelService.getUserService().getUser(currentUser));
    } catch (UserNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  public Invite sendInvite(String user1, String user2) throws FailedApiCallException {
    return sendInvite(user1, user2, null);
  }
}
