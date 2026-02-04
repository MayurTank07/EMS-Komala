package in.ems.entities;

public class Dept {
	
	private int deptID;
	private String deptName;
	
	
	
	public Dept(int deptID, String deptName) {
		super();
		this.deptID = deptID;
		this.deptName = deptName;
	}
	
	
	public int getDeptID() {
		return deptID;
	}
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
