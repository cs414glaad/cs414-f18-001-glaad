package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

import java.util.ArrayList;

public class UserData extends DataType {
  public String username;
  public String email;
  public UserHistoryData userHistory;
  public InviteData[] invites;
  public InviteData[] receivedInvites;
  public String[] games;

  private InviteData[] invitesToList(ArrayList<Invite> invites) {
    InviteData[] invitesData = new InviteData[invites.size()];
    for (int i = 0; i < invitesData.length; i++) {
      invitesData[i] = new InviteData(invites.get(i));
    }
    return invitesData;
  }

  public UserData(User user) {
    username = user.getUsername();
    email = user.getEmail();
    userHistory = new UserHistoryData(user.getUserHistory());
    invites = invitesToList(user.getPendingInvites());
    receivedInvites = invitesToList(user.getPendingReceivedInvites());
    games = new String[user.getGames().size()];
    for (int i = 0; i < games.length; i++) {
      games[i] = user.getGames().get(i);
    }
  }
}
