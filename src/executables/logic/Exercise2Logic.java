package executables.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.CallableStatement;

import executables.logic.dbUtils.DBUtils;
import executables.logic.pojo.Employee;

public class Exercise2Logic extends AbstractManager {

	Connection conn = null;

	CallableStatement stmt = null;

	public Exercise2Logic() throws SQLException, ClassNotFoundException {
		Class.forName(DBUtils.DRIVER);

		conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
	}

	public List<Employee> selectEmployees() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();

		conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		String sql = "SELECT * FROM EMPLEADOS WHERE dept_no = '10'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();

		while (result.next()) {
			Employee employee = new Employee();

			employee.setApellido(result.getString("apellido"));
			employee.setOficio(result.getString("oficio"));
			employee.setSalario(result.getInt("salario"));

			employees.add(employee);
		}
		release(conn, pstmt, result);
		return employees;
	}

	public List<Employee> selectEmployeesAlmacenado() throws SQLException {
		List<Employee> employees = new ArrayList<Employee>();

		// Este procedimiento almacenado esta creado en la base de datos, y llama a
		// getDep10Employees(), el cual hace la misma query que el anterior metodo
		String sql = "{call getDep10Employees()}";

		CallableStatement cstmt = conn.prepareCall(sql);
		ResultSet result = cstmt.executeQuery();

		while (result.next()) {
			Employee employee = new Employee();

			employee.setApellido(result.getString("apellido"));
			employee.setOficio(result.getString("oficio"));
			employee.setSalario(result.getInt("salario"));

			employees.add(employee);

		}

		release(conn, cstmt, result);
		return employees;
	}
}
