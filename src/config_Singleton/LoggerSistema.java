package config_Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class LoggerSistema {
    private static LoggerSistema instancia;
    private LoggerSistema() {}

    public static LoggerSistema getInstancia() {
        if (instancia == null) {
            instancia = new LoggerSistema();
        }
        return instancia;
    }

    public void registerLog(String mensaje){
        System.out.println(LocalDateTime.now() + " | " + mensaje);
    }
    
    public void error(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }
    
    public boolean guardarLogBD(int idUsuario, String accion) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "INSERT INTO log_sistema (id_usuario, accion) VALUES (?, ?)";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ps.setString(2, accion);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("[ERROR LOG] Falla en la persistencia de auditoria: " + e.getMessage());
            return false;
        }
    }
}
