package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.server.Database;

public class ModelService {
  private UserService userService;
  private GameService gameService;
  private Database database;

  public ModelService() {
    database = new Database(this);
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
}
