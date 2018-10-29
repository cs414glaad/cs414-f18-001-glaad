package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

public class InviteData extends DataType {
  public String fromUser;
  public String[] toUser;

  public InviteData(Invite invite) {
    fromUser = invite.getFromUser().getUsername();
    toUser = (String[])(invite.getToUsers().toArray());
  }
}
