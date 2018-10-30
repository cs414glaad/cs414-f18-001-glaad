package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import spark.Request;
import spark.Response;

/**
 * This type of request is for making changes in the game aspects of the model.
 *
 * Available types are:
 *
 * - 'move': requires gameId, fromCell, toCell
 *
 * Json format:
 * {
 *   type: {type},
 *   gameId: {gameId},
 *   fromCell: {cellId},
 *   toCell: {cellId},
 * }
 */
public class GameRequest extends RestRequest {
  public String gameId;
  public String fromCell;
  public String toCell;

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    return null;
  }

  @Override
  protected void validate() throws InvalidApiCallException, FailedApiCallException {

  }
}
