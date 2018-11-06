package edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions;

public class UserNotFoundException extends Exception {
  public UserNotFoundException() {
    this("user not found");
  }

  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}

