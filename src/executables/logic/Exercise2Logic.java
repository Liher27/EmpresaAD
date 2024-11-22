package executables.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import executables.logic.dbUtils.DBUtils;
import executables.logic.pojo.Employee;

public class Exercise2Logic extends AbstractManager {

	public List<Employee> selectEmployees() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();

		Class.forName(DBUtils.DRIVER);

		Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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

	public List<Employee> selectEmployeesAlmacenado() {
		return null;
	}

}
