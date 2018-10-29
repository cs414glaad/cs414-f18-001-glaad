package edu.colostate.cs.cs414.f18.the_other_alex.server;

import com.google.gson.Gson;

public class RestCall {
  public String type;

  @Override
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
