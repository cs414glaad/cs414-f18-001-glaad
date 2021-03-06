package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.ResponseData;
import spark.Request;
import spark.Response;

/**
 * This type of request is for making changes in the game aspects of the model.
 *
 * Available types are:
 *
 * - 'move':
 *     requires gameId, fromCell, toCell
 *     makes a move
 *     returns ResponseData
 *
 * Json format:
 * {
 *   type: {type},
 *   gameId: {gameId},
 *   fromCell: {cellId},
 *   toCell: {cellId},
 * }
 * cell id's are specified in GameService makeMove method: "row col" where row
 * and col are the row and column of piece's position
 */
public class GameRequest extends RestRequest {
  public String gameId;
  public String fromCell;
  public String toCell;

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade) throws InvalidApiCallException, FailedApiCallException {
    switch (type) {
      case "move":
        modelFacade.makeMove(gameId, fromCell, toCell, currentUser);
        ResponseData responseData = new ResponseData("success", "move was successful");
        return responseData.toString();
      default:
        throw new InvalidApiCallException("invalid type for 'game' call");
    }
  }

  @Override
  protected void validate(String currentUser) throws InvalidApiCallException, FailedApiCallException {
    super.validate(currentUser);
    requireLoggedIn(currentUser);
    switch (type) {
      case "move":
        assertNotNull(gameId, "gameId");
        assertNotNull(toCell, "toCell");
        assertNotNull(fromCell, "fromCell");
        break;
      default:
        throw new InvalidApiCallException("invalid type for game");
    }
  }
}
