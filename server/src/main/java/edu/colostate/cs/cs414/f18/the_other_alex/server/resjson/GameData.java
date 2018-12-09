package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.model.GameState;
import edu.colostate.cs.cs414.f18.the_other_alex.model.PieceColor;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class GameData extends DataType {
  private String colorToString(PieceColor color) {
    switch (color) {
      case RED:
        return "red";
      case BLACK:
        return "black";
      case NONE:
      default:
        return "";
    }
  }

  private String gameStateToString(GameState gameState) {
    switch(gameState) {
      case IN_PROGRESS:
        return "in_progress";
      case OVER:
        return "over";
      default:
        return ""; // error
    }
  }

  public GameData(Game game) {
    id = game.getGameId();
    user1 = game.getUser1().getUsername();
    user2 = game.getUser2().getUsername();
    user1Color = colorToString(game.getUser1Color());
    user2Color = colorToString(game.getUser2Color());
    turn = game.getTurn().getUsername();
    gameState = gameStateToString(game.getGameState());
  }

  public String user1;
  public String user2;
  public String user1Color;
  public String user2Color;
  public String turn;
  public String gameState;
}
