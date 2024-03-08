package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminSection {

	public void adminMenu(Connection con, Scanner scanner) throws SQLException {
		boolean exit = false;
		while (!exit) {
			System.out.println("\nAdmin Menu:");
			System.out.println("1. Display Customer Details");
			System.out.println("2. Update Customer Details");
			System.out.println("3. Remove Customer");
			System.out.println("4. Filter Personal Details");
			System.out.println("5. No. of Customers ");
			System.out.println("6. Search Customer");
			System.out.println("7. Exit Admin Menu");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				displayCustomers(con);
				break;
			case 2:
				updateCustomer(con, scanner);
				break;
			case 3:
				removeCustomer(con, scanner);
				break;
			case 4:
				showColumnResult(con, scanner);
				break;
			case 5:
				showCustomerCount(con);
				break;
			case 6:
				displayCustomersByCharacter(con, scanner);
				break;
			case 7:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			}
		}
	}

	private void displayCustomers(Connection con) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM customers");

		System.out.printf("%-15s %-20s %-15s %-40s %-15s%n", "Customer ID", "Customer Name", "Mobile Number",
				"Products", "Payment Mode");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------");

		while (rs.next()) {
			printFormattedTableRow(rs.getInt("Customer_ID"), rs.getString("Customer_Name"), rs.getLong("Mobile_Number"),
					rs.getString("Products"), rs.getString("PaymentMode"));
		}
	}

	private void updateCustomer(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter Customer ID: ");
		int id = sc.nextInt();

		System.out.println("Enter New Customer Name: ");
		String name = sc.next();

		String query = String.format("UPDATE customers SET Customer_Name='%s' WHERE Customer_ID = %d", name, id);

		int rowsAffected = st.executeUpdate(query);
		System.out.println(rowsAffected + " record updated!!!");
	}

	private void removeCustomer(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter Customer ID: ");
		int id = sc.nextInt();

		int rowsAffected = st.executeUpdate("DELETE FROM customers WHERE Customer_ID = " + id);
		System.out.println(rowsAffected + " record deleted!!!");
	}

	private void showColumnResult(Connection con, Scanner scanner) throws SQLException {
		try (Statement st = con.createStatement()) {
			String query = "SELECT Customer_Name, Mobile_Number FROM customers";
			ResultSet rs = st.executeQuery(query);

			System.out.println("Results for Customer Name and Mobile Number:");
			while (rs.next()) {
				String customerName = rs.getString("Customer_Name");
				long mobileNumber = rs.getLong("Mobile_Number");
				System.out.printf("Customer Name: %s, Mobile Number: %d%n", customerName, mobileNumber);
			}
		}
	}

	private void showCustomerCount(Connection con) throws SQLException {
		try (Statement st = con.createStatement()) {
			String query = "SELECT COUNT(*) AS customer_count FROM customers";
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				int customerCount = rs.getInt("customer_count");
				System.out.println("Total number of customers: " + customerCount);
			}
		}
	}

	private void displayCustomersByCharacter(Connection con, Scanner scanner) throws SQLException {
		System.out.println("Enter the character to search for customer : ");
		String character = scanner.next();

		try (Statement st = con.createStatement()) {
			String query = "SELECT * FROM customers WHERE Customer_Name LIKE '%" + character + "%'";
			ResultSet rs = st.executeQuery(query);

			System.out.println("Customers with names containing the character '" + character + "':");
			while (rs.next()) {
				int customerId = rs.getInt("Customer_ID");
				String customerName = rs.getString("Customer_Name");
				long mobileNumber = rs.getLong("Mobile_Number");
				String products = rs.getString("Products");
				String paymentMode = rs.getString("PaymentMode");

				System.out.printf("Customer ID: %d, Name: %s, Mobile Number: %d, Products: %s, Payment Mode: %s%n",
						customerId, customerName, mobileNumber, products, paymentMode);
			}
		}
	}

	private void printFormattedTableRow(int customerId, String customerName, long mobileNumber, String products,
			String paymentMode) {
		System.out.printf("%-15d %-20s %-15d %-40s %-15s%n", customerId, customerName, mobileNumber, products,
				paymentMode);
	}
}
