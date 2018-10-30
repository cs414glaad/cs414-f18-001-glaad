package edu.colostate.cs.cs414.f18.the_other_alex.server;

import spark.Spark;

import static spark.Spark.*;

public class Server {

  private int port;
  private ModelManager modelManager;

  private static void addShutdownHook(Server server) {
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      @Override
      public void run()
      {
        server.shutdown();
      }
    });
  }

  public void shutdown() {
    modelManager.shutdown();
  }

  public static void main(String[] args) {
    int port = 3001;
    Server server = new Server(port);
    addShutdownHook(server);
    System.out.println("Server started on port: "+port);
  }

  private void linkRoutes() {
    String path = "/public"; // ?
    Spark.staticFileLocation(path); // ?

    get("/", modelManager::root);
    post("/login", modelManager::login);
    post("/query", modelManager::query);
    post("/user", modelManager::user);
    post("/game", modelManager::game);
  }

  public Server(int port) {
    loadState();
    setPort(port);
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
