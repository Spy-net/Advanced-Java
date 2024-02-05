package customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerSection {

	public void customerMenu(Connection con, Scanner scanner) throws SQLException {
		boolean exit = false;
		while (!exit) {
			System.out.println("\nCustomer Menu:");
			System.out.println("1. Create Account");
			System.out.println("2. Order Product");
			System.out.println("3. Display Profile");
			System.out.println("4. Update Profile");
			System.out.println("5. Exit Customer Menu");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				createAccount(con, scanner);
				break;
			case 2:
				orderProduct(con, scanner);
				break;
			case 3:
				displayProfile(con, scanner);
				break;
			case 4:
				updateProfile(con, scanner);
				break;
			case 5:
				exit = true;
				System.out.println("Don't Forget To Visit Again.");
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 5.");
			}
		}
	}

	private void createAccount(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter Customer Name: ");
		String name = sc.next();

		System.out.println("Enter Mobile Number: ");
		long mobileNumber = sc.nextLong();

		String query = String.format("INSERT INTO customers (Customer_Name, Mobile_Number) VALUES ('%s', %d)", name,
				mobileNumber);

		int rowsAffected = st.executeUpdate(query);
		System.out.println(rowsAffected + " record inserted!!!");
	}

	private void orderProduct(Connection con, Scanner sc) throws SQLException {
		System.out.println("Enter Mobile Number: ");
		long mobileNumber = sc.nextLong();

		try (Statement st = con.createStatement()) {
			String query = "SELECT Customer_ID FROM customers WHERE Mobile_Number = " + mobileNumber;
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				int customerId = rs.getInt("Customer_ID");

				System.out.println("Enter Products Ordered (comma-separated): ");
				String products = sc.next();

				System.out.println("Enter Payment Mode: ");
				String paymentMode = sc.next();

				String updateQuery = String.format(
						"UPDATE customers SET Products = '%s', PaymentMode = '%s' WHERE Customer_ID = %d", products,
						paymentMode, customerId);
				int rowsAffected = st.executeUpdate(updateQuery);

				System.out.println(rowsAffected + " record updated for Customer ID: " + customerId);
			} else {
				System.out.println("Customer not found with Mobile Number: " + mobileNumber);
			}
		}
	}

	private void displayProfile(Connection con, Scanner sc) throws SQLException {
		System.out.println("Enter Mobile Number: ");
		long mobileNumber = sc.nextLong();

		try (Statement st = con.createStatement()) {
			String query = "SELECT * FROM customers WHERE Mobile_Number = " + mobileNumber;
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				System.out.printf("%-15s %-20s %-15s %-40s %-15s%n", "Customer ID", "Customer Name", "Mobile Number",
						"Products", "Payment Mode");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------");
				printFormattedTableRow(rs.getInt("Customer_ID"), rs.getString("Customer_Name"),
						rs.getLong("Mobile_Number"), rs.getString("Products"), rs.getString("PaymentMode"));
			} else {
				System.out.println("Customer not found with Mobile Number: " + mobileNumber);
			}
		}
	}

	private void updateProfile(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter Customer ID: ");
		int id = sc.nextInt();

		System.out.println("Enter New Customer Name: ");
		String name = sc.next();

		System.out.println("Enter New Mobile Number: ");
		long mobileNumber = sc.nextLong();

		System.out.println("Enter New Products Ordered (comma-separated): ");
		String products = sc.next();

		System.out.println("Enter New Payment Mode: ");
		String paymentMode = sc.next();

		String query = String.format(
				"UPDATE customers SET Customer_Name='%s', Mobile_Number=%d, Products='%s', PaymentMode='%s' WHERE Customer_ID = %d",
				name, mobileNumber, products, paymentMode, id);

		int rowsAffected = st.executeUpdate(query);
		System.out.println(rowsAffected + " record updated!!!");
	}

	private void printFormattedTableRow(int customerId, String customerName, long mobileNumber, String products,
			String paymentMode) {
		System.out.printf("%-15d %-20s %-15d %-40s %-15s%n", customerId, customerName, mobileNumber, products,
				paymentMode);
	}
}
