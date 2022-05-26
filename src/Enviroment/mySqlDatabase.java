package Enviroment;

import java.sql.*;
import java.util.Scanner;

public  class mySqlDatabase {

    private static Connection conn;

    public static void saveLevelResult(String time, int collectibles, String username) throws SQLException {            // Sends the result to database which is hosted in free hosting service

        conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11495525", "sql11495525", "nb6eLsmCaw");
        Statement statement = conn.createStatement();
        String sqlInsert = "INSERT INTO HighScores (levelID, playerUsername, time, crystalsCollected) VALUES (?, ?, ?, ?) ";
        PreparedStatement thisStatement = conn.prepareStatement(sqlInsert);
        thisStatement.setString(1, "Level One");
        thisStatement.setString(2, username);
        thisStatement.setString(3, String.valueOf(time));
        thisStatement.setString(4, String.valueOf(collectibles));

        thisStatement.executeUpdate();

        ResultSet resultSet = statement.executeQuery("select * from HighScores");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("playerUsername") +" "+ resultSet.getString("levelID")+" "+ resultSet.getString("time")+" "+ resultSet.getString("crystalsCollected"));
        }
    }

    public static void getDB(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Nickname: ");
        String consolePlayerID = inputScanner.nextLine();
        //System.out.println("playerID: " +consolePlayerID);

        try {


            conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11495525", "sql11495525", "nb6eLsmCaw");

            Statement statement = conn.createStatement();

            String sqlInsert = "INSERT INTO playerinfo (playerNickname) VALUES (?) ";
            PreparedStatement thisStatement = conn.prepareStatement(sqlInsert);

            thisStatement.setString(1, consolePlayerID);
            thisStatement.executeUpdate();

           ResultSet resultSet = statement.executeQuery("select * from playerinfo");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") +" "+ resultSet.getString("playerNickname"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

}