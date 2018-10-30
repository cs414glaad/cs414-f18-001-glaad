package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import spark.Request;
import spark.Response;

/**
 * Available game types are:
 *
 * - 'hist': requires username
 * - 'game': requires gameId
 * - 'record': requires gameId
 * - 'board': requires gameId
 *
 * Json format:
 * {
 *   type: {type},
 *   gameId: {gameId},
 *   username: {username},
 * }
 */
public class QueryRequest extends RestRequest {
  public String username;
  public String gameId;

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    return null;
  }

  @Override
  protected void validate() throws InvalidApiCallException, FailedApiCallException {

  }
}
