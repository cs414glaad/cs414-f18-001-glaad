package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.InviteData;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.InviteList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.UserList;
import spark.Request;
import spark.Response;

/**
 * Available game types are:
 *
 * - 'inv': requires toUser
 * - 'user': requires username, email, password
 *
 * Json format:
 * {
 *   type: {type},
 *   email: {email},
 *   username: {username},
 *   password: {password},
 *   toUser: {toUsername},
 * }
 */
public class UserRequest extends RestRequest {
  public String username;
  public String email;
  public String password;
  public String toUser; // username of new user

  private String handleUserInv(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    Invite invite = modelFacade.createInvite(currentUser, toUser);
    InviteList inviteList = new InviteList(invite);
    return inviteList.toString();
  }

  private String handleUserUser(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    User user = modelFacade.createUser(
        username,
        email,
        password);
    UserList userList = new UserList(user);
    return userList.toString();
  }

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade) {
    switch(type) {
      case "inv":
        return handleUserInv(request, response, currentUser, modelFacade);
      case "user":
        return handleUserUser(request, response, currentUser, modelFacade);
    }
    return null;
  }

  @Override
  public void validate() throws InvalidApiCallException {
    if (type == null) {
      throw new InvalidApiCallException("type for user not specified");
    }
    switch (type) {
      case "inv":
        if (toUser == null) {
          invalidCall("toUser required for 'inv' type");
        }
        break;
      case "user":
        if (username == null) {
          invalidCall("username required for 'user' type");
        }
        if (email == null) {
          invalidCall("email required for 'user' type");
        }
        if (password == null) {
          invalidCall("password required for 'user' type");
        }
        break;
      default:
        throw new InvalidApiCallException("invalid type for user");
    }
  }
}
