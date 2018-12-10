package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;

public class ServerTest {
  Server server;

  @BeforeEach
  void setUp() throws InvalidInputException {
    server = new Server(100);
  }
}
