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
  }

  public boolean createUser(String username, String email, String password) {
  }

  public Game getGame(String gameId) {
  }

  public GameRecord getGameRecord(String gameId) {
  }

  public boolean makeMove(String fromCell, String toCell, String user) {
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
  }
}
