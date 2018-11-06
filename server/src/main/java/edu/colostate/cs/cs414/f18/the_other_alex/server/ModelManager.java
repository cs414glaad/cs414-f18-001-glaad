package edu.colostate.cs.cs414.f18.the_other_alex.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson.GameRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson.QueryRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson.UserRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.ResponseData;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class ModelManager {
  private static final String SESSION_NAME = "username";
  private ModelFacade modelFacade;
  private Gson gson;
  private JsonParser jsonParser;

  public ModelManager() {
    modelFacade = new ModelFacade();
    gson = new Gson();
    jsonParser = new JsonParser();
  }

  private String failedApiCall(Request request, Response response, String msg) {
    response.status(HttpStatus.FORBIDDEN_403);
    ResponseData responseData = new ResponseData(ResponseData.FAILURE, msg);
    return responseData.toString();
  }

  private String invalidApiCall(Request request, Response response, String msg) {
    response.status(HttpStatus.BAD_REQUEST_400); // is this right?
    ResponseData responseData = new ResponseData(ResponseData.INVALID, msg);
    return responseData.toString();
  }

  private String getUsername(Request request) {
    return request.session().attribute(SESSION_NAME);
  }

  private <T extends RestRequest> String handleRequest(Request request, Response response, Class<T> classOfT) {
    try {
      T t = validateRequest(request, classOfT);
      return t.handleRequest(request, response, getUsername(request), modelFacade);
    } catch (InvalidApiCallException e) {
      return invalidApiCall(request, response, e.getMessage());
    } catch (FailedApiCallException e) {
      return failedApiCall(request, response, e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      return failedApiCall(request, response, e.getMessage());
    }
  }

  private <T extends RestRequest> T validateRequest(Request request, Class<T> classOfT)
      throws InvalidApiCallException, FailedApiCallException {
    try {
      JsonElement jsonElement = jsonParser.parse(request.body());
      T t = gson.fromJson(jsonElement, classOfT);
      if (t == null) {
        throw new InvalidApiCallException("must send request for api call");
      }
      t.validate();
      return t;
    } catch (JsonSyntaxException e) {
      throw new InvalidApiCallException(e.getMessage());
    }
  }

  /**
   * This is the entry route to the website. If the user is not logged in, it redirects to the login page. If the user
   * is logged in, it redirects to the main page.
   *
   * @param request  The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String root(Request request, Response response) {
    response.redirect("index.html");
    return null;
  }

  /**
   * This route is used to create the session and validates the user.
   * <p>
   * This route forwards to the following methods from the ModelFacade:
   * <p>
   * - authenticate(String username, String email, String password): boolean
   *
   * @param request  The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String login(Request request, Response response) {
    try {
      String username = request.queryParams("username");
      String email = request.queryParams("email");
      String password = request.queryParams("password");
      User user = modelFacade.authenticate(username, email, password);
      if (user == null) {
        throw new FailedApiCallException("unable to log user in");
      } else {
        request.session().attribute(SESSION_NAME, user.getUsername());
      }
    } catch (FailedApiCallException e) {
      return failedApiCall(request, response, e.getMessage());
    } catch (InvalidApiCallException e) {
      return invalidApiCall(request, response, e.getMessage());
    }
    response.redirect("/");
    return null;
  }

  /**
   * Used to log the user out.
   *
   * @param request  The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String logout(Request request, Response response) {
    request.session().removeAttribute(SESSION_NAME);
    response.redirect("/");
    return null;
  }

  /**
   * This route forwards to the following methods from the ModelFacade:
   * <p>
   * - getUserHistory(String username): UserHistory
   * - getGame(String gameId): Game
   * - getGameRecord(String gameId): GameRecord
   * - getBoard(String gameId): String[][]
   * <p>
   * Json objects sent to this method are of the format defined by QueryRequest.
   *
   * @param request  The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String query(Request request, Response response) {
    response.type("application/json");
    return handleRequest(request, response, QueryRequest.class);
  }
  /**
   * This route forwards to the following methods from the ModelFacade:
   *
   * - createInvite(String fromUser, String toUser): Invite
   * - createUser(String username, email, password): boolean
   *
   * Json objects sent to this method are of the format defined by UserRequest.
   *
   * @param request The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String user(Request request, Response response) {
    response.type("application/json");
    return handleRequest(request, response, UserRequest.class);
  }

  /**
   * This route forwards to the following methods from the ModelFacade:
   *
   * - makeMove(String gameId, String fromPiece, String toPiece, String username): boolean
   *
   * Json objects sent to this method are of the format defined by GameRequest.
   *
   * @param request The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String game(Request request, Response response) {
    response.type("application/json");
    return handleRequest(request, response, GameRequest.class);
  }

  public void shutdown() {
    modelFacade.shutdown();
  }
}
