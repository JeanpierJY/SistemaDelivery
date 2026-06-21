/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_SOLID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import config_Singleton.ConexionBD;
import config_Singleton.LoggerSistema;
import modelos.Producto;
/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class ProductoDAOPostgres implements IProductoDAO{

    @Override
    public List<Producto> obtenerCatalogo() {
        List<Producto> catalogo = new ArrayList<>();
        Connection conexion = ConexionBD.getInstancia().getConexion();
        
        String query = "SELECT * FROM producto ORDER BY id_producto ASC";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setDisponible(rs.getBoolean("disponible"));
                catalogo.add(p);
                
            }
        }catch(Exception e){
            LoggerSistema.getInstancia().error("Error al cargar catalogo: " + e.getMessage());
        }
        return catalogo;
    }

    @Override
    public boolean registrarProducto(Producto producto) {
        Connection  conexion = ConexionBD.getInstancia().getConexion();
        String query = "INSERT INTO producto(nombre,descripcion,precio,disponible) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setBoolean(4, producto.isDisponible());
            
            return ps.executeUpdate()>0;
        }catch(Exception e){
            LoggerSistema.getInstancia().error("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "UPDATE producto SET nombre = ?, descripcion = ?, precio=?, disponible= ? WHERE id_producto = ?";
    
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setBoolean(4, producto.isDisponible());
            ps.setInt(5, producto.getIdProducto());
            
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            LoggerSistema.getInstancia().error("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }   
        
    @Override
    public boolean eliminarProducto(int idProducto) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "UPDATE producto SET disponible = false WHERE id_producto = ?";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idProducto);
            
            return ps.executeUpdate() >0;
        }catch(Exception e){
            LoggerSistema.getInstancia().error("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    
}
