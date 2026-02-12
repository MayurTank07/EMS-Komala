package in.ems.entities;

public class Employee {
	
	private int eID;
	private String eName;
	private double eSalary;
	private int deptID;
	private String eAddress;
	private String ePhone;
	private String eDOJ;
	private String eDOL;
	
	
	public Employee()
	{
		super();
	}
	
	public Employee(int eID, String eName, double eSalary, int deptID, String eAddress, String ePhone, String eDOJ,
			String eDOL) {
		super();
		this.eID = eID;
		this.eName = eName;
		this.eSalary = eSalary;
		this.deptID = deptID;
		this.eAddress = eAddress;
		this.ePhone = ePhone;
		this.eDOJ = eDOJ;
		this.eDOL = eDOL;
	}
	
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public double geteSalary() {
		return eSalary;
	}
	public void seteSalary(double eSalary) {
		this.eSalary = eSalary;
	}
	public int getDeptID() {
		return deptID;
	}
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	public String geteAddress() {
		return eAddress;
	}
	public void seteAddress(String eAddress) {
		this.eAddress = eAddress;
	}
	public String getePhone() {
		return ePhone;
	}
	public void setePhone(String ePhone) {
		this.ePhone = ePhone;
	}
	public String geteDOJ() {
		return eDOJ;
	}
	public void seteDOJ(String eDOJ) {
		this.eDOJ = eDOJ;
	}
	public String geteDOL() {
		return eDOL;
	}
	public void seteDOL(String eDOL) {
		this.eDOL = eDOL;
	}
	
	
	
}
