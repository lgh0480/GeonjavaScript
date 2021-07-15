package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	public static Connection getConnect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("연결 성공!!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn; 
	}
}
