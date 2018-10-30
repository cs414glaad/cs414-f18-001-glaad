package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import spark.Request;
import spark.Response;

public abstract class RestRequest extends RestCall {

  /**
   * Once the request is validated, it can be handled. This method implements that handling.
   * @return Returns the response string
   */
  protected abstract String handleRequest(
      Request request,
      Response response,
      String currentUser,
      ModelFacade modelFacade);

  /**
   * Will validate the provided rest call. If the call is invalid, throws InvalidApiCallException.
   * If the request is valid, but fails, then FailedApiCallException is thrown.
   *
   * @throws InvalidApiCallException If request is invalid.
   * @throws FailedApiCallException If request fails to complete (e.g User couldn't be created)
   */
  protected abstract void validate() throws InvalidApiCallException, FailedApiCallException;
}
