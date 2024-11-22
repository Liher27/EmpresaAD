package executables.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import executables.logic.dbUtils.DBUtils;
import executables.logic.pojo.Employee;

public class Exercise3Logic extends AbstractManager {

	public Employee selectEmployee() throws Exception {
		Employee employee = new Employee();
		Class.forName(DBUtils.DRIVER);

		Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		String sql = "SELECT * FROM EMPLEADOS JOIN DEPARTAMENTOS ON EMPLEADOS.DEPT_NO = DEPARTAMENTOS.DEPT_NO ORDER BY SALARIO DESC LIMIT 1;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();

		if (result.next()) {
			employee.setApellido(result.getString("apellido"));
			employee.setDnombre(result.getString("dnombre"));
			employee.setSalario(result.getInt("salario"));
		}
		release(conn, pstmt, result);
		return employee;
	}

	public Employee selectEmployeeAlmacenado() {
		return null;
	}

}
