import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException; 
import java.sql.Statement;

public class InsertingOperation {

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
				String insertDataSQL = "INSERT INTO Courses (Name, Department, Duration) VALUES "
						+ "('BCA', 'AIIT', '3 Year'), " + "('BCA+MCA', 'AIIT', '4 Year')";
				int rowsAffected = statement.executeUpdate(insertDataSQL);
				System.out.println(rowsAffected + " row(s) inserted into the 'Courses' table.");
			} catch (SQLException e) {
				System.out.println("Failed to insert data: " + e.getMessage());

			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}