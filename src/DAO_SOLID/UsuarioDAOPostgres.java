/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_SOLID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config_Singleton.ConexionBD;
import config_Singleton.LoggerSistema;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class UsuarioDAOPostgres implements IUsuarioDAO {

    @Override
    public String verificarUsuarioEnBD(String email) {
        String rolEncontrado = null;
        
        // Uso de Singleton para obtener la conexion
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "SELECT * FROM usuario WHERE email = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                rolEncontrado = rs.getString("rol");
                
                System.out.println("-> Respuesta de BD: El usuario '" + nombre + "' existe y tiene el rol " + rolEncontrado);
            } else {
                System.out.println("-> Respuesta de BD: Correo no encontrado.");
            }
            
        } catch (Exception e) {
            // Uso de Singleton para registrar el error
            LoggerSistema.getInstancia().error("Error SQL: " + e.getMessage());
        }
        
        return rolEncontrado;
    }
}