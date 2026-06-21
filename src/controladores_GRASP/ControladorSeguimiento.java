/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores_GRASP;

import logicaSeguimiento_Observer.IObservador;
import modelos.Pedido;
import logicaNotificaciones_Factory.Notificacion;
import logicaNotificaciones_Factory.NotificacionFactory;

public class ControladorSeguimiento implements IObservador {

    @Override
    public void actualizar(Pedido pedido) {
        System.out.println("[SISTEMA] Detectado cambio de estado en BD. Pedido ID: " + pedido.getIdPedido());
        try {
            Notificacion alerta = NotificacionFactory.crearNotificacion("EMAIL");
            alerta.enviarMensaje("Tu pedido ahora esta en estado: " + pedido.getEstado());
        } catch (Exception e) {
            System.out.println("Error en Factory de notificaciones.");
        }
    }
}

