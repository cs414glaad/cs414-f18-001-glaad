package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Database;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.sql.SQLException;

public class TestDatabase {

    private Database database = new Database();

    @Test
    public void testAddingAndFindingGame() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException{
        long u1ID = -1;
        long u2ID = -1;
        Game g = null;
        try {
            User testUser1 = new User("User1", "user1@gmail.com", "passw0rd");
            User testUser2 = new User("User1", "user2@gmail.com",
                    "TheArtistFormallyKnownAsPooprat");
            Game testGame = new Game(testUser1, testUser2, "1");

            u1ID = database.addSerializedObject(testUser1);
            u2ID = database.addSerializedObject(testUser2);
            database.addSerializedObject(testGame);

            g = database.getGame(testGame.getGameId().hashCode());
            assertEquals("1", g.getGameId());
        }
        finally {
            database.deleteUserEntryUsingID((int) u1ID);
            database.deleteUserEntryUsingID((int) u2ID);
            database.deleteGameEntryUsingID(g.getGameId().hashCode());
        }
    }

    @Test
    public void testAddingAndFindingUserByEmail() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        long uID = -1;
        User u = null;
        try {
            User testUser = new User("User1", "user1@gmail.com", "passw0rd");

            uID = database.addSerializedObject(testUser);

            u = database.getUserByEmail("user1@gmail.com");

            assertEquals("User1", u.getUsername());
        } finally {
            database.deleteUserEntryUsingID((int) uID);
        }
    }

    @Test
    public void testAddingAndFindingUserByUsername() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        long uID = -1;
        User u = null;
        try {
            User testUser = new User("User1", "user1@gmail.com", "passw0rd");

            uID = database.addSerializedObject(testUser);

            u = database.getUser("User1");

            assertEquals("user1@gmail.com", u.getEmail());
        } finally {
            database.deleteUserEntryUsingID((int) uID);
        }
    }


    @Test
    public void testAddingAndFindingUserByUserID() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        int uID = -1;
        User u = null;
        try {
            User testUser = new User("User1", "user1@gmail.com", "passw0rd");

            uID = database.addSerializedObject(testUser);

            u = database.findSerializedUserByUserID(uID);

            assertEquals("User1", u.getUsername());
        } finally {
            database.deleteUserEntryUsingID((int) uID);
        }
    }

}
