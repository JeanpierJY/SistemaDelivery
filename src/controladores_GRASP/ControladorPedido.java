/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores_GRASP;

import DAO_SOLID.IPedidoDAO;
import DAO_SOLID.PedidoDAOPostgres;
import modelos.Pedido;
import logicaSeguimiento_Observer.SujetoPedido;

public class ControladorPedido {
    private IPedidoDAO pedidoDAO;
    private SujetoPedido notificador;

    public ControladorPedido() {
        this.pedidoDAO = new PedidoDAOPostgres();
        this.notificador = new SujetoPedido();
        // Conectamos el Observer al Controlador
        this.notificador.agregarObservador(new ControladorSeguimiento());
    }

    public boolean guardarYNotificar(Pedido nuevoPedido) {
        boolean exito = pedidoDAO.registrarPedido(nuevoPedido);
        if (exito) {
            notificador.notificarObservadores(nuevoPedido);
        }
        return exito;
    }

    public boolean actualizarEstadoYNotificar(int idPedido, String nuevoEstado) {
        boolean exito = pedidoDAO.actualizarEstadoPedido(idPedido, nuevoEstado);
        if (exito) {
            Pedido pMock = new Pedido();
            pMock.setIdPedido(idPedido);
            pMock.setEstado(nuevoEstado);
            notificador.notificarObservadores(pMock);
        }
        return exito;
    }
}
