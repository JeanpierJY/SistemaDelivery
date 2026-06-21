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

public class EstadoCompletado implements EstadoPedido {
    @Override
    public void siguienteEstado(Pedido pedido) {
        System.out.println("El pedido ya está completado. No hay un siguiente estado.");
    }

    @Override
    public void mostrarEstado() {
        System.out.println("El pedido está COMPLETADO.");
    }
}
