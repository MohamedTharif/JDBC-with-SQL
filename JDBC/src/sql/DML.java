package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DML {
	   private static final String DB_URL = "jdbc:mysql://localhost/";
	   private static final String USER = "root";
	   private static final String PASS = "Tharif786";


	public static void main(String[] args) {
		  try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		             Statement stmt = conn.createStatement()) {
					  
			        // using the Library databse
				    String useDB="USE Library";
				    stmt.executeUpdate(useDB);
				   
		            // Insert values into Books table
		           String insertBooksSQL = "INSERT INTO Books (Title, Author, ISBN, PublishedYear, Copies) " +
		                                     "VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 1925, 5), " +
		                                     "('1984', 'George Orwell', '9780451524935', 1949, 10), " +
		                                     "('To Kill a Mockingbird', 'Harper Lee', '9780060935467', 1960, 7)";
		            stmt.executeUpdate(insertBooksSQL);
		            System.out.println("Inserted records into Books table.");

		            // Insert values into Members table
		            String insertMembersSQL = "INSERT INTO Members (Name, Email, PhoneNumber, MembershipDate) " +
		                                       "VALUES ('Alice Johnson', 'alice@example.com', '1234567890', '2024-01-15'), " +
		                                       "('Bob Smith', 'bob@example.com', '0987654321', '2024-02-01'), " +
		                                       "('Carol Davis', 'carol@example.com', '1122334455', '2024-03-10')";
		            stmt.executeUpdate(insertMembersSQL);
		            System.out.println("Inserted records into Members table.");
		            
		         // Execute the DESCRIBE TABLE command
		            String descBooksSQL = "DESC Books";
		            ResultSet rs = stmt.executeQuery(descBooksSQL);

		            // Print the structure of the Books table
		            System.out.println("Books Table Structure:");
		            System.out.println("Field           | Type              | Null | Key | Default | Extra");
		            System.out.println("---------------------------------------------------------------");

		            while (rs.next()) {
		                System.out.printf("%-15s | %-17s | %-4s | %-3s | %-7s | %-10s\n",
		                        rs.getString("Field"),
		                        rs.getString("Type"),
		                        rs.getString("Null"),
		                        rs.getString("Key"),
		                        rs.getString("Default") != null ? rs.getString("Default") : "NULL",
		                        rs.getString("Extra"));
		            }
		            
		            
		            // Query to fetch all records from Books table
		            String selectBooksSQL = "SELECT * FROM Books";
		            ResultSet booksResult = stmt.executeQuery(selectBooksSQL);
		            System.out.println("Books Table:");
		            System.out.println("BookID | Title                     | Author              | ISBN          | PublishedYear | Copies");
		            System.out.println("-------------------------------------------------------------------------------------------");
		            while (booksResult.next()) {
		                System.out.printf("%-6d | %-25s | %-20s | %-13s | %-13d | %-6d\n",
		                        booksResult.getInt("BookID"),
		                        booksResult.getString("Title"),
		                        booksResult.getString("Author"),
		                        booksResult.getString("ISBN"),
		                        booksResult.getInt("PublishedYear"),
		                        booksResult.getInt("Copies"));
		            }

		            // Query to fetch all records from Members table
		            String selectMembersSQL = "SELECT * FROM Members";
		            ResultSet membersResult = stmt.executeQuery(selectMembersSQL);
		            System.out.println("\nMembers Table:");
		            System.out.println("MemberID | Name           | Email                 | PhoneNumber | MembershipDate");
		            System.out.println("-------------------------------------------------------------------------------");
		            while (membersResult.next()) {
		                System.out.printf("%-8d | %-15s | %-20s | %-12s | %-15s\n",
		                        membersResult.getInt("MemberID"),
		                        membersResult.getString("Name"),
		                        membersResult.getString("Email"),
		                        membersResult.getString("PhoneNumber"),
		                        membersResult.getDate("MembershipDate"));
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

	}

}
