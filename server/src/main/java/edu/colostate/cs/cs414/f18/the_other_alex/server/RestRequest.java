package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import spark.Request;
import spark.Response;

/**
 * If more request types are needed, here is how to add them.
 *
 * 1. Identify if they fit as part of GameRequest, QueryRequest or UserRequest. You shouldn't need to create a new
 * request type object.
 * 2. Add validator to validate in order to accept the request.
 * 3. Add handler to switch statement in handleRequest.
 */
public abstract class RestRequest extends RestCall {

  protected void assertNotNull(Object obj, String name) throws InvalidApiCallException {
    if (obj == null) {
      throw new InvalidApiCallException("'"+name+"' must be specified for '"+type+"' type");
    }
  }

  protected void assertNotNullOrEmpty(String value, String name) throws InvalidApiCallException {
    assertNotNull(value, name);
    if (value.isEmpty()) {
      throw new InvalidApiCallException("'"+name+"' must not be empty");
    }
  }

  protected void requireLoggedIn(String username) throws InvalidApiCallException {
    if (username == null) {
      throw new InvalidApiCallException("user must be logged in");
    }
  }

  /**
   * Once the request is validated, it can be handled. This method implements that handling.
   * @return Returns the response string
   */
  protected abstract String handleRequest(
      Request request,
      Response response,
      String currentUser,
      ModelFacade modelFacade) throws InvalidApiCallException, FailedApiCallException;

  /**
   * Will validate the provided rest call. If the call is invalid, throws InvalidApiCallException.
   * If the request is valid, but fails, then FailedApiCallException is thrown.
   *
   * @throws InvalidApiCallException If request is invalid.
   * @throws FailedApiCallException If request fails to complete (e.g User couldn't be created)
   */
  protected void validate(String currentUser) throws InvalidApiCallException, FailedApiCallException {
    if (type == null) {
      throw new InvalidApiCallException("type must be defined");
    }
  }
}
