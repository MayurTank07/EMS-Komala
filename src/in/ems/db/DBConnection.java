package in.ems.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
		
	public static Connection connectDb() {

	    Connection conn = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/employee_management_db",
	            "root",
	            "dexysexy@69"
	        );

	        System.out.println("Connected with DB...");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return conn;
	}

	
	public static boolean createRequiredTables() {

	    boolean status = false;

	    try {
	        Connection con = connectDb();
	        java.sql.Statement stmt = con.createStatement();

	        // 1. Department Table
	        String deptTable = """
	            CREATE TABLE IF NOT EXISTS dept (
	                deptID INT PRIMARY KEY AUTO_INCREMENT,
	                deptName VARCHAR(100) NOT NULL
	            )
	        """;

	        // 2. Employee Table
	        String empTable = """
	            CREATE TABLE IF NOT EXISTS emp (
	                eID INT PRIMARY KEY AUTO_INCREMENT,
	                eName VARCHAR(100) NOT NULL,
	                eSalary DOUBLE NOT NULL,
	                deptID INT,
	                eAddress VARCHAR(255),
	                ePhone VARCHAR(15),
	                eDOJ DATE,
	                eDOL DATE,
	                FOREIGN KEY (deptID) REFERENCES dept(deptID)
	            )
	        """;

	        // 3. Registration Table
	        String registrationTable = """
	            CREATE TABLE IF NOT EXISTS registration (
	                id INT PRIMARY KEY AUTO_INCREMENT,
	                username VARCHAR(50) UNIQUE NOT NULL,
	                password VARCHAR(255) NOT NULL,
	                email VARCHAR(100) UNIQUE NOT NULL
	            )
	        """;

	        stmt.execute(deptTable);
	        stmt.execute(empTable);
	        stmt.execute(registrationTable);

	        status = true;
	        System.out.println("✅ All required tables created successfully.");

	    } catch (Exception e) {
	        System.out.println("❌ Error creating tables");
	        e.printStackTrace();
	    }

	    return status;
	}

	
	
}
