package edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions;

public class InvalidMoveException extends Exception {
  public InvalidMoveException() {
    super();
  }

  public InvalidMoveException(String errorMessage) {
    super(errorMessage);
  }
}
