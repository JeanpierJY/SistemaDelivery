package DAO_SOLID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config_Singleton.ConexionBD;
import config_Singleton.LoggerSistema;
import modelos.Pedido;
import modelos.DetallePedido;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class PedidoDAOPostgres implements IPedidoDAO {

    @Override
    public boolean registrarPedido(Pedido pedido) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        boolean exito = false;

        try {
            conexion.setAutoCommit(false);
            String queryPedido = "INSERT INTO pedido (id_cliente, id_repartidor, estado, costo_envio, total) VALUES (?, ?, ?, ?, ?) RETURNING id_pedido";
            
            PreparedStatement psPedido = conexion.prepareStatement(queryPedido);
            psPedido.setInt(1, pedido.getIdCliente());
            
            if (pedido.getIdRepartidor() != null) {
                psPedido.setInt(2, pedido.getIdRepartidor());
            } else {
                psPedido.setNull(2, java.sql.Types.INTEGER);
            }
            
            psPedido.setString(3, pedido.getEstado());
            psPedido.setDouble(4, pedido.getCostoEnvio());
            psPedido.setDouble(5, pedido.getTotal());
            ResultSet rs = psPedido.executeQuery();
            
            int idPedidoGenerado = 0;
            if (rs.next()) {
                idPedidoGenerado = rs.getInt("id_pedido");
            }

            String queryDetalle = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
            PreparedStatement psDetalle = conexion.prepareStatement(queryDetalle);

            for (DetallePedido detalle : pedido.getDetalles()) {
                psDetalle.setInt(1, idPedidoGenerado);
                psDetalle.setInt(2, detalle.getIdProducto());
                psDetalle.setInt(3, detalle.getCantidad());
                psDetalle.setDouble(4, detalle.getPrecioUnitario());
                psDetalle.executeUpdate();
            }

            conexion.commit();
            exito = true;
            LoggerSistema.getInstancia().registerLog("Pedido registrado con ID: " + idPedidoGenerado);

        } catch (Exception e) {
            try {
                conexion.rollback();
                LoggerSistema.getInstancia().error("Cambios revertidos por error: " + e.getMessage());
            } catch (Exception ex) {
                LoggerSistema.getInstancia().error("Fallo de la base de datos.");
            }
        } finally {
            try { conexion.setAutoCommit(true); } catch (Exception e) {}
        }

        return exito;
    }

    @Override
    public List<Pedido> obtenerPedidosPorEstado(String estado) {
        List<Pedido> lista = new ArrayList<>();
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "SELECT * FROM pedido WHERE estado = ? ORDER BY fecha_registro ASC";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdCliente(rs.getInt("id_cliente"));
                
                int idRep = rs.getInt("id_repartidor");
                if (!rs.wasNull()) {
                    p.setIdRepartidor(idRep);
                }
                
                p.setEstado(rs.getString("estado"));
                p.setCostoEnvio(rs.getDouble("costo_envio"));
                p.setTotal(rs.getDouble("total"));
                p.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                lista.add(p);
            }
        }catch(Exception e){
            LoggerSistema.getInstancia().error("Error al obtener pedidos"+e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "UPDATE pedido SET estado = ? WHERE id_pedido = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idPedido);
            
            boolean exito = ps.executeUpdate() > 0;
            if(exito){
                 LoggerSistema.getInstancia().registerLog("Pedido #" + idPedido + " cambio a estado: " + nuevoEstado);
            }
            return exito;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean asignarRepartidor(int idPedido, int idRepartidor) {
        Connection conexion = ConexionBD.getInstancia().getConexion();
        String query = "UPDATE pedido SET id_repartidor = ?, estado = 'EN CURSO' WHERE id_pedido = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idRepartidor);
            ps.setInt(2, idPedido);
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error al asignar repartidor: " + e.getMessage());
            return false;
        }
    }
}