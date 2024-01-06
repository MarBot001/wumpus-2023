package hu.nye.progTech;

import java.sql.*;

public class ConnectToData {

    public boolean doesPlayerExist(String playerName, String playerPassword) {
        String url = "jdbc:mysql://localhost:3306/wumpus_progtech";
        String user = "your_username";
        String password = "your_password";

        String query = "SELECT * FROM PLAYER WHERE NAME = ? AND PASSWORD = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, playerPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePlayerScore(String playerName) {
        String url = "jdbc:mysql://localhost:3306/wumpusz_progtech";
        String user = "your_username";
        String password = "your_password";

        String updateQuery = "UPDATE PLAYER SET SCORE = SCORE + 1 WHERE NAME = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, playerName);
            preparedStatement.executeUpdate();
            System.out.println("Játékos pontszáma növelve.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPlayerData(String playerName, String playerPassword) {
        String url = "jdbc:mysql://localhost:3306/wumpusz_progtech"; // MySQL adatbázis URL-je
        String user = "your_username"; // MySQL felhasználónév
        String password = "your_password"; // MySQL jelszó

        String insertQuery = "INSERT INTO PLAYER (NAME, PASSWORD, SCORE) VALUES (?, ?, 1)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, playerPassword);
            preparedStatement.executeUpdate();
            System.out.println("Új játékos rekord hozzáadva.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
