package edu.colostate.cs.cs414.f18.the_other_alex.server;

import org.junit.jupiter.api.BeforeEach;

public class ServerTest {
  Server server;

  @BeforeEach
  void setUp() {
    server = new Server(100);
  }
}
