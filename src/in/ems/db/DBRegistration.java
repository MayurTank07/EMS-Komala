package in.ems.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.ems.entities.Registration;

public class DBRegistration {

	public boolean storeRegistrationData(Registration reg)
	{
		 boolean status = false;

	        try {
	            Connection conn = DBConnection.connectDb();

	            String sql = "INSERT INTO registration (username, password, email) VALUES (?, ?, ?)";

	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, reg.getUsername());
	            ps.setString(2, reg.getPassword());
	            ps.setString(3, reg.getEmail());

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                status = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return status;
	}
	
}
