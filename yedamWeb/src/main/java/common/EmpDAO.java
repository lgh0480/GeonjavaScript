 package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public Employee selectEmp(String employeeId) {
		conn = DBCon.getConnect();
		String sql = "select * from empl_demo where employee_id = ?";
		Employee emp = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employeeId);
			psmt.executeQuery();
			rs= psmt.executeQuery();
			if(rs.next()) {
				emp = new Employee();
				emp.setEmail(rs.getString("email"));
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setHireDate(rs.getString("hire_date").substring(0,10));
				emp.setLastName(rs.getString("last_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public void insertEmp(Employee emp) {
		conn = DBCon.getConnect();
		String sql = "insert into empl_demo(employee_id, first_name, last_name, email, hire_date, job_id)"
				+ "values(?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, "IT_PROG");
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<Employee> getEmpList() {
		
		List<Employee> empList = new ArrayList<>();
		conn = DBCon.getConnect();
		try {
		 psmt = conn.prepareStatement("select * from empl_demo");
		 rs = psmt.executeQuery();
		while(rs.next()) {
			Employee emp = new Employee();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString("email"));
			emp.setHireDate(rs.getString("hire_date").substring(0,10));
			emp.setSalary(rs.getInt("salary"));
			empList.add(emp);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	public void updateEmp(Employee emp) {
		conn = DBCon.getConnect();
		String sql = "update empl_demo "
				+ "set first_name = ?, last_name = ?, email = ?, hire_date = ? "
				+ "where employee_id= ?";
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setInt(5, emp.getEmployeeId());
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteEmp(Employee emp) {
		conn = DBCon.getConnect();
		String sql = "delete from empl_demo where employee_id = ?";
				
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, emp.getEmployeeId());
			
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
