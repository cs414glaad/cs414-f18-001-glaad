package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

import java.util.ArrayList;

public class UserList extends RestCall {
  public ArrayList<UserData> users;

  public UserList() {
    users = new ArrayList<>();
  }

  public UserList(User user) {
    this();
    users.add(new UserData(user));
  }
}
