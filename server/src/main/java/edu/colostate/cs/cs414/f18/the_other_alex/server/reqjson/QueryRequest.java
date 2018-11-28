package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Board;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.model.GameRecord;
import edu.colostate.cs.cs414.f18.the_other_alex.model.UserHistory;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.BoardList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.GameList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.GameRecordList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.UserHistoryList;
import spark.Request;
import spark.Response;

/**
 * Available game types are:
 *
 * - 'hist':
 *     requires username
 *     gets user history
 *     returns UserHistoryList
 * - 'game':
 *     requires gameId
 *     gets a game by id
 *     returns GameList
 * - 'record':
 *     requires gameId
 *     gets a game record by id
 *     returns GameRecordList
 * - 'board':
 *     requires gameId
 *     gets board by id
 *     returns GameBoardList
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
   * @return a UserHistory data type
   */
  private String handleHist(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    try {
      UserHistory userHistory = modelFacade.getUserHistory(username);
      return (new UserHistoryList(userHistory)).toString();
    } catch (UserNotFoundException e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  /**
   * Calls getGame(String gameId) from Game
   * @return
   */
  private String handleGame(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    Game game = modelFacade.getGame(gameId);
    GameList gameList = new GameList(game);
    return gameList.toString();
  }

  /**
   * Calls getGameRecord(String gameId) from GameRecord
   * @return
   */
  private String handleRecord(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    GameRecord gameRecord = modelFacade.getGameRecord(gameId);
    GameRecordList gameRecordList = new GameRecordList(gameRecord);
    return gameRecordList.toString();
  }

  /**
   * Calls getBoard(String gameId) from Game
   * @return
   */
  private String handleBoard(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    Board board = modelFacade.getBoard(gameId);
    BoardList boardList = new BoardList(board);
    return boardList.toString();
  }

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade)
      throws InvalidApiCallException, FailedApiCallException {
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
