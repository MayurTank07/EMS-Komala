package in.ems.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBLogin {

	public boolean checkLoginDetails(String loginId, String loginPassword)
	{
		
		// checking out id and password
		boolean status = false;

        try (Connection conn = DBConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM registration WHERE username = ? AND password = ?"
             )) {

            ps.setString(1, loginId);
            ps.setString(2, loginPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                status = true;   // Login successful
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

		
		return status;
	}
	
}
