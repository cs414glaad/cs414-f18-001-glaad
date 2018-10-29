package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

/**
 * This type of request is for making changes in the game aspects of the model.
 *
 * Available types are:
 *
 * - 'move': requires gameId, fromCell, toCell
 *
 * Json format:
 * {
 *   type: {type},
 *   gameId: {gameId},
 *   fromCell: {cellId},
 *   toCell: {cellId},
 * }
 */
public class GameRequest extends RestCall {
  public String gameId;
  public String fromCell;
  public String toCell;
}
