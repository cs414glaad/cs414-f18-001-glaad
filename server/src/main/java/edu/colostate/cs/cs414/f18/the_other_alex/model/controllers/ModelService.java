package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

public class ModelService {
  private UserService userService;
  private GameService gameService;

  public UserService getUserService() {
    return userService;
  }

  public GameService getGameService() {
    return gameService;
  }
}
