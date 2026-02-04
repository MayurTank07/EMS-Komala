package in.ems.main;

import java.util.Scanner;

import in.ems.db.DBConnection;
import in.ems.db.DBRegistration;
import in.ems.entities.Registration;

public class Main {

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

            	    // logical part where we will be checking username and password 
            	    boolean isValidLogin = false;
            	    
            	    if(isValidLogin)
            	    {
            	    	System.out.println("\n✅ Login Successful!");
            	        System.out.println("Welcome back to EMS 🎉");
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
