package edu.colostate.cs.cs414.f18.the_other_alex.server;

import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

public class Server {

  private int port;

  public static void main(String[] args) {
    int port = 3001;
    Server server = new Server(port);
    System.out.println("Server started on port: "+port);
  }

  public Server(int port) {
    this.port = port;
    port(this.port);

    String path = "/public"; // ?
    Spark.staticFileLocation(path); // ?

    get("/", (req, res) -> {
      res.redirect("index.html");
      return null;
    });

    get("/test", this::test);
  }

  private String test(Request request, Response response) {
    response.type("application/json");
    return "It's working.\n";
  }
}
