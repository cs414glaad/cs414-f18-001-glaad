package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Board;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.model.GameRecord;
import edu.colostate.cs.cs414.f18.the_other_alex.model.UserHistory;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.BoardList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.GameList;
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

  /**
   * Calls getUserHistory(String username) in UserHistory
   * @return returns a UserHistory data type
   */
  private String handleHist(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    UserHistory userHistory = modelFacade.getUserHistory(username);
    return null; // TODO
  }

  /**
   * Calls getGame(String gameId) from Game
   * @return
   */
  private String handleGame(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    Game game = modelFacade.getGame(gameId);
    GameList gameList = new GameList(game);
    return gameList.toString();
  }

  /**
   * Calls getGameRecord(String gameId) from GameRecord
   * @return
   */
  private String handleRecord(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    GameRecord gameRecord = modelFacade.getGameRecord(gameId);
    return null; // TODO
  }

  /**
   * Calls getBoard(String gameId) from Game
   * @return
   */
  private String handleBoard(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    Board board = modelFacade.getBoard(gameId);
    BoardList boardList = new BoardList(board);
    return boardList.toString();
  }

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade)
      throws InvalidApiCallException {
    switch (type) {
      case "hist":
        return handleHist(request, response, currentUser, modelFacade);
      case "game":
        return handleGame(request, response, currentUser, modelFacade);
      case "record":
        return handleRecord(request, response, currentUser, modelFacade);
      case "board":
        return handleBoard(request, response, currentUser, modelFacade);
      default:
        throw new InvalidApiCallException("invalid type specified for query request");
    }
  }

  @Override
  protected void validate() throws InvalidApiCallException, FailedApiCallException {
    super.validate();
    switch (type) {
      case "hist":
        assertNotNull(username, "username");
        break;
      case "game":
      case "record":
      case "board":
        assertNotNull(gameId, "gameId");
        break;
      default:
        throw new InvalidApiCallException("invalid type for user");
    }
  }
}
