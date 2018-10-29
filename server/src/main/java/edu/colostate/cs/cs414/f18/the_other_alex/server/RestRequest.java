package edu.colostate.cs.cs414.f18.the_other_alex.server;

import com.google.gson.Gson;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import spark.Request;
import spark.Response;

public abstract class RestRequest extends RestCall {
  protected void invalidCall(String msg) throws InvalidApiCallException {
    throw new InvalidApiCallException(msg);
  }

  protected void failedCall(String msg) throws FailedApiCallException {
    throw new FailedApiCallException(msg);
  }

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

  @Override
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
