package Enviroment;

import java.sql.*;
import java.util.Scanner;

public  class mySqlDatabase {

    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11495525", "sql11495525", "nb6eLsmCaw");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;



    public static void saveLevelResult(String level, int score, String username) throws SQLException {            // Sends the result to database which is hosted in free hosting service

        Statement statement = conn.createStatement();
        String sqlInsert = "INSERT INTO HighScores (levelID, score,username) VALUES (?, ?, ?) ";

        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM HighScores WHERE levelID = ? AND username = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        selectStatement.setString(1, level);
        selectStatement.setString(2, username);

        ResultSet playerRun = selectStatement.executeQuery();
        playerRun.last();
        int rowCount = playerRun.getRow();
        System.out.println(rowCount);
        if(rowCount == 0){
            PreparedStatement thisStatement = conn.prepareStatement(sqlInsert);
            thisStatement.setString(1, level);
            thisStatement.setString(2, Integer.toString(score));
            thisStatement.setString(3, username);

            thisStatement.executeUpdate();

            ResultSet resultSet = statement.executeQuery("select * from HighScores");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username") +" "+ resultSet.getString("levelID")+" "+ resultSet.getString("score"));
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
                    System.out.println(resultSet.getString("username") +" "+ resultSet.getString("levelID")+" "+ resultSet.getString("score"));
                }
                System.out.println("Updated the score");
            }
        }
    }

   public static String getHighestScoreOfAllTime(String level) throws SQLException {
       PreparedStatement selectStatement = conn.prepareStatement("SELECT score, username FROM HighScores WHERE levelID = ? ORDER BY score ASC", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       selectStatement.setString(1, level);

       ResultSet playerRun = selectStatement.executeQuery();
       String highestScore = "0";
       while(playerRun.next()){
           highestScore = playerRun.getString("score");
       }
       System.out.println(highestScore);
       return highestScore;
   }
}