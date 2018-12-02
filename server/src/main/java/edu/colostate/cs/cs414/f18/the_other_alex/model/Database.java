package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.sql.*;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Database {
    String username;
    String password;
    String myUrl;
    String myDriver;

    public Database() {
        username = "ashellum";
        password = "830284211";
        myDriver = "com.mysql.jdbc.Driver"; // add dependencies in
        //String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
        myUrl = "jdbc:mysql://faure.cs.colostate.edu/theotheralex";
    }
    // Or I could make it take a database connection, that would be more efficient probs. We'll see if it's an issue

    // condense all of this find user shit into one method instead of copying code like a fool
    public User findSerializedUserByEmail(String email) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE Email = ?;";
//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        PreparedStatement st = conn.prepareStatement(deserializeUserSearchString);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        User deSerializedObject = (User) objectIn.readObject();
        rs.close();
        st.close();
        conn.close();

        return deSerializedObject;
    }


    public User findSerializedUserByUsername(String username) throws SQLException, IOException,
            ClassNotFoundException , IllegalAccessException, InstantiationException{
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE Username = ?;";
//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, this.username, password);
        PreparedStatement st = conn.prepareStatement(deserializeUserSearchString);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        User deSerializedObject = (User) objectIn.readObject();
        rs.close();
        st.close();
        conn.close();

        return deSerializedObject;
    }

    //ADD USERNAME SEARCH
    public User findSerializedUserByUserID(int UserID) throws SQLException, IOException,
            ClassNotFoundException , IllegalAccessException, InstantiationException{
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE UserID = ?;";
//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        PreparedStatement st = conn.prepareStatement(deserializeUserSearchString);
        st.setInt(1, UserID);
        ResultSet rs = st.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        User deSerializedObject = (User) objectIn.readObject();
        rs.close();
        st.close();
        conn.close();

        return deSerializedObject;
    }

    private long addSerializedUser(User user) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String serializeUser = "INSERT INTO UserTable(Email, Username, UserObject) VALUES (?, ?, ?);";

        PreparedStatement pstmt = conn.prepareStatement(serializeUser);
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getUsername());
        pstmt.setObject(3, user);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int UserID = -1;
        if (rs.next()) {
            UserID = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return UserID;

    }

    public Game findSerializedGameByID(int GameID) throws SQLException, IOException,
            ClassNotFoundException , IllegalAccessException, InstantiationException{
        String deserializeGameSearchString = "SELECT GameObject FROM Game WHERE GameID = ?;";
//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        PreparedStatement ptstmt = conn.prepareStatement(deserializeGameSearchString);
        ptstmt.setInt(1, GameID);
        ResultSet rs = ptstmt.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        Game deserializedObject = (Game) objectIn.readObject();
        rs.close();
        ptstmt.close();
        conn.close();

        return deserializedObject;
    }

    public long addSerializedObject(Object o) throws ClassNotFoundException, IOException,
            SQLException, IllegalAccessException, InstantiationException {
        if (o instanceof User) {
            return addSerializedUser((User) o);
        }
        else if (o instanceof Game) {
            return addGameObjectToTable((Game) o);
        }
        else {
            throw new ClassNotFoundException("The object passed to addSerializedObject is of an unsupported type.");
        }
    }

    private long addGameObjectToTable(Game g) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String serializeGameHistory = "INSERT INTO Game(GameID, serializedObject) VALUES (?, ?);";

        PreparedStatement pstmt = conn.prepareStatement(serializeGameHistory);
        pstmt.setInt(1, g.getGameId().hashCode());
        pstmt.setObject(2, g);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int serializedID = -1;
        if (rs.next()) {
            serializedID = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return serializedID;
    }

    //FOR TESTING PURPOSES ONLY
    public void deleteUserEntryUsingID(int userID) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String userDeletionString = "DELETE FROM UserTable WHERE UserID = ?;";

        PreparedStatement pstmt = conn.prepareStatement(userDeletionString);
        pstmt.setInt(1, userID);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    //FOR TESTING PURPOSES ONLY
    public void deleteGameEntryUsingID(int gameID) throws SQLException, IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {

//        Class.forName(myDriver).newInstance();
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String gameDeletionString = "DELETE FROM Game WHERE GameID = ?;";

        PreparedStatement pstmt = conn.prepareStatement(gameDeletionString);
        pstmt.setInt(1, gameID);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
