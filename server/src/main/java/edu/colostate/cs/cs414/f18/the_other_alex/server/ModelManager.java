package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import spark.Request;
import spark.Response;

public class ModelManager {
  private static final String SESSION_NAME = "username";
  private ModelFacade modelFacade;

  public ModelManager() {
    modelFacade = new ModelFacade();
  }
  /**
   * This is the entry route to the website. If the user is not logged in, it redirects to the login page. If the user
   * is logged in, it redirects to the main page.
   *
   * @param request The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String root(Request request, Response response) {
    String username = request.session().attribute(SESSION_NAME);
    if (username == null) {
      response.redirect("login.html");
    } else {
      // TODO: validate user
      response.redirect("index.html");
    }
    return null;
  }

  /**
   * This route is used to create the session and validates the user.
   *
   * This route forwards to the following methods from the ModelFacade:
   *
   * - authenticate(String user, String password): boolean
   *
   * @param request The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String login(Request request, Response response) {
    String email = request.queryParams("email");
    String password = request.queryParams("password");
    String username = modelFacade.getUserByEmail(email).getUsername();
    if (modelFacade.authenticate(username, password)) {
      request.session().attribute(SESSION_NAME, username);
    }
    response.redirect("/");
    return null; // TODO: return an error on failure?
  }

  /**
   * This route forwards to the following methods from the ModelFacade:
   *
   * - getUserHistory(String username): UserHistory
   * - getGame(String gameId): Game
   * - getGameRecord(String gameId): GameRecord
   * - getBoard(String gameId): String[][]
   *
   * Json objects sent to this method are of the format defined by QueryRequest.
   *
   * @param request The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String query(Request request, Response response) {
    response.type("text/html");
    return "Testing query\n";
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
    response.type("text/html");
    return "Testing user\n";
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
    response.type("text/html");
    return "Testing game\n";
  }
}
