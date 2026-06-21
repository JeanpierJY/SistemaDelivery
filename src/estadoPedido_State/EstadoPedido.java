/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadoPedido_State;
import modelos.Pedido;
/**
 *
 * @author Jean
 */
public interface EstadoPedido {
    void siguienteEstado(Pedido pedido);
    void mostrarEstado();
}