package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class UserData extends DataType {
  public String username;
  public String email;
  public UserHistoryData userHistory;
  public InviteData[] invites;
  public String[] games;

  public UserData(User user) {
    username = user.getUsername();
    email = user.getEmail();
    userHistory = new UserHistoryData(user.getUserHistory());
    invites = new InviteData[user.getPendingInvites().size()];
    for (int i = 0; i < invites.length; i++) {
      invites[i] = new InviteData(user.getPendingInvites().get(i));
    }
    games = new String[user.getGames().size()];
    for (int i = 0; i < games.length; i++) {
      games[i] = user.getGames().get(i);
    }
  }
}
