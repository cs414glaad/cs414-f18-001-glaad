package edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions;

public class GameNotFoundException extends Exception {
  public GameNotFoundException() {
    this("game not found");
  }

  public GameNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
