import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import admin.AdminSection;
import customer.CustomerSection;

public class ResturantManagementSystem {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/javaadv";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "SPYBOT";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);

            System.out.println("Welcome to Restaurant Management System!");
            boolean exit = false;
            while (!exit) {
                System.out.println("\nChoose Your Role:");
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        AdminSection adminSection = new AdminSection();
                        adminSection.adminMenu(con, scanner);
                        break;
                    case 2:
                        CustomerSection customerSection = new CustomerSection();
                        customerSection.customerMenu(con, scanner);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
