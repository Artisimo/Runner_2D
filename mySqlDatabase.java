import java.sql.*;
import java.util.Scanner;

public  class mySqlDatabase {

    public static void getDB(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Nickname: ");
        String consolePlayerID = inputScanner.nextLine();
        System.out.println("playerID: " +consolePlayerID);

        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-runner2d", "root", "root");

            Statement statement = connection.createStatement();

            String sqlInsert = "INSERT INTO playerinfo (playerNickname) VALUES (?) ";
            PreparedStatement thisStatement = connection.prepareStatement(sqlInsert);

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