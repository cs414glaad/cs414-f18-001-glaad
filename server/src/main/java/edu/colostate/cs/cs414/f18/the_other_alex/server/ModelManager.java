package edu.colostate.cs.cs414.f18.the_other_alex.server;

import spark.Request;
import spark.Response;

public class ModelManager {
  public String root(Request request, Response response) {
    response.type("text/html");
    return "Testing root";
    //res.redirect("index.html");
    //return null;
  }

  public String login(Request request, Response response) {
    response.type("text/html");
    return "Testing login\n";
  }

  public String query(Request request, Response response) {
    response.type("text/html");
    return "Testing query\n";
  }

  public String user(Request request, Response response) {
    response.type("text/html");
    return "Testing user\n";
  }

  public String game(Request request, Response response) {
    response.type("text/html");
    return "Testing game\n";
  }
}
