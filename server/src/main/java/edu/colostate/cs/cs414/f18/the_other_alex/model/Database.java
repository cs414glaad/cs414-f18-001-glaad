package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.sql.*;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Observer;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.GameService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.UserService;

public class Database {
    private String username;
    private String password;
    private String myUrl;
    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public Database() {
        username = "ashellum";
        password = "830284211";
        //String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
        myUrl = "jdbc:mysql://129.82.45.59:3306/theotheralex";
    }
    // Or I could make it take a database connection, that would be more efficient probs. We'll see if it's an issue

    // condense all of this find user shit into one method instead of copying code like a fool
    public User getUserByEmail(String email) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String deserializeUserSearchString = "SELECT SerializedObject FROM UserTable WHERE Email = ?;";
//        Class.forName(myDriver).newInstance();
            conn = DriverManager.getConnection(myUrl, username, password);
            st = conn.prepareStatement(deserializeUserSearchString);
            st.setString(1, email);
            rs = st.executeQuery();
            rs.next();

            byte[] buf = rs.getBytes(1);
            ObjectInputStream objectIn = null;
            if (buf != null) {
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            }
            User deSerializedObject = (User) objectIn.readObject();

            return deSerializedObject;
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }


    public User getUser(String username) throws SQLException, IOException,
            ClassNotFoundException , IllegalAccessException, InstantiationException {
        try {
            String deserializeUserSearchString = "SELECT SerializedObject FROM UserTable WHERE Username = ?;";
//        Class.forName(myDriver).newInstance();
            conn = DriverManager.getConnection(myUrl, this.username, password);
            st = conn.prepareStatement(deserializeUserSearchString);
            st.setString(1, username);
            rs = st.executeQuery();
            rs.next();

            byte[] buf = rs.getBytes(1);
            ObjectInputStream objectIn = null;
            if (buf != null) {
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            }
            User deSerializedObject = (User) objectIn.readObject();

            return deSerializedObject;
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    public User findSerializedUserByUserID(int UserID) throws SQLException, IOException,
            ClassNotFoundException , IllegalAccessException, InstantiationException{
        try {
            String deserializeUserSearchString = "SELECT SerializedObject FROM UserTable WHERE UserID = ?;";
//        Class.forName(myDriver).newInstance();
            conn = DriverManager.getConnection(myUrl, username, password);
            st = conn.prepareStatement(deserializeUserSearchString);
            st.setInt(1, UserID);
            rs = st.executeQuery();
            rs.next();

            byte[] buf = rs.getBytes(1);
            ObjectInputStream objectIn = null;
            if (buf != null) {
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            }
            User deSerializedObject = (User) objectIn.readObject();

            return deSerializedObject;
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    private int addSerializedUser(User user) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class.forName(myDriver).newInstance();
        try {
            conn = DriverManager.getConnection(myUrl, username, password);
            String serializeUser = "INSERT INTO UserTable(Email, Username, SerializedObject) VALUES (?, ?, ?);";

            st = conn.prepareStatement(serializeUser, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getEmail());
            st.setString(2, user.getUsername());
            st.setObject(3, user);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            int UserID = -1;
            if (rs.next()) {
                UserID = rs.getInt(1);
            }
            return UserID;
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }

    }

    public Game getGame(String GameID) throws SQLException, IOException,
            ClassNotFoundException , IllegalAccessException, InstantiationException{
        String deserializeGameSearchString = "SELECT SerializedObject FROM Game WHERE GameID = ?;";
        try {
            conn = DriverManager.getConnection(myUrl, username, password);
            st = conn.prepareStatement(deserializeGameSearchString);
            st.setString(1, GameID);
            rs = st.executeQuery();
            rs.next();

            byte[] buf = rs.getBytes(1);
            ObjectInputStream objectIn = null;
            if (buf != null) {
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            }
            Game deserializedObject = (Game) objectIn.readObject();

            return deserializedObject;
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    public int addSerializedObject(Object o) throws ClassNotFoundException, IOException,
            SQLException, IllegalAccessException, InstantiationException {
        if (o instanceof User) {
            return addSerializedUser((User) o);
        }
        else if (o instanceof Game) {
            addGameObjectToTable((Game) o);
            return 0;
        }
        else {
            throw new ClassNotFoundException("The object passed to addSerializedObject is of an unsupported type.");
        }
    }

    private void addGameObjectToTable(Game g) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class.forName(myDriver).newInstance();
        try {
            conn = DriverManager.getConnection(myUrl, username, password);
            String serializeGameHistory = "INSERT INTO Game(GameID, SerializedObject) VALUES (?, ?);";

            st = conn.prepareStatement(serializeGameHistory);
            st.setString(1, g.getGameId());
            st.setObject(2, g);
            st.executeUpdate();
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    //FOR TESTING PURPOSES ONLY
    public void deleteUserEntryUsingID(int userID) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

//        Class.forName(myDriver).newInstance();
        try {
            conn = DriverManager.getConnection(myUrl, username, password);
            String userDeletionString = "DELETE FROM UserTable WHERE UserID = ?;";

            st = conn.prepareStatement(userDeletionString);
            st.setInt(1, userID);
            st.executeUpdate();
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    //FOR TESTING PURPOSES ONLY
    public void deleteGameEntryUsingID(String gameID) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {

//        Class.forName(myDriver).newInstance();
        try {
            conn = DriverManager.getConnection(myUrl, username, password);
            String gameDeletionString = "DELETE FROM Game WHERE GameID = ?;";

            st = conn.prepareStatement(gameDeletionString);
            st.setString(1, gameID);
            st.executeUpdate();
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false ) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }

    public void deleteUserEntryUsingUsername(String deletionUsername) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

//        Class.forName(myDriver).newInstance()
        try {
            conn = DriverManager.getConnection(myUrl, username, password);
            String userDeletionString = "DELETE FROM UserTable WHERE Username = ?;";

            st = conn.prepareStatement(userDeletionString);
            st.setString(1, deletionUsername);
            st.executeUpdate();
        }
        finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
    }
}
