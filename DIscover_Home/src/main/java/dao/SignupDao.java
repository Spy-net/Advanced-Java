package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Tourist;
import utils.JDBCUtils;

public class SignupDao {

    public int registerTourist(Tourist tourist) throws ClassNotFoundException {
        String INSERT_TOURIST_SQL = "INSERT INTO tourists" +
            "  (email, username, password, phone_number) VALUES " +
            " (?, ?, ?, ?);";

        int result = 0;
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOURIST_SQL)) {
            preparedStatement.setString(1, tourist.getEmail()); 
            preparedStatement.setString(2, tourist.getUsername()); 
            preparedStatement.setString(3, tourist.getPassword());
            preparedStatement.setString(4, tourist.getPhoneNumber());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {

            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}
