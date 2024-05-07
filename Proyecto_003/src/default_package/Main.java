package default_package;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.StudentImpl;

/**
 * @author David Llumiquinga
 * Title: Conexion a DDBB
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = conection.conectionDataBase.connect();
        if (connection != null) {
        	
            System.out.println("Conexión establecida.");
            
        } else {
        	
            System.out.println("No se pudo establecer la conexión.");
        }
        
        
        StudentImpl alumnoImpl= new StudentImpl(connection);
        
        
        
    
       
        List<Student> alumnos = new ArrayList<Student>();
        for (Student alumno : alumnos) {
            System.out.println(alumno.getId() + " " + alumno.getName() + " " + alumno.getLastname() + ", " + alumno.getAge());
        }


	}

}
