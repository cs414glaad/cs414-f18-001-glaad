package edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions;

public class InvalidApiCallException extends Exception {
  public InvalidApiCallException() {
    super();
  }

  public InvalidApiCallException(String msg) {
    super(msg);
  }
}
