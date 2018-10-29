package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

import java.util.ArrayList;

public class GameList extends RestCall {
  public GameList() {
    games = new ArrayList<>();
  }

  public GameList(Game game) {
    this();
    games.add(new GameData(game));
  }
  public ArrayList<GameData> games;
}
