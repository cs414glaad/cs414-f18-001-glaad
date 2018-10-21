package edu.colostate.cs.cs414.f18.the_other_alex.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class ServerTest {
  Server server;

  @BeforeEach
  void setUp() {
    server = Server.getInstance();
  }

  @Test
  void testStart() {
    // Assumes doesn't block
    server.start(); // assert doesn't throw error
  }
}
