package Enviroment;

import java.sql.*;
import Game.*;
import java.util.Scanner;

public  class mySqlDatabase {

    private static Connection conn;
    private static ResultSet rs;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11495525", "sql11495525", "nb6eLsmCaw");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveLevelResult(String level, int score, String userID) throws SQLException {            // Sends the result to database which is hosted in free hosting service

        Statement statement = conn.createStatement();
        String sqlInsert = "INSERT INTO HighScores (levelID, score,userID) VALUES (?, ?, ?) ";

        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM HighScores WHERE levelID = ? AND userID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        selectStatement.setString(1, level);
        selectStatement.setString(2, userID);

        ResultSet playerRun = selectStatement.executeQuery();
        playerRun.last();
        int rowCount = playerRun.getRow();
        System.out.println(rowCount);
        if(rowCount == 0){
            PreparedStatement thisStatement = conn.prepareStatement(sqlInsert);
            thisStatement.setString(1, level);
            thisStatement.setString(2, Integer.toString(score));
            thisStatement.setString(3, userID);

            thisStatement.executeUpdate();

            ResultSet resultSet = statement.executeQuery("select * from HighScores");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("userID") +" "+ resultSet.getString("levelID")+" "+ resultSet.getString("score"));
            }
            System.out.println("Saved players first score in this level");
        }else{
            System.out.println(score);
            int runID = Integer.parseInt(playerRun.getString("runID"));
            if(Integer.parseInt(playerRun.getString("score")) > score){
                PreparedStatement updateBestRun = conn.prepareStatement("Update HighScores SET score = ? WHERE runID = ?");
                updateBestRun.setString(1, Integer.toString(score));
                updateBestRun.setString(2, Integer.toString(runID));
                updateBestRun.executeUpdate();
                ResultSet resultSet = statement.executeQuery("select * from HighScores");

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("userID") +" "+ resultSet.getString("levelID")+" "+ resultSet.getString("score"));
                }
                System.out.println("Updated the score");
            }
        }
    }

    public static int getUserID(String userName) throws SQLException {
        int id;
        if(!doesUserExist(userName)){
            insertUser(userName);
        }
        Statement statement = conn.createStatement();
        String sqlSelect = "SELECT * FROM Users WHERE userName = ? ";
        PreparedStatement thisStatement = conn.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        thisStatement.setString(1, userName);

        ResultSet rs = thisStatement.executeQuery();
        rs.next();
        id = Integer.parseInt(rs.getString("id"));
        return id;
    }

    public static boolean doesUserExist(String userName) throws SQLException {
        Statement statement = conn.createStatement();
        String sqlSelect = "SELECT * FROM Users WHERE userName = ? ";
        PreparedStatement thisStatement = conn.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        thisStatement.setString(1, userName);

        ResultSet rs = thisStatement.executeQuery();
        rs.last();
        if(rs.getRow() == 0){
            return false;
        }else{
            return true;
        }
    }

    public static void insertUser(String userName) throws SQLException {
        Statement statement = conn.createStatement();
        String sqlSelect = "INSERT INTO Users (userName) VALUES (?)";
        PreparedStatement thisStatement = conn.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        thisStatement.setString(1, userName);
        thisStatement.executeUpdate();
    }

    public static void createLobby(String player1,String level) throws SQLException {
        Statement statement = conn.createStatement();
        String sqlInsert = "INSERT INTO Lobbies (Player_1,Running,Level) VALUES (?, ?, ?) ";

        PreparedStatement thisStatement = conn.prepareStatement(sqlInsert);
        thisStatement.setString(1, player1);
        thisStatement.setString(2, Integer.toString(0));

        System.out.println("ok");
        thisStatement.executeUpdate();
    }
    public static void joinLobby(String player_1, String player_2) throws SQLException {
        PreparedStatement updateLobby = conn.prepareStatement("Update Lobbies SET Player_2 = ? WHERE Player_1 = ?");

        updateLobby.setString(1,player_2);
        updateLobby.setString(2,player_1);

        updateLobby.executeUpdate();
    }
   public static String getHighestScoreOfAllTime(String level) throws SQLException {

       PreparedStatement selectStatement = conn.prepareStatement("SELECT score FROM HighScores  WHERE levelID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       selectStatement.setString(1, level);

       ResultSet playerRun = selectStatement.executeQuery();
       String highestScore = "0";
       while(playerRun.next()){
           highestScore = playerRun.getString("score");
       }
       return highestScore;
   }

    public static String getUsersHighestScoreOfAllTime(String level, String userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT score FROM HighScores WHERE levelID = ? AND userID = ? ORDER BY score ASC", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        selectStatement.setString(1, level);
        selectStatement.setString(2, userID);

        ResultSet playerRun = selectStatement.executeQuery();
        String highestScore = "0";
        while(playerRun.next()){
            highestScore = playerRun.getString("score");
        }
        return highestScore;
    }
}