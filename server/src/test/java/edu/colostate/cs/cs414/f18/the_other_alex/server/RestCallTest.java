package edu.colostate.cs.cs414.f18.the_other_alex.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson.GameRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson.QueryRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.reqjson.UserRequest;
import edu.colostate.cs.cs414.f18.the_other_alex.server.resjson.BoardList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestCallTest {

  Gson gson;
  JsonParser jsonParser;

  @BeforeEach
  void setUp() {
    gson = new Gson();
    jsonParser = new JsonParser();

  }

  @Test
  void testToString() {
    UserRequest input = new UserRequest();
    input.type = "testType";
    input.username = "testUsername";
    input.email = null;
    input.password = null;
    JsonElement jsonElement = jsonParser.parse(input.toString());
    UserRequest output = gson.fromJson(jsonElement, UserRequest.class);
    assertEquals(output.type, input.type);
    assertEquals(output.username, input.username);
    assertEquals(output.email, input.email);
    assertEquals(output.password, input.password);
  }

  private String buildBoardList() {
    String body = "{type:'listOfBoards',boards: [{cells:[";
    for (int row = 0; row < 8; row++) {
      body = body+"[";
      boolean isFlipped = false;
      String color = "black";
      if (!isFlipped) {
        color = null;
      } else if (color.equals("black")) {
        color = "red";
      } else if (color.equals("red")) {
        color = "black";
      }
      for (int col = 0; col < 4; col++) {
        String id = ""+row+" "+col;
        String append = "{id: '"+id+"',";
        if (isFlipped) {
          append = append+"isFlipped: true,color:'"+color+"'}";
        } else {
          append = append+"isFlipped: false}";
        }
        body = body+append+",";
      }
      body = body+"],";
    }
    return body+"]}]}";
  }

  @Test
  void testBoardListGson() {
    String body = buildBoardList();
    BoardList boardList = gson.fromJson(jsonParser.parse(body), BoardList.class);
    assertEquals(boardList.type,"listOfBoards");
    assertEquals(boardList.boards.size(), 1);
    assertEquals(boardList.boards.get(0).cells[0][0].id, "0 0");
    assertEquals(boardList.boards.get(0).cells[7][3].id, "7 3");
  }

  @Test
  void testGameListGson() {

  }

  @Test
  void testInviteListGson() {

  }

  @Test
  void testUserListGson() {

  }

  @Test
  void testUserRequestGson() {
    String body = "{type: 'testType', username: 'testUser', email: 'testEmail', password: 'testPass'}";
    UserRequest userRequest = gson.fromJson(jsonParser.parse(body), UserRequest.class);
    assertEquals(userRequest.type, "testType");
    assertEquals(userRequest.email, "testEmail");
    assertEquals(userRequest.username, "testUser");
    assertEquals(userRequest.password, "testPass");
  }

  @Test
  void testUserRequestGsonPart() {
    String body = "{type: 'testType', username: 'testUser'}";
    UserRequest userRequest = gson.fromJson(jsonParser.parse(body), UserRequest.class);
    assertEquals(userRequest.type, "testType");
    assertEquals(userRequest.email, null);
    assertEquals(userRequest.username, "testUser");
    assertEquals(userRequest.password, null);
  }

  @Test
  void testQueryRequestGson() {
    String body = "{type: 'testType', username: 'testUsername', gameId: 'testGameId'}";
    QueryRequest queryRequest = gson.fromJson(jsonParser.parse(body), QueryRequest.class);
    assertEquals(queryRequest.type, "testType");
    assertEquals(queryRequest.username, "testUsername");
    assertEquals(queryRequest.gameId, "testGameId");
  }

  @Test
  void testGameRequestGson() {
    String body = "{type: 'testType', gameId:'testGameId', fromCell: 'testFromCell', toCell: 'testToCell', username:'testUsername'}";
    GameRequest gameRequest = gson.fromJson(jsonParser.parse(body), GameRequest.class);
    assertEquals(gameRequest.type, "testType");
    assertEquals(gameRequest.gameId, "testGameId");
    assertEquals(gameRequest.toCell, "testToCell");
    assertEquals(gameRequest.fromCell, "testFromCell");
  }
}