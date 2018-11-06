package edu.colostate.cs.cs414.f18.the_other_alex.model;

import java.sql.*;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.ArrayList;
import java.io.IOException;

public class Database {
    String username;
    String password;
    String myUrl;
    String myDriver;

    public Database() {
        username = "ashellum";
        password = "830284211";
        String myDriver = "com.mysql.jdbc.Driver"; // add dependencies in
        //String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
        String myUrl = "jdbc:mysql://faure.cs.colostate.edu/theotheralex";
    }
    // Or I could make it take a database connection, that would be more efficient probs. We'll see if it's an issue


    public User findSerializedUserByEmail(String email) throws SQLException, IOException,
            ClassNotFoundException {
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE Email = ?;";
        Class.forName(myDriver);
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

    //ADD USERNAME SEARCH
    public User findSerializedUserByUserID(int UserID) throws SQLException, IOException,
            ClassNotFoundException {
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE UserID = ?;";
        Class.forName(myDriver);
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
            ClassNotFoundException {
        Class.forName(myDriver);
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
            ClassNotFoundException {
        String deserializeGameSearchString = "SELECT GameObject FROM Game WHERE GameID = ?;";
        Class.forName(myDriver);
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


    //don't forget to change to PreparedStatements
    /*public GameRecord findSerializedGameRecordByID(int GameRecordID) throws SQLException, IOException,
            ClassNotFoundException {
        String deserializeGameRecordSearchString = "SELECT GameRecordObject FROM GameRecord WHERE GameID = ?;";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        PreparedStatement st = conn.prepareStatement(deserializeGameRecordSearchString);
        st.setInt(1, GameRecordID);
        ResultSet rs = st.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        GameRecord deSerializedObject = (GameRecord) objectIn.readObject();
        rs.close();
        st.close();
        conn.close();

        return deSerializedObject;
    }*/

    public long addSerializedObject(Object o) throws ClassNotFoundException, IOException,
            SQLException {
        if (o instanceof User) {
            return addSerializedUser((User) o);
        }
       /* else if (o instanceof UserHistory) {
            return addObjectToTable("UserHistory", o);
        }
        else if (o instanceof Invite) {
            return addObjectToTable("Invite", o);
        }
        else if (o instanceof GameRecord) {
            return addObjectToTable("GameRecord", o);
        }*/
        else if (o instanceof Game) {
            return addGameObjectToTable((Game) o);
        }
        else {
            throw new ClassNotFoundException("This is not a known table instance. Did you mean to call addFriends?");
        }
    }

    /*private long addObjectToTable(String tableName, Object o) throws SQLException, IOException,
            ClassNotFoundException {
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String serializeGameHistory = "INSERT INTO " + tableName + "(serializedObject) VALUES (?);";

        PreparedStatement pstmt = conn.prepareStatement(serializeGameHistory);
        pstmt.setObject(1, o);
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
    }*/
    private long addGameObjectToTable(Game g) throws SQLException, IOException,
            ClassNotFoundException {
        Class.forName(myDriver);
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
    /*
    public long addFriends(int UserIDOne, int UserIDTwo) throws SQLException, IOException,
            ClassNotFoundException {
    Class.forName(myDriver);
    Connection conn = DriverManager.getConnection(myUrl, username, password);
    String insertString = "INSERT INTO Friends(UserIDOne, UserIDTwo) VALUES(" + UserIDOne + ", " + UserIDTwo + ");" ;

    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(insertString);


    }*/

}
