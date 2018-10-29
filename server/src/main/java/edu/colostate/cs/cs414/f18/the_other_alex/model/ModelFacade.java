package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class ModelFacade {
  private ModelService modelService;

  public ModelFacade(ModelService modelService) {
    this.modelService = modelService;
  }

  public UserHistory getUserHistory(String username) {
    UserService userService = this.modelService.getUserService();
    User user = userService.getUser(username);
    return user.getUserHistory();
  }

  public Invite createInvite(String fromUser, String toUser) {
      return null; // TODO
  }

  public boolean createUser(String username, String email, String password) {
    try {
      modelService.getUserService().registerUser(username, email, password);
      return true;
    } catch (Exception e) {
      return false;
    }
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

  public boolean authenticate(String user, String password) {
    return false; // TODO
  }
}
