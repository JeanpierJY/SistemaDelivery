/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Jean
 */
public class DAOPedido {
    private Connection conexion;

    public DAOPedido() {
        this.conexion = ConexionBD.getInstancia().getConexion() ;
    }
    
    public String obtenerEstadoActual(int idPedido){
        String sql = " SELECT estado_actual FROM pedido WHERE id_pedido=?";
        try{
        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, idPedido);
    
}
    
    
    
    
  
}
