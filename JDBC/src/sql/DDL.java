package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DDL {
	static final String DB_URL = "jdbc:mysql://localhost/";
	   static final String USER = "root";
	   static final String PASS = "Tharif786";

	public static void main(String[] args) {
		// Open a connection
	      try( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	   Statement stmt = conn.createStatement();
	    	  ) 
	      {	// creating DATABASE	      
	         String createDB = "CREATE DATABASE Library";
	         stmt.executeUpdate(createDB);
	         System.out.println("Database created successfully...");   
	        
	         // using the Library databse
	         String useDB="USE Library";
	         stmt.executeUpdate(useDB);
	         System.out.println("Using Library Database");
	        
	         // creating BOOKS TABLE
	         String booksTable ="CREATE TABLE Books (" +
	                    "BookID INT AUTO_INCREMENT PRIMARY KEY, " +
	                    "Title VARCHAR(255) NOT NULL, " +
	                    "Author VARCHAR(255) NOT NULL, " +
	                    "ISBN VARCHAR(13) UNIQUE NOT NULL, " +
	                    "PublishedYear INT CHECK (PublishedYear >= 1800 AND PublishedYear <= 2100), " +
	                    "Copies INT NOT NULL DEFAULT 1" +
	                    ")";
	         stmt.executeUpdate(booksTable);
	         System.out.println("Books Table Created successfully");

	         //creating Members Table
	         String membersTable = "CREATE TABLE Members (" +
	                    "MemberID INT AUTO_INCREMENT PRIMARY KEY, " +
	                    "Name VARCHAR(100) NOT NULL, " +
	                    "Email VARCHAR(100) UNIQUE NOT NULL, " +
	                    "PhoneNumber VARCHAR(15) NOT NULL, " +
	                    "MembershipDate DATE NOT NULL" +
	                    ")";
	          stmt.executeUpdate(membersTable);
	          System.out.println("Members table created successfully.");
	          
	          //show all databases present
	          System.out.println("\nDatabases available:");
	          ResultSet rsDatabases = stmt.executeQuery("SHOW DATABASES");
	          while (rsDatabases.next()) {
	               System.out.println(rsDatabases.getString(1));
	            }
	          // tables present in the Library database  
	          System.out.println("\nTables in Library database:");
	          ResultSet rsTables = stmt.executeQuery("SHOW TABLES");
	          while (rsTables.next()) {
	               System.out.println(rsTables.getString(1));
	            }
	       
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	}

}
