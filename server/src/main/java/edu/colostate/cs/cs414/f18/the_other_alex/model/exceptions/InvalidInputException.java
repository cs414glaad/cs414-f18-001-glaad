package edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions;

public class InvalidInputException extends Throwable {
  public InvalidInputException(String errorMessage) {
    super(errorMessage);
  }
}
