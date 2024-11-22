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
import executables.logic.pojo.Department;

public class Exercise1Logic extends AbstractManager {

	Connection conn = null;

	CallableStatement stmt = null;

	public Exercise1Logic() throws SQLException, ClassNotFoundException {
		Class.forName(DBUtils.DRIVER);

		conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
	}

	public List<Department> selectDepartments() throws Exception {
		List<Department> departments = new ArrayList<Department>();

		conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		String sql = "SELECT * FROM DEPARTAMENTOS";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();

		if (result.next()) {
			Department department = new Department();

			department.setDnombre(result.getString("dnombre"));
			department.setLoc(result.getString("loc"));
			department.setDept_no(result.getInt("dept_no"));

			departments.add(department);
		}
		release(conn, pstmt, result);
		return departments;
	}

	public List<Department> selectDepartmentsAlmacenado() throws SQLException {
		List<Department> departments = new ArrayList<Department>();

		// Este procedimiento almacenado esta creado en la base de datos, y llama a
		// get_departments(), el cual hace la misma query que el anterior metodo
		String sql = "{call get_departments()}";
		
		CallableStatement stmt = conn.prepareCall(sql);
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			Department department = new Department();
			department.setDnombre(result.getString("dnombre"));
			department.setLoc(result.getString("loc"));
			department.setDept_no(result.getInt("dept_no"));
			departments.add(department);
		}
		
		release(conn, stmt, result);
		return departments;
	}

}
