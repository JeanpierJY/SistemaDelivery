/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_SOLID;

import java.util.List;
import modelos.Pedido;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public interface IPedidoDAO {
    boolean registrarPedido(Pedido pedido);
    List<Pedido> obtenerPedidosPorEstado(String estado);
    boolean actualizarEstadoPedido(int idPedido, String nuevoEstado);
    boolean asignarRepartidor(int idPedido, int idRepartidor);
}
