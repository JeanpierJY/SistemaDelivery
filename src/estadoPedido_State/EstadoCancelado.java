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
public class EstadoCancelado implements EstadoPedido {
    @Override
    public void siguienteEstado(Pedido pedido) {
        System.out.println("El pedido se ha cancelado. Comuniquese con el restaurante al 964711128.");
    }

    @Override
    public void mostrarEstado() {
        System.out.println("El pedido ha sido CANCELADO.");
    }
}
