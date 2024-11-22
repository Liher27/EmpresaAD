package executables.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.CallableStatement;

import executables.logic.dbUtils.DBUtils;
import executables.logic.pojo.Employee;

public class Exercise3Logic extends AbstractManager {

	Connection conn = null;

	CallableStatement stmt = null;

	public Exercise3Logic() throws SQLException, ClassNotFoundException {
		Class.forName(DBUtils.DRIVER);

		conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
	}

	public Employee selectEmployee() throws SQLException {
		Employee employee = new Employee();
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

	public Employee selectEmployeeAlmacenado() throws SQLException {
		Employee employee = new Employee();

		// Este procedimiento almacenado esta creado en la base de datos, y llama a
		// getBestEmployee(), el cual hace la misma query que el anterior metodo
		String sql = "{call getBestEmployee()}";

		CallableStatement cstmt = conn.prepareCall(sql);
		ResultSet result = cstmt.executeQuery();

		if (result.next()) {
			employee.setApellido(result.getString("apellido"));
			employee.setOficio(result.getString("oficio"));
			employee.setSalario(result.getInt("salario"));
		}

		release(conn, cstmt, result);
		return employee;
	}
}
