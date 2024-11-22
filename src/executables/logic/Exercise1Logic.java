package executables.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import executables.logic.dbUtils.DBUtils;
import executables.logic.pojo.Department;

public class Exercise1Logic extends AbstractManager {

	public List<Department> selectDepartments() throws Exception {
		List<Department> departments = new ArrayList<Department>();

		Class.forName(DBUtils.DRIVER);

		Connection conn = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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

	public List<Department> selectDepartmentsAlmacenado() {
		return null;
	}

}
