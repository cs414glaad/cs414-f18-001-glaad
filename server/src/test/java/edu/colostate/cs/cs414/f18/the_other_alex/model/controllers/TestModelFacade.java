package edu.colostate.cs.cs414.f18.the_other_alex.model.controllers;

import edu.colostate.cs.cs414.f18.the_other_alex.model.Game;
import edu.colostate.cs.cs414.f18.the_other_alex.model.Invite;
import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.GameNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.InvalidInputException;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.FailedApiCallException;
import edu.colostate.cs.cs414.f18.the_other_alex.server.exceptions.InvalidApiCallException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestModelFacade {

  User user1;
  User user2;
  User user3;
  User user4;
  ModelFacade modelFacade;

  @BeforeEach
  void setUp() throws InvalidInputException, FailedApiCallException, SQLException, IOException,
          ClassNotFoundException , IllegalAccessException, InstantiationException {
    modelFacade = new ModelFacade(false);
    user1 = modelFacade.createUser("user1", "user1@email.com", "passwd");
    user2 = modelFacade.createUser("user2", "user2@email.com", "passwd");
    user3 = modelFacade.createUser("user3", "user3@email.com", "passwd");
    user4 = modelFacade.createUser("user4", "user4@email.com", "passwd");
  }

  @Test
  void getUserHistory() throws UserNotFoundException {
    modelFacade.getUserHistory("user1");
  }

  @Test
  void sendInvite() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    assertEquals(user1, invite.getFromUser(), "fromUser set properly");
    assertTrue(invite.getToUsers().contains("user2"));
    assertTrue(user1.getPendingInvites().contains(invite), "user1 has sent invite");
    assertTrue(user2.getPendingReceivedInvites().contains(invite), "user2 has received invite");
    assertEquals(1, invite.countObservers());
  }

  @Test
  void sendInviteDoubleSame() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    Invite secondSend = modelFacade.sendInvite("user1", "user2", invite.getInviteId());
    assertEquals(invite, secondSend);
  }

  @Test
  void sendInviteDoubleDiff() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    Invite secondSend = modelFacade.sendInvite("user1", "user2", null);
    assertNotEquals(invite, secondSend);
  }

  @Test
  void acceptInvite() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    modelFacade.sendInvite("user1", "user3", invite.getInviteId());
    modelFacade.acceptInvite("user2", invite.getInviteId());
    // nobody should have the invite
    assertTrue(user1.getPendingInvites().isEmpty());
    assertTrue(user2.getPendingReceivedInvites().isEmpty());
    assertTrue(user3.getPendingReceivedInvites().isEmpty());
    // user1, user2 should have the game now
    assertFalse(user1.getGames().isEmpty());
    assertFalse(user2.getGames().isEmpty());
    assertEquals(invite.getInviteId(), user1.getGames().get(0));
    // invite should have one user in toUsers
    assertEquals(1, invite.getToUsers().size());
    // toUser should be set
    assertEquals(user2.getUsername(), invite.getToUser());
    // fromUser should still have from user
    assertEquals(user1, invite.getFromUser());
  }

  @Test
  void acceptInviteMulti() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    modelFacade.sendInvite("user1", "user3", invite.getInviteId());
    modelFacade.acceptInvite("user2", invite.getInviteId());
    // user3 shouldn't have the invite anymore
    assertTrue(user3.getPendingReceivedInvites().isEmpty());
  }

  @Test
  void rejectInvite() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    modelFacade.rejectInvite("user2", invite.getInviteId());
    assertTrue(invite.getToUsers().isEmpty());
    assertTrue(user2.getPendingReceivedInvites().isEmpty());
  }

  @Test
  void rejectInviteMultiInvite() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    modelFacade.sendInvite("user1", "user3", invite.getInviteId());
    modelFacade.rejectInvite("user2", invite.getInviteId());
    assertFalse(invite.getToUsers().contains(user2.getUsername()));
    assertTrue(invite.getToUsers().contains(user3.getUsername()));
    assertTrue(user2.getPendingReceivedInvites().isEmpty());
    assertFalse(user3.getPendingReceivedInvites().isEmpty());
  }

  @Test
  void createUser() throws InvalidInputException, FailedApiCallException, UserNotFoundException {
    assertEquals(user1, modelFacade.getUser("user1"));
    assertEquals("user1", user1.getUsername());
    assertEquals("user1@email.com", user1.getEmail());
    assertThrows(FailedApiCallException.class, () -> {
      modelFacade.createUser(user1.getUsername(), "user1new@email.com", "passwd");
    });
    assertThrows(FailedApiCallException.class, () -> {
      modelFacade.createUser("user1new", user1.getEmail(), "passwd");
    });
  }

  @Test
  void failedCreateUser() throws InvalidInputException{
    assertThrows(InvalidInputException.class, ()->{
      modelFacade.createUser("joe","jabroni mail","123123");
    });
  }

  @Test
  void getUserByEmail() throws UserNotFoundException {
    assertEquals(user1, modelFacade.getUserByEmail("user1@email.com"));
    assertThrows(UserNotFoundException.class, () -> modelFacade.getUserByEmail("bad email"));
  }

  @Test
  void getUser() throws UserNotFoundException {
    assertEquals(user1, modelFacade.getUser(user1.getUsername()));
  }

  private String generateGame() throws FailedApiCallException {
    Invite invite = modelFacade.sendInvite("user1", "user2", null);
    return modelFacade.acceptInvite("user2", invite.getInviteId());
  }

  @Test
  void getGame() throws FailedApiCallException, GameNotFoundException {
    String gameId = generateGame();
    assertEquals(gameId, modelFacade.getGame(gameId).getGameId());
  }

  @Test
  void getGameRecord() throws FailedApiCallException {
    String gameId = generateGame();
    modelFacade.getGameRecord(gameId);
  }

  @Test
  void getBoard() throws FailedApiCallException {
    modelFacade.getBoard(generateGame());
  }

  @Test
  void authenticate() throws InvalidApiCallException, FailedApiCallException {
    assertEquals(user1, modelFacade.authenticate(user1.getUsername(), null, "passwd"));
    assertEquals(user1, modelFacade.authenticate(null, user1.getEmail(), "passwd"));
    assertThrows(InvalidApiCallException.class, () -> {
      modelFacade.authenticate(null, null, "passwd");
    });
    assertThrows(FailedApiCallException.class, () -> {
      modelFacade.authenticate(user1.getUsername(), null, "bad password");
    });
    assertThrows(FailedApiCallException.class, () -> {
      modelFacade.authenticate(null, user1.getEmail(), "bad password");
    });
  }

  @Test
  void shutdown() {
    modelFacade.shutdown(); // assert no throw
  }

  @Test
  void makeMove() throws FailedApiCallException, GameNotFoundException {
    Game game = modelFacade.getGame(generateGame());
    User turn = game.getTurn();
    modelFacade.makeMove(game.getGameId(), "0 0", "0 0", turn.getUsername());
  }
}