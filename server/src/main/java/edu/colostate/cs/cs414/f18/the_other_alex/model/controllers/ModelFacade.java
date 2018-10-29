package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.*;

public class ModelFacade {
  private ModelService modelService;

  public ModelFacade() {
    this.modelService = new ModelService();
  }

  public UserHistory getUserHistory(String username) {
    UserService userService = this.modelService.getUserService();
    User user = userService.getUser(username);
    return user.getUserHistory();
  }

  public Invite createInvite(String fromUser, String toUser) {
      return null; // TODO
  }

  public User createUser(String username, String email, String password) {
    return modelService.getUserService().registerUser(username, email, password);
  }

  public Game getGame(String gameId) {
    return modelService.getGameService().getGame(gameId);
  }

  public GameRecord getGameRecord(String gameId) {
    return modelService.getGameService().getGame(gameId).getGameRecord();
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

  public boolean makeMove(String gameId, String fromCellId, String toCellId, String username) {
    try {
      Game game = modelService.getGameService().getGame(gameId);
      Cell[][] cells = game.getBoard().getCells();
      User user = modelService.getUserService().getUser(username);
      Cell fromCell = getCellFromId(cells, fromCellId);
      Cell toCell = getCellFromId(cells, toCellId);
      game.makeMove(fromCell, toCell, user);
      return true;
    } catch (Exception e) {
      return false; // TODO: fix this
    }
  }

  /**
   * @param gameId - the unique game Id
   * @return Returns a string of cell Ids
   */
  public String[][] getBoard(String gameId) {
    Cell[][] cells = modelService.getGameService().getGame(gameId).getBoard().getCells();
    String[][] pieceIds = new String[cells.length][cells[0].length];
    for (int row = 0; row < cells.length; row++) {
      for (int col = 0; col < cells[row].length; col++) {
        pieceIds[row][col] = cells[row][col].getPiece().getId();
      }
    }
    return pieceIds;
  }

  /**
   * Returns false on failure. Returns false if either argument is null.
   * @param username
   * @param password
   * @return
   */
  public boolean authenticate(String username, String password) {
    return false; // TODO
  }

  public User getUserByEmail(String username) {
    return modelService.getUserService().getUserByEmail(username);
  }
}
