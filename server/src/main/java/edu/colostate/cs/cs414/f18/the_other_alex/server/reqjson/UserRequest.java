package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.InviteList;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.ResponseData;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.UserList;
import java.io.IOException;
import java.sql.SQLException;
import spark.Request;
import spark.Response;

/**
 * Available game types are:
 *
 * - 'replno':
 *     requires inviteId
 *     rejects invite
 *     returns ResponseData
 *
 * - 'replcancel':
 *     requires inviteId
 *     cancels invite
 *     returns ResponseData
 *
 * - 'repl':
 *     requires inviteId
 *     accepts invite
 *     returns ResponseData
 * - 'inv':
 *     requires toUser, optional: inviteId
 *     sends invite to toUser
 *     returns InviteList
 * - 'user':
 *     requires username, email, password
 *     creates new user
 *     returns UserList
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

  private String handleUserReplno(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException,
          FailedApiCallException, InvalidInputException,
          SQLException, ClassNotFoundException, IllegalAccessException, IOException, InstantiationException{
    try {
      modelFacade.rejectInvite(currentUser, inviteId);
    } catch ( UserNotFoundException e) {
      throw new IllegalAccessException();
    }
    ResponseData responseData = new ResponseData(ResponseData.SUCCESS, "rejected invite");
    return responseData.toString();
  }

  private String handleUserReplcancel(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException,
          FailedApiCallException, InvalidInputException,
          SQLException, ClassNotFoundException, IllegalAccessException, IOException, InstantiationException{
    try {
      modelFacade.cancelInvite(currentUser, inviteId);
    }
    catch ( UserNotFoundException e) {
      throw new IllegalAccessException();
    }
    ResponseData responseData = new ResponseData(ResponseData.SUCCESS, "cancelled invite");
    return responseData.toString();
  }

  private String handleUserRepl(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException,
          FailedApiCallException, InvalidInputException,
          SQLException, ClassNotFoundException, IllegalAccessException, IOException, InstantiationException{
    try {
      modelFacade.acceptInvite(currentUser, inviteId);
    } catch ( UserNotFoundException e) {
      throw new IllegalAccessException();
    }
    ResponseData responseData = new ResponseData(ResponseData.SUCCESS, "accepted invite");
    return responseData.toString();
  }

  private String handleUserInv(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException,
          FailedApiCallException, InvalidInputException,
          SQLException, ClassNotFoundException, IllegalAccessException, IOException, InstantiationException{
    Invite invite = modelFacade.sendInvite(currentUser, toUser, inviteId);
    InviteList inviteList = new InviteList(invite);
    return inviteList.toString();
  }

  private String handleUserUser(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException, InvalidApiCallException,
          SQLException, IOException, ClassNotFoundException , IllegalAccessException, InstantiationException
  {
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
  protected String handleRequest(Request request, Response response, String currentUser, ModelFacade modelFacade) throws FailedApiCallException, InvalidApiCallException,
          SQLException, IOException, ClassNotFoundException , IllegalAccessException, InstantiationException, InvalidInputException {
    switch(type) {
      case "replcancel"://cancel invite
        return handleUserReplcancel(request, response, currentUser, modelFacade);
      case "replno": // reject invite
        return handleUserReplno(request, response, currentUser, modelFacade);
      case "repl": // accept invite
        return handleUserRepl(request, response, currentUser, modelFacade);
      case "inv": // send invite
        return handleUserInv(request, response, currentUser, modelFacade);
      case "user": // create user
        return handleUserUser(request, response, currentUser, modelFacade);
    }
    return null;
  }

  @Override
  public void validate(String currentUser) throws InvalidApiCallException, FailedApiCallException {
    super.validate(currentUser);
    switch (type) {
      case "replcancel": //cancel invite
      case "replno": // reject invite
      case "repl": // accept invite
        assertNotNull(inviteId, "inviteId");
        requireLoggedIn(currentUser);
        break;
      case "inv": // create invite
        assertNotNullOrEmpty(toUser, "toUser");
        requireLoggedIn(currentUser);
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
