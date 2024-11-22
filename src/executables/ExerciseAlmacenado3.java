package executables;

import executables.logic.Exercise3Logic;
import executables.logic.pojo.Employee;

public class ExerciseAlmacenado3 {

	public static void main(String[] args) {

		try {
			Exercise3Logic db = new Exercise3Logic();
			Employee employee = db.selectEmployeeAlmacenado();
			System.out.println("APELLIDO: " + employee.getApellido());
			System.out.println("DEPARTAMENTO: " + employee.getDnombre());
			System.out.println("SALARIO: " + employee.getSalario());
		} catch (Exception e) {
			System.out.println("ha habido un error leyendo los datos.");
		}

	}

}
