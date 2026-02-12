package in.ems.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.ems.entities.Dept;

public class DBDept {

    // 1️⃣ Insert Department
    public boolean insertDepartment(Dept dept) {

        boolean status = false;

        String sql = "INSERT INTO dept (deptName) VALUES (?)";

        try (Connection conn = DBConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dept.getDeptName());

            int rows = ps.executeUpdate();  // 1

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // 2️⃣ Update Department
    public boolean updateDepartment(Dept dept) {

        boolean status = false;

        String sql = "UPDATE dept SET deptName=? WHERE deptID=?";

        try (Connection conn = DBConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dept.getDeptName());
            ps.setInt(2, dept.getDeptID());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // 3️⃣ Delete Department
    public boolean deleteDepartment(int deptId) {

        boolean status = false;

        String sql = "DELETE FROM dept WHERE deptID=?";

        try (Connection conn = DBConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // 4️⃣ View All Departments
    public List<Dept> viewDepartments() {

        List<Dept> list = new ArrayList<>();

        String sql = "SELECT * FROM dept";

        try (Connection conn = DBConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Dept dept = new Dept(
                        rs.getInt("deptID"),
                        rs.getString("deptName")
                );

                list.add(dept);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 5️⃣ View Department By ID
    public Dept viewDepartmentById(int deptId) {

        Dept dept = null;

        String sql = "SELECT * FROM dept WHERE deptID=?";

        try (Connection conn = DBConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                dept = new Dept(
                        rs.getInt("deptID"),
                        rs.getString("deptName")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dept;
    }
}
