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
import java.util.ArrayList;
import java.util.List;
import modelos.Persona;
import modelos.Empleado;
import modelos.Administrador;
import modelos.Cliente;
import modelos.Repartidor;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class UsuarioDAOPostgres implements IUsuarioDAO{
    @Override
    public Persona loginUsuario(String email, String password) {
        Persona usuarioAutenticado = null;
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query ="SELECT * FROM usuario WHERE email = ? AND password = ? AND rol IN ('ADMIN','EMPLEADO','REPARTIDOR','CLIENTE')";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String rolBd = rs.getString("rol");
                
                if(rolBd.equals("ADMIN")){
                    Administrador admin = new Administrador();
                    admin.setId(rs.getInt("id_usuario"));
                    admin.setDni(rs.getString("dni"));
                    admin.setNombre(rs.getString("nombre"));
                    admin.setEmail(rs.getString("email"));
                    admin.setRol(rolBd);
                    usuarioAutenticado = admin;
                    
                }else if(rolBd.equals("REPARTIDOR")){
                    Repartidor rappy = new Repartidor();
                    rappy.setId(rs.getInt("id_usuario"));
                    rappy.setDni(rs.getString("dni"));
                    rappy.setNombre(rs.getString("nombre"));
                    rappy.setEmail(rs.getString("email"));
                    rappy.setRol(rolBd);
                    rappy.setPlacaMoto(rs.getString("placa_moto"));
                    usuarioAutenticado = rappy;
                    
                }else if(rolBd.equals("CLIENTE")){
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id_usuario"));
                    cliente.setDni(rs.getString("dni"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setRol(rolBd);
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setFechaCumpleaños(rs.getDate("fecha_cumpleanios")); 
                    usuarioAutenticado = cliente;
                }else if(rolBd.equals("EMPLEADO")){
                    Empleado empleado = new Empleado();
                    empleado.setId(rs.getInt("id_usuario"));
                    empleado.setDni(rs.getString("dni"));
                    empleado.setDni(rs.getString("nombre"));
                    empleado.setEmail(rs.getString("email"));
                    empleado.setRol(rolBd);
                    usuarioAutenticado = empleado;
                }
            }
        } catch (Exception ex) {
             LoggerSistema.getInstancia().error("Error en el login "+ ex.getMessage());
        }
        return usuarioAutenticado;
    }

    @Override
    public boolean registrarUsuario(Persona nuevoUsuario, String password) {
        boolean exito = false;
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "INSERT INTO usuario (dni, nombre, email, password, direccion, rol, fecha_cumpleanios, placa_moto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            
            ps.setString(1, nuevoUsuario.getDni());
            ps.setString(2, nuevoUsuario.getNombre());
            ps.setString(3, nuevoUsuario.getEmail());
            ps.setString(4, password);
            
            String direccionParaBD = null;
            java.sql.Date fechaSqlParaBD = null; 
            String placaParaBD = null;
            
            if(nuevoUsuario instanceof Cliente){
                Cliente c = (Cliente) nuevoUsuario;
                direccionParaBD = c.getDireccion();
                if(c.getFechaCumpleaños() != null) {
                    fechaSqlParaBD = new java.sql.Date(c.getFechaCumpleaños().getTime());
                } 
            } else if (nuevoUsuario instanceof Repartidor) {
                Repartidor r = (Repartidor) nuevoUsuario;
                placaParaBD = r.getPlacaMoto();
            }
            
            ps.setString(5, direccionParaBD);
            ps.setString(6, nuevoUsuario.getRol());
            ps.setDate(7, fechaSqlParaBD); 
            ps.setString(8, placaParaBD);
            
            int filasInsertadas = ps.executeUpdate();
            if(filasInsertadas > 0){
                exito = true;
            }
        }catch(Exception e){
            LoggerSistema.getInstancia().error("Error al registrar: "+ e.getMessage());
        }
        return exito;
    }

    @Override
    public List<Persona> obtenerUsuarios() {
        List<Persona> lista = new ArrayList<>();
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "SELECT * FROM usuario ORDER BY id_usuario ASC";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String rolBd = rs.getString("rol");
                
                if(rolBd.equals("ADMIN")){
                    Administrador admin = new Administrador();
                    admin.setId(rs.getInt("id_usuario"));
                    admin.setDni(rs.getString("dni"));
                    admin.setNombre(rs.getString("nombre"));
                    admin.setEmail(rs.getString("email"));
                    admin.setRol(rolBd);
                    lista.add(admin);
                    
                }else if(rolBd.equals("REPARTIDOR")){
                    Repartidor rappy = new Repartidor();
                    rappy.setId(rs.getInt("id_usuario"));
                    rappy.setDni(rs.getString("dni"));
                    rappy.setNombre(rs.getString("nombre"));
                    rappy.setEmail(rs.getString("email"));
                    rappy.setRol(rolBd);
                    rappy.setPlacaMoto(rs.getString("placa_moto"));
                    lista.add(rappy);
                    
                }else if(rolBd.equals("CLIENTE")){
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id_usuario"));
                    cliente.setDni(rs.getString("dni"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setRol(rolBd);
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setFechaCumpleaños(rs.getDate("fecha_cumpleanios"));
                    lista.add(cliente);
                }
            }
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error al obtener usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizarUsuario(Persona usuario) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "UPDATE usuario SET dni=?, nombre=?, email=?, direccion=?, placa_moto=?, fecha_cumpleanios=? WHERE id_usuario=?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getEmail());
            
            String direccionParaBD = null;
            java.sql.Date fechaSqlParaBD = null; 
            String placaParaBD = null;
            
            if(usuario instanceof Cliente){
                Cliente c = (Cliente) usuario;
                direccionParaBD = c.getDireccion();
                if(c.getFechaCumpleaños() != null) {
                    fechaSqlParaBD = new java.sql.Date(c.getFechaCumpleaños().getTime());
                } 
            } else if (usuario instanceof Repartidor) {
                Repartidor r = (Repartidor) usuario;
                placaParaBD = r.getPlacaMoto();
            }
            
            ps.setString(4, direccionParaBD);
            ps.setString(5, placaParaBD);
            ps.setDate(6, fechaSqlParaBD);
            ps.setInt(7, usuario.getId());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(int idUsuario) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error al eliminar usuario (puede tener pedidos vinculados): " + e.getMessage());
            return false;
        }
    }
    
}