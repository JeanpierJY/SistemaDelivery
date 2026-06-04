package database;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    //Aqui se aplica Singleton
    private static ConexionBD instancia;
    private Connection conexion;
    
    private ConexionBD() {
        // Datos de configuracion para PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/delivery";
        String usuario = "postgres"; 
        String password = "root";    

        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa a PostgreSQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontro el Driver de PostgreSQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos");
            e.printStackTrace();
        }
    }
    
    //Aqui se crea la instancia solo una vez
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}