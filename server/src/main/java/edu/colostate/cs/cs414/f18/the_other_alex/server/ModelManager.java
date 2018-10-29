package edu.colostate.cs.cs414.f18.the_other_alex.server;

import spark.Request;
import spark.Response;

public class ModelManager {
  /**
   * This is the entry route to the website.
   *
   * @param request The HTTP request
   * @param response The HTTP response
   * @return The HTTP body
   */
  public String root(Request request, Response response) {
    response.redirect("index.html");
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
    response.type("text/html");
    return "Testing login\n";
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
