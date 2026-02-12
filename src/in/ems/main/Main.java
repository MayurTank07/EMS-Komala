package in.ems.main;

import java.util.List;
import java.util.Scanner;

import in.ems.db.DBConnection;
import in.ems.db.DBDept;
import in.ems.db.DBEmployee;
import in.ems.db.DBLogin;
import in.ems.db.DBRegistration;
import in.ems.entities.Dept;
import in.ems.entities.Employee;
import in.ems.entities.Registration;

public class Main {
	
	public static void showDashboard(Scanner sc) {

	    DBDept dbDept = new DBDept();
	    DBEmployee dbEmployee = new DBEmployee();

	    int choice;

	    do {
	        System.out.println("\n========= EMS DASHBOARD =========");
	        System.out.println("1. Add Department");
	        System.out.println("2. View Departments");
	        System.out.println("3. Add Employee");
	        System.out.println("4. View All Employees");
	        System.out.println("5. Update Employee");
	        System.out.println("6. Delete Employee");
	        System.out.println("7. Logout");
	        System.out.print("Enter Choice: ");

	        choice = Integer.parseInt(sc.nextLine());

	        switch (choice) {

	            // ------------------ ADD DEPT ------------------
	            case 1:
	                System.out.print("Enter Department Name: ");
	                String deptName = sc.nextLine();

	                Dept dept = new Dept(0, deptName);

	                if (dbDept.insertDepartment(dept))
	                    System.out.println("✅ Department Added Successfully");
	                else
	                    System.out.println("❌ Failed to Add Department");
	                break;

	            // ------------------ VIEW DEPT ------------------
	            case 2:
	                List<Dept> deptList = dbDept.viewDepartments();

	                for (Dept d : deptList) {
	                    System.out.println(d.getDeptID() + " - " + d.getDeptName());
	                }
	                break;

	            // ------------------ ADD EMPLOYEE ------------------
	            case 3:
	                System.out.print("Enter Name: ");
	                String name = sc.nextLine();

	                System.out.print("Enter Salary: ");
	                double salary = Double.parseDouble(sc.nextLine());

	                System.out.print("Enter Dept ID: ");
	                int deptId = Integer.parseInt(sc.nextLine());

	                System.out.print("Enter Address: ");
	                String address = sc.nextLine();

	                System.out.print("Enter Phone: ");
	                String phone = sc.nextLine();

	                System.out.print("Enter DOJ (yyyy-mm-dd): ");
	                String doj = sc.nextLine();

	                System.out.print("Enter DOL (yyyy-mm-dd or NULL): ");
	                String dol = sc.nextLine();
	                if (dol.isEmpty()) {
	                    dol = null;
	                }

	                Employee emp = new Employee(0, name, salary, deptId, address, phone, doj, dol);

	                if (dbEmployee.insertEmployee(emp))
	                    System.out.println("✅ Employee Added Successfully");
	                else
	                    System.out.println("❌ Failed to Add Employee");

	                break;

	            // ------------------ VIEW EMPLOYEES ------------------
	            case 4:
	                List<Employee> empList = dbEmployee.viewEmployees();

	                for (Employee e : empList) {
	                    System.out.println("----------------------------------");
	                    System.out.println("ID: " + e.geteID());
	                    System.out.println("Name: " + e.geteName());
	                    System.out.println("Salary: " + e.geteSalary());
	                    System.out.println("Dept ID: " + e.getDeptID());
	                    System.out.println("Phone: " + e.getePhone());
	                }
	                break;

	            // ------------------ UPDATE EMPLOYEE ------------------
	            case 5:
	                System.out.print("Enter Employee ID to Update: ");
	                int updateId = Integer.parseInt(sc.nextLine());

	                Employee existingEmp = dbEmployee.viewEmployeeById(updateId);

	                if (existingEmp == null) {
	                    System.out.println("❌ Employee Not Found");
	                    break;
	                }

	                System.out.print("Enter New Name: ");
	                existingEmp.seteName(sc.nextLine());

	                System.out.print("Enter New Salary: ");
	                existingEmp.seteSalary(Double.parseDouble(sc.nextLine()));

	                if (dbEmployee.updateEmployee(existingEmp))
	                    System.out.println("✅ Employee Updated Successfully");
	                else
	                    System.out.println("❌ Update Failed");

	                break;

	            // ------------------ DELETE EMPLOYEE ------------------
	            case 6:
	                System.out.print("Enter Employee ID to Delete: ");
	                int deleteId = Integer.parseInt(sc.nextLine());

	                if (dbEmployee.deleteEmployee(deleteId))
	                    System.out.println("✅ Employee Deleted Successfully");
	                else
	                    System.out.println("❌ Delete Failed");

	                break;

	            case 7:
	                System.out.println("👋 Logged Out Successfully");
	                break;

	            default:
	                System.out.println("Invalid Choice");
	        }

	    } while (choice != 7);
	}


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DBConnection.connectDb();
        DBConnection.createRequiredTables();
        
        System.out.println("\n==========================================");
        System.out.println("        Welcome to EMS System");
        System.out.println("==========================================\n");

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("\nEnter your choice : ");

        int choice = Integer.parseInt(sc.nextLine()); // FIXED

        switch (choice) {

            case 1:
                System.out.println("\n---------- User Registration ----------\n");

                System.out.print("Enter Email    : ");
                String email = sc.nextLine();

                System.out.print("Enter Username : ");
                String username = sc.nextLine();

                System.out.print("Enter Password : ");
                String password = sc.nextLine();

                Registration resg = new Registration(username, password, email);

                DBRegistration dbReg = new DBRegistration();

                boolean isInserted = dbReg.storeRegistrationData(resg);

                if (isInserted) {
                    System.out.println("\n✅ Registration completed successfully!");
                } else {
                    System.out.println("\n❌ Registration failed. Try again.");
                }
                System.out.println("----------------------------------------");


                break;

            case 2:
            	  System.out.println("\n---------- User Login ----------\n");

            	    System.out.print("Enter Email / Username : ");
            	    String loginId = sc.nextLine();

            	    System.out.print("Enter Password         : ");
            	    String loginPassword = sc.nextLine();

            	    DBLogin dbLogin = new DBLogin();
            	    
            	    boolean isValidLogin = dbLogin.checkLoginDetails(loginId, loginPassword);
            	    
            	    if(isValidLogin)
            	    {
            	    	System.out.println("\n✅ Login Successful!");
            	        System.out.println("Welcome back to EMS 🎉");
            	        
            	        showDashboard(sc);
            	    }
            	    else
            	    {
            	    	 System.out.println("\n❌ Invalid credentials!");
            	         System.out.println("Please check your email/username or password.");
            	    }

                break;

            default:
                System.out.println("\n❌ Invalid Choice. Please try again.");
        }

        sc.close();
    }
}
