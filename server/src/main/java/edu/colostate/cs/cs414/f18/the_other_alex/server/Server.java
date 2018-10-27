package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.ModelFacade;
import spark.Spark;

import static spark.Spark.*;

public class Server {

  private int port;
  private ModelManager modelManager;

  public static void main(String[] args) {
    int port = 3001;
    Server server = new Server(port);
    System.out.println("Server started on port: "+port);
  }

  private void linkRoutes() {
    String path = "/public"; // ?
    Spark.staticFileLocation(path); // ?

    get("/", modelManager::root);
    get("/login", modelManager::login);
    get("/query", modelManager::query);
    get("/user", modelManager::user);
    get("/game", modelManager::game);
  }

  public Server(int port) {
    loadState();
    setPort(port)
    linkRoutes();
  }

  private void setPort(int port) {
    this.port = port;
    port(this.port);
  }

  private void loadState() {
    modelManager = new ModelManager();
  }
}
