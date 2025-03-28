package executables;

import java.util.List;

import executables.logic.Exercise2Logic;
import executables.logic.pojo.Employee;

public class ExerciseAlmacenado2 {

	public static void main(String[] args) {

		try {
			Exercise2Logic db = new Exercise2Logic();
			List<Employee> employees = db.selectEmployeesAlmacenado();

			for (Employee employee : employees) {
				System.out.println("APELLIDO: " + employee.getApellido());
				System.out.println("OFICIO: " + employee.getOficio());
				System.out.println("SALARIO: " + employee.getSalario());
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("ha habido un error leyendo los datos.");
		}
	}

}
