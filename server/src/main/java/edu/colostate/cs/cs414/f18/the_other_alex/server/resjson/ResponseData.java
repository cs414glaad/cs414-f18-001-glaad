package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.server.RestCall;

public class ResponseData extends RestCall {
  public String msg;
  public static final String SUCCESS = "success";
  public static final String FAILURE = "failure";
  public static final String INVALID = "invalid";

  /**
   * Valid id's are invalid, failure, success
   */
  public ResponseData(String status, String msg) {
    super(status);
    this.msg = msg;
  }
}
