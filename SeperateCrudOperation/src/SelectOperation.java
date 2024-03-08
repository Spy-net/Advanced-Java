import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectOperation {

    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/javaadv";
        String username = "root";
        String password = "SPYBOT";

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    
    public static void main(String[] args) {
        try {
            Connection connection = connect();
            System.out.println("Connected to the database: " + connection.getCatalog());
 
            try (Statement statement = connection.createStatement()) {
                String selectQuery = "SELECT * FROM Courses";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    String department = resultSet.getString("Department");
                    String duration = resultSet.getString("Duration");

                    System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department + ", Duration: " + duration);
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query: " + e.getMessage());
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}