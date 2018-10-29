package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

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
public class UserRequest extends RestCall {
  public String username;
  public String email;
  public String password;
  public String toUser; // username of new user
}
