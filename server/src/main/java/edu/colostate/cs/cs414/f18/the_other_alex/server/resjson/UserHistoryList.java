package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.model.UserHistory;
import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

import java.util.ArrayList;

public class UserHistoryList extends RestCall {
  public ArrayList<UserHistoryData> userHistories;

  public UserHistoryList() {
    super();
    userHistories = new ArrayList<>();
  }

  public UserHistoryList(UserHistory userHistory) {
    this();
    userHistories.add(new UserHistoryData(userHistory));
  }
}
