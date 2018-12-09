package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.*;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelFacade;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.GameNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidMoveException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
            User testUser2 = new User("User2", "user2@gmail.com",
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

    @Test
    public void ensureBoardAndCellSerialization() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException, InvalidMoveException {
        long u1ID = -1;
        long u2ID = -1;
        Game g = null;
        try {
            User testUser1 = new User("User1", "user1@gmail.com", "passw0rd");
            User testUser2 = new User("User2", "user2@gmail.com",
                    "TheArtistFormallyKnownAsPooprat");
            Game testGame = new Game(testUser1, testUser2, "1");

            //flip the piece
            testGame.getBoard().move(testGame.getBoard().getCells()[1][1], testGame.getBoard().getCells()[1][1]);
            String type = testGame.getBoard().getCells()[1][1].getPiece().getType();

            u1ID = database.addSerializedObject(testUser1);
            u2ID = database.addSerializedObject(testUser2);
            database.addSerializedObject(testGame);

            g = database.getGame(testGame.getGameId().hashCode());


            assertTrue(g.getBoard().getCells()[1][1].getPiece().getIsFlipped());
            assertEquals(type, testGame.getBoard().getCells()[1][1].getPiece().getType());

        }
        finally {
            database.deleteUserEntryUsingID((int) u1ID);
            database.deleteUserEntryUsingID((int) u2ID);
            database.deleteGameEntryUsingID(g.getGameId().hashCode());
        }
    }

    @Test
    public void ensureGameStateSerialization() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException, InvalidMoveException {
        long u1ID = -1;
        long u2ID = -1;
        Game g = null;
        try {
            User testUser1 = new User("User1", "user1@gmail.com", "passw0rd");
            User testUser2 = new User("User2", "user2@gmail.com",
                    "TheArtistFormallyKnownAsPooprat");
            Game testGame = new Game(testUser1, testUser2, "1");

            u1ID = database.addSerializedObject(testUser1);
            u2ID = database.addSerializedObject(testUser2);
            database.addSerializedObject(testGame);

            g = database.getGame(testGame.getGameId().hashCode());

            g.getBoard().move(g.getBoard().getCells()[1][1], g.getBoard().getCells()[1][1]);

            assertEquals(GameState.IN_PROGRESS, g.getGameState());

        }
        finally {
            database.deleteUserEntryUsingID((int) u1ID);
            database.deleteUserEntryUsingID((int) u2ID);
            database.deleteGameEntryUsingID(g.getGameId().hashCode());
        }
    }

    @Test
    public void ensurePieceSerialization() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException, InvalidMoveException {
        long u1ID = -1;
        long u2ID = -1;
        Game g = null;
        try {
            User testUser1 = new User("User1", "user1@gmail.com", "passw0rd");
            User testUser2 = new User("User2", "user2@gmail.com",
                    "TheArtistFormallyKnownAsPooprat");
            Game testGame = new Game(testUser1, testUser2, "1");

            String pieceType = testGame.getBoard().getCells()[0][1].getPiece().getType();

            u1ID = database.addSerializedObject(testUser1);
            u2ID = database.addSerializedObject(testUser2);
            database.addSerializedObject(testGame);

            g = database.getGame(testGame.getGameId().hashCode());

            g.getBoard().move(g.getBoard().getCells()[1][1], g.getBoard().getCells()[1][1]);

            assertEquals(pieceType, g.getBoard().getCells()[0][1].getPiece().getType());

        }
        finally {
            database.deleteUserEntryUsingID((int) u1ID);
            database.deleteUserEntryUsingID((int) u2ID);
            database.deleteGameEntryUsingID(g.getGameId().hashCode());
        }
    }

    @Test
    public void ensureInviteSerialization() throws InvalidInputException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException, InvalidMoveException, FailedApiCallException,
            UserNotFoundException, GameNotFoundException {
        ModelFacade m = new ModelFacade(true);
        long u1ID = -1;
        long u2ID = -1;
        Game g = null;
        try {
            User testUser1 = m.createUser("User1", "user1@gmail.com", "passw0rd");
            User testUser2 = m.createUser("User2", "user2@gmail.com",
                    "TheArtistFormallyKnownAsPooprat");


            Invite i = m.sendInvite("User1", "User2", null);

            User u = m.getUser("User1");

            assertEquals("User2", u.getPendingInvites().get(0).getToUsers().get(0));

        }
        finally {
            m.deleteUserEntryUsingUsername("User1");
            m.deleteUserEntryUsingUsername("User2");
        }
    }
}
