package in.ems.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.ems.entities.Employee;


// this class is responsible to perform all the dml operations on Employee table 

public class DBEmployee {

	
	// for insertion on employee table
	public boolean insertEmployee(Employee emp)
	{
		  boolean status = false;

		    String sql = "INSERT INTO emp (eName, eSalary, deptID, eAddress, ePhone, eDOJ, eDOL) VALUES (?, ?, ?, ?, ?, ?, ?)";

		    try (Connection conn = DBConnection.connectDb();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setString(1, emp.geteName());
		        ps.setDouble(2, emp.geteSalary());
		        ps.setInt(3, emp.getDeptID());
		        ps.setString(4, emp.geteAddress());
		        ps.setString(5, emp.getePhone());
		        ps.setString(6, emp.geteDOJ());
		        if (emp.geteDOL() == null || emp.geteDOL().isEmpty()) {
		            ps.setNull(7, java.sql.Types.DATE);
		        } else {
		            ps.setDate(7, java.sql.Date.valueOf(emp.geteDOL()));
		        }

		        int rows = ps.executeUpdate();  // 1

		        if (rows > 0) {
		            status = true;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return status;
	}

	// for update emp recordds on employee table
	public boolean updateEmployee(Employee emp)
	{
		 boolean status = false;

		    String sql = "UPDATE emp SET eName=?, eSalary=?, deptID=?, eAddress=?, ePhone=?, eDOJ=?, eDOL=? WHERE eID=?";

		    try (Connection conn = DBConnection.connectDb();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setString(1, emp.geteName());
		        ps.setDouble(2, emp.geteSalary());
		        ps.setInt(3, emp.getDeptID());
		        ps.setString(4, emp.geteAddress());
		        ps.setString(5, emp.getePhone());
		        ps.setString(6, emp.geteDOJ());
		        ps.setString(7, emp.geteDOL());
		        ps.setInt(8, emp.geteID());

		        int rows = ps.executeUpdate();  // 1

		        if (rows > 0) {
		            status = true;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return status;
	}
	
	// for delete employee rec on employee table
	public boolean deleteEmployee(int empId)
	{
		boolean status = false;

	    String sql = "DELETE FROM emp WHERE eID=?";

	    try (Connection conn = DBConnection.connectDb();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, empId);

	        int rows = ps.executeUpdate();   // 1

	        if (rows > 0) {
	            status = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return status;
	}
	
	// for view all the on employee table
	public List<Employee> viewEmployees() {

	    List<Employee> list = new ArrayList<>();

	    String sql = "SELECT * FROM emp";

	    try (Connection conn = DBConnection.connectDb();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            Employee emp = new Employee();

	            emp.seteID(rs.getInt("eID"));
	            emp.seteName(rs.getString("eName"));
	            emp.seteSalary(rs.getDouble("eSalary"));
	            emp.setDeptID(rs.getInt("deptID"));
	            emp.seteAddress(rs.getString("eAddress"));
	            emp.setePhone(rs.getString("ePhone"));
	            emp.seteDOJ(rs.getString("eDOJ"));
	            emp.seteDOL(rs.getString("eDOL"));

	            list.add(emp);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	
	public Employee viewEmployeeById(int empId)
	{
		 Employee emp = null;

		    String sql = "SELECT * FROM emp WHERE eID=?";

		    try (Connection conn = DBConnection.connectDb();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setInt(1, empId);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {

		            emp = new Employee();

		            emp.seteID(rs.getInt("eID"));
		            emp.seteName(rs.getString("eName"));
		            emp.seteSalary(rs.getDouble("eSalary"));
		            emp.setDeptID(rs.getInt("deptID"));
		            emp.seteAddress(rs.getString("eAddress"));
		            emp.setePhone(rs.getString("ePhone"));
		            emp.seteDOJ(rs.getString("eDOJ"));
		            emp.seteDOL(rs.getString("eDOL"));
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return emp;
		
	}
	
	
}
