package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

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
public class QueryRequest extends RestCall {
  public String username;
  public String gameId;
}
