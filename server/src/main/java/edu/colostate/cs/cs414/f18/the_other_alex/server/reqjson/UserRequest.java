package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.InviteList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.ResponseData;
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
 *   inviteId: {inviteId}
 * }
 */
public class UserRequest extends RestRequest {
  public String username;
  public String email;
  public String password;
  public String toUser; // username of new user
  public String inviteId;

  private String handleUserReplno(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    throw new FailedApiCallException("not implemented"); // TODO
  }

  private String handleUserRepl(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    modelFacade.acceptInvite(currentUser, inviteId);
    return (new ResponseData(ResponseData.SUCCESS, "accepted invite")).toString();
  }

  private String handleUserInv(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite(currentUser, toUser, null);
    InviteList inviteList = new InviteList(invite);
    return inviteList.toString();
  }

  private String handleUserUser(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException, InvalidApiCallException {
    try {
      User user = modelFacade.createUser(
          username,
          email,
          password);
      UserList userList = new UserList(user);
      return userList.toString();
    } catch (InvalidInputException e) {
      throw new InvalidApiCallException(e.getMessage());
    }
  }

  @Override
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException, InvalidApiCallException {
    switch(type) {
      case "replno": // reject invite
        return handleUserReplno(request, response, currentUser, modelFacade);
      case "repl": // accept invite
        return handleUserRepl(request, response, currentUser, modelFacade);
      case "inv": // send
        return handleUserInv(request, response, currentUser, modelFacade);
      case "user":
        return handleUserUser(request, response, currentUser, modelFacade);
    }
    return null;
  }

  @Override
  public void validate() throws InvalidApiCallException, FailedApiCallException {
    super.validate();
    switch (type) {
      case "replno": // reject invite
      case "repl": // accept invite
        assertNotNull(inviteId, "inviteId");
        break;
      case "inv": // create invite
        assertNotNullOrEmpty(toUser, "toUser");
        break;
      case "user": // create user
        assertNotNullOrEmpty(username, "username");
        assertNotNullOrEmpty(email, "email");
        assertNotNullOrEmpty(password, "password");
        break;
      default:
        throw new InvalidApiCallException("invalid type for user");
    }
  }
}
