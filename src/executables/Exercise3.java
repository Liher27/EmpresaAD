package executables;

import executables.logic.Exercise3Logic;
import executables.logic.pojo.Employee;

public class Exercise3 {

	public static void main(String[] args) {

		Exercise3Logic db = new Exercise3Logic();
		try {
			Employee employee = db.selectEmployee();
			System.out.println("APELLIDO: " + employee.getApellido());
			System.out.println("DEPARTAMENTO: " + employee.getDnombre());
			System.out.println("SALARIO: " + employee.getSalario());
		} catch (Exception e) {
			System.out.println("ha habido un error leyendo los datos.");
		}
		
		
	}

}
