package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.*;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.GameNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Database;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;

import java.util.ArrayList;
import java.util.Observable;

public class GameService extends Observable {
  private ArrayList<Game> cachedGames;
  private Database database;

  public GameService(Database database) {
    this.database = database;
    cachedGames = new ArrayList<>();
  }

  /**
   * Creates a game including the two players
   *
   * @param p1 the first player
   * @param p2 the second player
   * @return Returns the created game
   */
  public Game createGame(User p1, User p2, String id) {
    Game game = new Game(p1, p2, id);
    cachedGames.add(game);
    return game;
  }

  public GameOutcome getGameOutcome(String gameID) {
    return null;
  }

  public Game getGame(String gameId) throws GameNotFoundException {
    for (Game game : cachedGames) {
      if (game.getGameId().equals(gameId)) {
        return game;
      }
    }
    if (database != null) {
      try {
        database.getGame(gameId.hashCode());
      } catch(Exception e) {
        throw new GameNotFoundException();
      }
    }
    return null;
  }

  private int getRow(String id) {
    String[] parts = id.split(" ");
    return Integer.parseInt(parts[0]);
  }

  private int getCol(String id) {
    String[] parts = id.split(" ");
    return Integer.parseInt(parts[1]);
  }

  private Cell getCellFromId(Cell[][] cells, String id) {
    return cells[getRow(id)][getCol(id)];
  }

  /**
   * Makes a move and throws an exception on error.
   *
   * @param gameId the Id of the game
   * @param fromCellId The Id of the from cell ("row col" where i and j are the row and column)
   * @param toCellId the id of the to cell
   * @param user The username of the user making the move
   * @throws FailedApiCallException if the user can't make a move
   */
  public void makeMove(String gameId, String fromCellId, String toCellId, User user) throws FailedApiCallException {
    try {
      Game game = getGame(gameId);
      Cell[][] cells = game.getBoard().getCells();
      Cell fromCell = getCellFromId(cells, fromCellId);
      Cell toCell = getCellFromId(cells, toCellId);
      game.makeMove(fromCell, toCell, user);
    } catch (Exception e) {
      throw new FailedApiCallException(e.getMessage());
    }
  }

  public void shutdown() {
    notifyObservers();
  }
}
