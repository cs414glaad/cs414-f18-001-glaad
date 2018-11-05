package edu.colostate.cs.cs414.f18.the_other_alex.server;

import com.google.gson.Gson;

public abstract class RestCall {
  public String type;
  public String status;

  /**
   * The default return status is return and success.
   */
  public RestCall() {
    this("success");
  }

  /**
   * The status can be specified and uses the default return type.
   * @param status
   */
  public RestCall(String status) {
    this("return", status);
  }

  /**
   * Specify both type and status. Usually, Gson builds the objects, so when type and status are specified, it's
   * unusual.
   *
   * @param type The type of rest call (return, move, inv, etc)
   * @param status The status (typically handled by client, not server).
   */
  public RestCall(String type, String status) {
    this.type = type;
    this.status = status;
  }

  @Override
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
