package edu.colostate.cs.cs414.f18.the_other_alex.server;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ServerTest {
  Server server;

  @BeforeEach
  void setUp() {
    server = new Server(100);
  }
}
