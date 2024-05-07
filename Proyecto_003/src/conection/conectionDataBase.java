package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectionDataBase {

	private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "0698";

    public static Connection connect() {
        Connection conection = null;
        try {
        	conection = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a la base de datos PostgreSQL servidor con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conection;
    }
}
