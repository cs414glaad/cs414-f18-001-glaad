package edu.colostate.cs.cs414.f18.the_other_alex.server;

import com.google.gson.Gson;

public abstract class DataType {
  public String id;

  @Override
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
