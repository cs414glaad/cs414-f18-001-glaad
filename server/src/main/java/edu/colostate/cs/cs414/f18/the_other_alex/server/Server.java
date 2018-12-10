package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
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

  public static void main(String[] args) throws InvalidInputException{
    int port = 3001;
    Server server = new Server(port);
    addShutdownHook(server);
    System.out.println("Server started on port: "+port);
  }

  private void linkRoutes() {
    String path = "/public"; // ?
    Spark.staticFileLocation(path); // ?
  get("/", modelManager::root);
  get("/logout", modelManager::logout);
  post("/login", modelManager::login);
  post("/query", modelManager::query);
  post("/user", modelManager::user);
  post("/game", modelManager::game);

  }

  public Server(int port) throws InvalidInputException {
    loadState();
    setPort(port);
    linkRoutes();
    setAccessControl();
  }

  private void setPort(int port) {
    this.port = port;
    port(this.port);
  }

  private void loadState() {
    modelManager = new ModelManager();
  }

  private void setAccessControl() {
    options("/*",
            (request, response) -> {

              String accessControlRequestHeaders = request
                      .headers("Access-Control-Request-Headers");
              if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
              }

              String accessControlRequestMethod = request
                      .headers("Access-Control-Request-Method");
              if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
              }

              return "OK";
            });

    before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
  }
}
