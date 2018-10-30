package edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions;

public class FailedApiCallException extends Exception {
  public FailedApiCallException() {
    super();
  }

  public FailedApiCallException(String msg) {
    super(msg);
  }
}
