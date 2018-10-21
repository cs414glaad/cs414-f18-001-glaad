package edu.colostate.cs.cs414.f18.the_other_alex.server;

public class Server {

  private static Server server = null;

  private int port;

  public static void main(String[] args) {
    Server server = Server.getInstance();
    server.start();
  }

  private Server() {
    port = 3001;
  }

  public static Server getInstance() {
    if (server == null) {
      server = new Server();
    }
    return server;
  }

  public void start() {
    System.out.println("Staring server on port: "+port);
    System.out.println("But not really. Bye.");
  }
}
