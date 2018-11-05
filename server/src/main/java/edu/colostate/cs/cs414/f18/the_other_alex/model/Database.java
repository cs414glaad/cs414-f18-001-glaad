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

    public String getUserByEmail(String email) {
// command line args contain username and password
        // pom.xml
        try { // connect to the database
             Class.forName(myDriver);
                       Connection conn = DriverManager.getConnection(myUrl, username, password);
                // SSH TUNNEL WILL NEED TO BE CREATED IN JAVASCRIPT DURING WEBSITE
            // START-UPs
            // Maybe do this in constructor
                        try { // create a statement
                               Statement st = conn.createStatement();
                               try { // submit a query
                                      String query = "SELECT UserObject FROM UserTable WHERE Email = \"" + email + "\";";
                                        ResultSet rs = st.executeQuery(query);

                                     try {
                                         return rs.getString(0);
                                     } finally {
                                         rs.close();
                                     }
                               } finally {
                                   st.close();
                               }
                        } finally {
                            conn.close();
                        }
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            if (e instanceof SQLException) {
                System.err.println("SQLException caught, rethrowing");
               // throw new SQLException(e);
                System.exit(-1);
            } else {
                System.exit(-1);
                //throw new ClassNotFoundException("ClassNotFoundException caught, rethrowing", e);
            }
        }
        return null;
    }

    public User findSerializedUserByEmail(String email) throws SQLException, IOException,
            ClassNotFoundException {
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE Email = \"" + email + "\";";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(deserializeUserSearchString);
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

    public User findSerializedUserByUserID(int UserID) throws SQLException, IOException,
            ClassNotFoundException {
        String deserializeUserSearchString = "SELECT UserObject FROM UserTable WHERE UserID = \"" + UserID + "\";";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(deserializeUserSearchString);
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

    public long addSerializedUser(User user) throws SQLException, IOException,
            ClassNotFoundException {
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String serializeUser = "INSERT INTO UserTable(Email, UserObject) VALUES (?, ?);";

        PreparedStatement pstmt = conn.prepareStatement(serializeUser);
        pstmt.setString(1, user.getEmail());
        pstmt.setObject(2, user);
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
        String deserializeGameSearchString = "SELECT GameObject FROM Game WHERE GameID = " + GameID + ";";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(deserializeGameSearchString);
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        Game deSerializedObject = (Game) objectIn.readObject();
        rs.close();
        st.close();
        conn.close();

        return deSerializedObject;
    }

    public long addSerializedGame(Game game) throws SQLException, IOException,
            ClassNotFoundException {
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, username, password);
        String serializeGame = "INSERT INTO Game(GameObject) VALUES (?);";

        PreparedStatement pstmt = conn.prepareStatement(serializeGame);
        pstmt.setObject(1, game);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int GameID = -1;
        if (rs.next()) {
            GameID = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return GameID;

    }
}
