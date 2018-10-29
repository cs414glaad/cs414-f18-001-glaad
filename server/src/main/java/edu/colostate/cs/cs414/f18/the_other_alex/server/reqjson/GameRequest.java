package edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson;

import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

public class GameRequest extends RestCall {
  public String gameId;
  public String fromCell;
  public String toCell;
  public String username;
}
