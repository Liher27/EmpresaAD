package executables;

import java.util.List;

import executables.logic.Exercise1Logic;
import executables.logic.pojo.Department;

public class Exercise1 {

	public static void main(String[] args) {

		try {
			Exercise1Logic db = new Exercise1Logic();
			List<Department> departments = db.selectDepartments();

			for (Department department : departments) {
				System.out.println("NOMBRE: " + department.getDnombre());
				System.out.println("NUMERO: " + department.getDept_no());
				System.out.println("LOCALIDAD: " + department.getLoc());
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("ha habido un error leyendo los datos.");
		}
	}

}
