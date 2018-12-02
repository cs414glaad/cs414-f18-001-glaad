package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Database;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDatabase {

  private Game testGame;
  private User testUser1;
  private User testUser2;
  private Database database = new Database();

  @Test
  public void testAddingGame() throws InvalidInputException, ClassNotFoundException, SQLException,
      InstantiationException, IllegalAccessException, IOException {
    testUser1 = new User("User1", "user1@gmail.com", "passw0rd");
    testUser2 = new User("User1", "user2@gmail.com", "pooprat");
    testGame = new Game(testUser1, testUser2, "1");
    long u1ID = database.addSerializedObject(testUser1);
    System.out.println("User one inserted");
    long u2ID = database.addSerializedObject(testUser2);
    System.out.println("User two inserted");
    database.addSerializedObject(testGame);
    System.out.println("Game inserted");
    Game g = database.findSerializedGameByID(1);
    assertEquals(g.getGameId(), "1");
    database.deleteGameEntryUsingID(1);
    database.deleteUserEntryUsingID((int)u1ID);
    database.deleteUserEntryUsingID((int)u2ID);
  }
}
