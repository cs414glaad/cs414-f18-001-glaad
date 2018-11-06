package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

import java.util.ArrayList;

public class InviteList extends RestCall {
  public InviteList() {
    super();
    invites = new ArrayList<>();
  }

  public InviteList(Invite invite) {
    this();
    invites.add(new InviteData(invite));
  }
  public ArrayList<InviteData> invites;
}
