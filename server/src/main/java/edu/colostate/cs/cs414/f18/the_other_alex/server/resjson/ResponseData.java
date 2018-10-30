package edu.colostate.cs.cs414.f18.the_other_alex.server.resjson;

import edu.colostate.cs.cs414.f18.the_other_alex.server.DataType;

/**
 * Valid id's are invalid, failure, success
 */
public class ResponseData extends DataType {
  public String msg;

  public ResponseData(String id, String msg) {
    this.id = id;
    this.msg = msg;
  }
}
